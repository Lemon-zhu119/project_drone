package com.ruoyi.web.controller.QuestionHistory.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.controller.QuestionHistory.domain.QuestionHistory;
import com.ruoyi.web.controller.QuestionHistory.service.IQuestionHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户顺序练习记录Controller
 * 
 * @author ruoyi
 * @date 2024-12-14
 */
@RestController
@RequestMapping("/question/history")
public class QuestionHistoryController extends BaseController
{
    @Autowired
    private IQuestionHistoryService questionHistoryService;

    /**
     * 查询用户顺序练习记录列表
     */
    @PreAuthorize("@ss.hasPermi('question:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(QuestionHistory questionHistory)
    {
        startPage();
        List<QuestionHistory> list = questionHistoryService.selectQuestionHistoryList(questionHistory);
        return getDataTable(list);
    }

    /**
     * 导出用户顺序练习记录列表
     */
    @PreAuthorize("@ss.hasPermi('question:history:export')")
    @Log(title = "用户顺序练习记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QuestionHistory questionHistory)
    {
        List<QuestionHistory> list = questionHistoryService.selectQuestionHistoryList(questionHistory);
        ExcelUtil<QuestionHistory> util = new ExcelUtil<QuestionHistory>(QuestionHistory.class);
        util.exportExcel(response, list, "用户顺序练习记录数据");
    }

    /**
     * 获取用户顺序练习记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('question:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(questionHistoryService.selectQuestionHistoryById(id));
    }

    /**
     * 新增用户顺序练习记录
     */
    @PreAuthorize("@ss.hasPermi('question:history:add')")
    @Log(title = "用户顺序练习记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QuestionHistory questionHistory)
    {
        return toAjax(questionHistoryService.insertQuestionHistory(questionHistory));
    }

    /**
     * 修改用户顺序练习记录
     */
    @PreAuthorize("@ss.hasPermi('question:history:edit')")
    @Log(title = "用户顺序练习记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QuestionHistory questionHistory)
    {
        return toAjax(questionHistoryService.updateQuestionHistory(questionHistory));
    }

    /**
     * 删除用户顺序练习记录
     */
    @PreAuthorize("@ss.hasPermi('question:history:remove')")
    @Log(title = "用户顺序练习记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(questionHistoryService.deleteQuestionHistoryByIds(ids));
    }
}
