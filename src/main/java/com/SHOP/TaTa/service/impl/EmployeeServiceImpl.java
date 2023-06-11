package com.SHOP.TaTa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.SHOP.TaTa.entity.Employee;
import com.SHOP.TaTa.mapper.EmployeeMapper;
import com.SHOP.TaTa.service.EmployeeService;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee>
        implements EmployeeService {
}
