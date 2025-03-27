package com.ruoyi.web.controller.customerfavor.controller;

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
import com.ruoyi.web.controller.customerfavor.domain.CustomerFavor;
import com.ruoyi.web.controller.customerfavor.service.ICustomerFavorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户收藏Controller
 * 
 * @author ruoyi
 * @date 2024-12-19
 */
@RestController
@RequestMapping("/customer/favor")
public class CustomerFavorController extends BaseController
{
    @Autowired
    private ICustomerFavorService customerFavorService;

    /**
     * 查询用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('customer:favor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerFavor customerFavor)
    {
        startPage();
        List<CustomerFavor> list = customerFavorService.selectCustomerFavorList(customerFavor);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('customer:favor:export')")
    @Log(title = "用户收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerFavor customerFavor)
    {
        List<CustomerFavor> list = customerFavorService.selectCustomerFavorList(customerFavor);
        ExcelUtil<CustomerFavor> util = new ExcelUtil<CustomerFavor>(CustomerFavor.class);
        util.exportExcel(response, list, "用户收藏数据");
    }

    /**
     * 获取用户收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:favor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(customerFavorService.selectCustomerFavorById(id));
    }

    /**
     * 新增用户收藏
     */
    @PreAuthorize("@ss.hasPermi('customer:favor:add')")
    @Log(title = "用户收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerFavor customerFavor)
    {
        return toAjax(customerFavorService.insertCustomerFavor(customerFavor));
    }

    /**
     * 修改用户收藏
     */
    @PreAuthorize("@ss.hasPermi('customer:favor:edit')")
    @Log(title = "用户收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerFavor customerFavor)
    {
        return toAjax(customerFavorService.updateCustomerFavor(customerFavor));
    }

    /**
     * 删除用户收藏
     */
    @PreAuthorize("@ss.hasPermi('customer:favor:remove')")
    @Log(title = "用户收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(customerFavorService.deleteCustomerFavorByIds(ids));
    }
}
