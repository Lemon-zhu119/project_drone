package com.ruoyi.web.controller.ExamPaper.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.controller.ExamPaper.domain.ExamPaperType;
import com.ruoyi.web.controller.question.domain.Question;
import com.ruoyi.web.controller.question.service.IQuestionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.ExamPaper.domain.ExamPaper;
import com.ruoyi.web.controller.ExamPaper.service.IExamPaperService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考卷管理Controller
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
@RestController
@RequestMapping("/ExamPaper/paper")
public class ExamPaperController extends BaseController
{
    @Autowired
    private IExamPaperService examPaperService;

    /**
     * 查询考卷管理列表
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paper:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExamPaper examPaper)
    {
        startPage();
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        return getDataTable(list);
    }

    /**
     * 导出考卷管理列表
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paper:export')")
    @Log(title = "考卷管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExamPaper examPaper)
    {
        List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        ExcelUtil<ExamPaper> util = new ExcelUtil<ExamPaper>(ExamPaper.class);
        util.exportExcel(response, list, "考卷管理数据");
    }

    /**
     * 获取考卷管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(examPaperService.selectExamPaperById(id));
    }

    /**
     * 新增考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paper:add')")
    @Log(title = "考卷管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExamPaper examPaper)
    {
        return toAjax(examPaperService.insertExamPaper(examPaper));
    }

    /**
     * 修改考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paper:edit')")
    @Log(title = "考卷管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExamPaper examPaper)
    {
        return toAjax(examPaperService.updateExamPaper(examPaper));
    }

    /**
     * 删除考卷管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paper:remove')")
    @Log(title = "考卷管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(examPaperService.deleteExamPaperByIds(ids));
    }
    /**
     * 随机生成试题管理
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paperDetail:insert')")
    @Log(title = "考卷随机生成管理", businessType = BusinessType.INSERT)
    @PostMapping("/randomGenerate")
    @ResponseBody
    public AjaxResult randomGenerate(@RequestBody ExamPaperType examPaperType)
    {
        return toAjax(examPaperService.insertRandomGenerate(examPaperType));
    }

    /**
     * 获取试卷关联的题目列表
     * @param question
     * @return
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paperDetail:list')")
    @GetMapping("/detailList")
    public TableDataInfo list(Question question,Long examPaperId)
    {
        startPage();
        List<Question> list = examPaperService.selectExamPaperDetailList(question,examPaperId);
        return getDataTable(list);
    }

    /**
     * 获取试卷关联的题目列表
     * @param question
     * @param examPaperId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('ExamPaper:paperDetail:list')")
    @GetMapping("/remainQuestionList")
    public TableDataInfo remainQuestionList(Question question,Long examPaperId)
    {
        startPage();
        List<Question> list = examPaperService.selectExamPaperRemainDetailList(question,examPaperId);
        return getDataTable(list);
    }
}
