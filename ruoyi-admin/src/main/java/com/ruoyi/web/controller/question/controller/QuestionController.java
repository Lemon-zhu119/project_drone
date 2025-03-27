package com.ruoyi.web.controller.question.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.api.common.Result;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import org.apache.poi.ss.usermodel.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.question.domain.Question;
import com.ruoyi.web.controller.question.service.IQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.api.util.FileUtil.isExcelFile;
import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 题目管理Controller
 *
 * @author ruoyi
 * @date 2024-12-11
 */
@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController
{
    @Autowired
    private IQuestionService questionService;

    /**
     * 查询题目管理列表
     */
    @PreAuthorize("@ss.hasPermi('question:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(Question question)
    {
        startPage();
        List<Question> list = questionService.selectQuestionList(question);
        return getDataTable(list);
    }

    /**
     * 导出题目管理列表
     */
    @PreAuthorize("@ss.hasPermi('question:question:export')")
    @Log(title = "题目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Question question)
    {
        List<Question> list = questionService.selectQuestionList(question);
        ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
        util.exportExcel(response, list, "题目管理数据");
    }

    /**
     * 获取题目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('question:question:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(questionService.selectQuestionById(id));
    }

    /**
     * 新增题目管理
     */
    @PreAuthorize("@ss.hasPermi('question:question:add')")
    @Log(title = "题目管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Question question)
    {
        return toAjax(questionService.insertQuestion(question));
    }

    /**
     * 修改题目管理
     */
    @PreAuthorize("@ss.hasPermi('question:question:edit')")
    @Log(title = "题目管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Question question)
    {
        return toAjax(questionService.updateQuestion(question));
    }

    /**
     * 删除题目管理
     */
    @PreAuthorize("@ss.hasPermi('question:question:remove')")
    @Log(title = "题目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(questionService.deleteQuestionByIds(ids));
    }
    @CrossOrigin
    @PostMapping("/uploadExcel")
    public Result<List<Question>> uploadExcel(@RequestParam("file") MultipartFile file)
            throws IOException {
        if(!isExcelFile(file)){
            throw new IllegalArgumentException("Uploaded file is not an Excel file.");
        }
        List<Question> list=new ArrayList<>();
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
                StringBuilder options=new StringBuilder();
                Map<String, String> rowMap = new HashMap<>();
                Question question = new Question();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = (cell != null && !cell.toString().equals("/") ? cell.toString() : "");//处理空单元格
                    rowMap.put(headers.get(j), cellValue);
                    switch (headers.get(j)) {
                        case "题目":
                            question.setContent(cellValue);
                            break;
                        case "分数":
                            question.setScore(Double.valueOf(cellValue));
                            break;
                        case "答案":
                            question.setAnswer(cellValue);
                            break;
                        case "A":
                            options.append("A:").append(cellValue);
                            break;
                        case "B":
                            options.append(";B:").append(cellValue);
                            break;
                        case "C":
                            options.append(";C:").append(cellValue);
                            break;
                    }
                    if (options.length() > 0 && options.charAt(options.length() - 1) == ';') {
                        options.deleteCharAt(options.length() - 1);
                    }
                    question.setOption(options.toString());
                }
                list.add(question);
            }
            for (Question question : list) {

                try {
                    questionService.saveOrUpdate(question);
                } catch (Exception e) {
                    log.error("更新题目失败: " + question.getContent(), e);
                    return Result.error("导入失败: 题目 [" + question.getContent() + "] 插入失败");
                }
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
        for (int i = 1; i < row.getPhysicalNumberOfCells(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // 只要有一个非空单元格，就认为行不为空
            }
        }
        return true;
    }

}
