package com.ruoyi.web.controller.ExamPaperDetail.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.ExamPaperDetail.domain.ExamPaperDetail;
import com.ruoyi.web.controller.ExamPaperDetail.service.IExamPaperDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 考卷管理Controller
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
@RestController
@RequestMapping("/ExamPaperDetail/paper")
public class ExamPaperDetailController extends BaseController
{
    @Autowired
    private IExamPaperDetailService examPaperDetailService;

    /**
     * 查询考卷管理列表
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamPaperDetail examPaperDetail)
    {
        startPage();
        List<ExamPaperDetail> list = examPaperDetailService.selectExamPaperDetailList(examPaperDetail);
        return getDataTable(list);
    }

    /**
     * 导出考卷管理列表
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:export')")
    @Log(title = "考卷管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamPaperDetail examPaperDetail)
    {
        List<ExamPaperDetail> list = examPaperDetailService.selectExamPaperDetailList(examPaperDetail);
        ExcelUtil<ExamPaperDetail> util = new ExcelUtil<ExamPaperDetail>(ExamPaperDetail.class);
        util.exportExcel(response, list, "考卷管理数据");
    }

    /**
     * 获取考卷管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(examPaperDetailService.selectExamPaperDetailById(id));
    }

    /**
     * 新增考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:add')")
    @Log(title = "考卷管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamPaperDetail examPaperDetail)
    {
        return toAjax(examPaperDetailService.insertExamPaperDetail(examPaperDetail));
    }

    /**
     * 修改考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:edit')")
    @Log(title = "考卷管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamPaperDetail examPaperDetail)
    {
        return toAjax(examPaperDetailService.updateExamPaperDetail(examPaperDetail));
    }

    /**
     * 删除考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:remove')")
    @Log(title = "考卷管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examPaperDetailService.deleteExamPaperDetailByIds(ids));
    }
    /**
     * 删除考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaperDetail:paper:remove')")
    @Log(title = "考卷管理", businessType = BusinessType.DELETE)
    @PostMapping("/delPaperDetailByDetail")
    @ResponseBody
    public AjaxResult removeDetail(@RequestBody ExamPaperDetail examPaperDetail)
    {
        return toAjax(examPaperDetailService.deleteExamPaperDetail(examPaperDetail));
    }
}
