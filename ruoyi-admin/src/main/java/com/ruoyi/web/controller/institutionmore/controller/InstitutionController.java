package com.ruoyi.web.controller.institutionmore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.api.common.Result;
import org.apache.poi.ss.usermodel.*;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import com.ruoyi.web.controller.institutionmore.service.IInstitutionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.api.util.FileUtil.isExcelFile;

/**
 * 机构信息详情管理Controller
 *
 * @author ruoyi
 * @date 2024-12-16
 */
@RestController
@RequestMapping("/institution/")
public class InstitutionController extends BaseController
{
    @Autowired
    private IInstitutionService institutionService;


    /**
     * 查询机构信息详情管理列表
     */
    @PreAuthorize("@ss.hasPermi('institution:institutionmore:list')")
    @GetMapping("/list")
    public TableDataInfo list(Institution institution)
    {
        startPage();
        List<Institution> list = institutionService.selectInstitutionList(institution);
        return getDataTable(list);
    }

    /**
     * 导出机构信息详情管理列表
     */
    @PreAuthorize("@ss.hasPermi('institution:institutionmore:export')")
    @Log(title = "机构信息详情管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Institution institution)
    {
        List<Institution> list = institutionService.selectInstitutionList(institution);
        ExcelUtil<Institution> util = new ExcelUtil<Institution>(Institution.class);
        util.exportExcel(response, list, "机构信息详情管理数据");
    }

    /**
     * 获取机构信息详情管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('institution:institutionmore:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(institutionService.selectInstitutionById(id));
    }

    /**
     * 新增机构信息详情管理
     */
    @PreAuthorize("@ss.hasPermi('institution:institutionmore:add')")
    @Log(title = "机构信息详情管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Institution institution)
    {
        int max=institutionService.getMaxOrderId();
        institution.setOrderId(max+1);
        return toAjax(institutionService.insertInstitution(institution));
    }

    /**
     * 修改机构信息详情管理
     */
    @PreAuthorize("@ss.hasPermi('institution:institutionmore:edit')")
    @Log(title = "机构信息详情管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Institution institution)
    {
        institutionService.editUrl(institution.getUrl(),institution.getId());
        return toAjax(institutionService.updateInstitution(institution));
    }

    /**
     * 删除机构信息详情管理
     */
    @PreAuthorize("@ss.hasPermi('institution:institutionmore:remove')")
    @Log(title = "机构信息详情管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(institutionService.deleteInstitutionByIds(ids));
    }
    @PutMapping("/rank/")
    public AjaxResult updateWithRank()
    {
       List<Institution> list=institutionService.updateWithRank();
       return AjaxResult.success(list);
    }
    @PostMapping("/uploadExcel")
    public Result<List<Institution>> uploadExcel(@RequestParam("file") MultipartFile file)
            throws IOException {
        if(!isExcelFile(file)){
            throw new IllegalArgumentException("Uploaded file is not an Excel file.");
        }
        List<Institution> list=new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }
            //遍历每一行
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if(isRowEmpty(row)){
                    continue;
                }
                Map<String, String> rowMap = new HashMap<>();
                Institution institution = new Institution();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = (cell != null && !cell.toString().equals("/") ? cell.toString() : "");//处理空单元格
                    rowMap.put(headers.get(j), cellValue);
                    switch (headers.get(j)) {
                        case "所在省份":
                            institution.setProvince(cellValue);
                            break;
                        case "单位名称":
                            institution.setCompanyname(cellValue);
                            break;
                        case "主营基地":
                            institution.setBase(cellValue);
                            break;
                        case "办公室地址":
                            institution.setOffice(cellValue);
                            break;
                        case "统一社会信用代码":
                            institution.setCreditcode(cellValue);
                            break;
                        case "法人名称":
                            institution.setLegalperson(cellValue);
                            break;
                        case "训练负责人姓名":
                            institution.setResponsibleperson(cellValue);
                            break;
                        case "训练负责人联系电话":
                            if (!cellValue.equals("")) {
                                if (cellValue.contains("-")) {
                                    institution.setPhone(cellValue);
                                } else {
                                    double number = Double.parseDouble(cellValue);
                                    String normalString = String.format("%.0f", number);
                                    institution.setPhone(normalString);
                                }
                            }
                            break;
                    }
                }
                list.add(institution);
            }
            for (Institution ins : list) {
                institutionService.insertInstitution(ins);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("导入失败:"+e.getMessage());
        }
        return Result.success(list);
    }
    public boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // 只要有一个非空单元格，就认为行不为空
            }
        }
        return true; // 所有单元格都为空，行为空
    }


}
