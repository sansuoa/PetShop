package com.ZHL.TaTa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ZHL.TaTa.entity.Employee;
import com.ZHL.TaTa.mapper.EmployeeMapper;
import com.ZHL.TaTa.service.EmployeeService;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee>
        implements EmployeeService {
}
