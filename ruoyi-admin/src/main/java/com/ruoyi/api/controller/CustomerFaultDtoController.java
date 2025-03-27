package com.ruoyi.api.controller;
import com.ruoyi.api.common.Result;
import com.ruoyi.api.domain.dto.CustomerFaultDto;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.customerFault.service.ICustomerFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fault")
public class CustomerFaultDtoController {
    @Autowired
    private ICustomerFaultService customerFaultService;
    @GetMapping("/list")
    public AjaxResult getFaultList(@RequestParam(required = false) Long customerId){
        if(customerId==null){
            customerId= 1L;
        }
        List<CustomerFaultDto> list=customerFaultService.selectCustomerFaultDtoList(customerId);
        return AjaxResult.success(list);
    }
}
