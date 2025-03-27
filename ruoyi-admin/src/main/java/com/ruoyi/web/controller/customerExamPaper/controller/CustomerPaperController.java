package com.ruoyi.web.controller.customerExamPaper.controller;

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
import com.ruoyi.web.controller.customerExamPaper.domain.CustomerPaper;
import com.ruoyi.web.controller.customerExamPaper.service.ICustomerPaperService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户考卷记录Controller
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
@RestController
@RequestMapping("/customer/exampaper")
public class CustomerPaperController extends BaseController
{
    @Autowired
    private ICustomerPaperService customerPaperService;

    /**
     * 查询用户考卷记录列表
     */
    @PreAuthorize("@ss.hasPermi('customer:exampaper:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerPaper customerPaper)
    {
        startPage();
        List<CustomerPaper> list = customerPaperService.selectCustomerPaperList(customerPaper);
        return getDataTable(list);
    }

    /**
     * 导出用户考卷记录列表
     */
    @PreAuthorize("@ss.hasPermi('customer:exampaper:export')")
    @Log(title = "用户考卷记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerPaper customerPaper)
    {
        List<CustomerPaper> list = customerPaperService.selectCustomerPaperList(customerPaper);
        ExcelUtil<CustomerPaper> util = new ExcelUtil<CustomerPaper>(CustomerPaper.class);
        util.exportExcel(response, list, "用户考卷记录数据");
    }

    /**
     * 获取用户考卷记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:exampaper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(customerPaperService.selectCustomerPaperById(id));
    }

    /**
     * 新增用户考卷记录
     */
    @PreAuthorize("@ss.hasPermi('customer:exampaper:add')")
    @Log(title = "用户考卷记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerPaper customerPaper)
    {
        return toAjax(customerPaperService.insertCustomerPaper(customerPaper));
    }

    /**
     * 修改用户考卷记录
     */
    @PreAuthorize("@ss.hasPermi('customer:exampaper:edit')")
    @Log(title = "用户考卷记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerPaper customerPaper)
    {
        return toAjax(customerPaperService.updateCustomerPaper(customerPaper));
    }

    /**
     * 删除用户考卷记录
     */
    @PreAuthorize("@ss.hasPermi('customer:exampaper:remove')")
    @Log(title = "用户考卷记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(customerPaperService.deleteCustomerPaperByIds(ids));
    }
}
