package com.ruoyi.web.controller.customerFault.controller;

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
import com.ruoyi.web.controller.customerFault.domain.CustomerFault;
import com.ruoyi.web.controller.customerFault.service.ICustomerFaultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户错题表Controller
 * 
 * @author ruoyi
 * @date 2024-12-16
 */
@RestController
@RequestMapping("/customer/fault")
public class CustomerFaultController extends BaseController
{
    @Autowired
    private ICustomerFaultService customerFaultService;

    /**
     * 查询用户错题表列表
     */
    @PreAuthorize("@ss.hasPermi('customer:fault:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerFault customerFault)
    {
        startPage();
        List<CustomerFault> list = customerFaultService.selectCustomerFaultList(customerFault);
        return getDataTable(list);
    }

    /**
     * 导出用户错题表列表
     */
    @PreAuthorize("@ss.hasPermi('customer:fault:export')")
    @Log(title = "用户错题表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerFault customerFault)
    {
        List<CustomerFault> list = customerFaultService.selectCustomerFaultList(customerFault);
        ExcelUtil<CustomerFault> util = new ExcelUtil<CustomerFault>(CustomerFault.class);
        util.exportExcel(response, list, "用户错题表数据");
    }

    /**
     * 获取用户错题表详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:fault:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(customerFaultService.selectCustomerFaultById(id));
    }

    /**
     * 新增用户错题表
     */
    @PreAuthorize("@ss.hasPermi('customer:fault:add')")
    @Log(title = "用户错题表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerFault customerFault)
    {
        return toAjax(customerFaultService.insertCustomerFault(customerFault));
    }

    /**
     * 修改用户错题表
     */
    @PreAuthorize("@ss.hasPermi('customer:fault:edit')")
    @Log(title = "用户错题表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerFault customerFault)
    {
        return toAjax(customerFaultService.updateCustomerFault(customerFault));
    }

    /**
     * 删除用户错题表
     */
    @PreAuthorize("@ss.hasPermi('customer:fault:remove')")
    @Log(title = "用户错题表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(customerFaultService.deleteCustomerFaultByIds(ids));
    }
}
