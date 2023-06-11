package com.SHOP.TaTa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.SHOP.TaTa.common.R;
import com.SHOP.TaTa.entity.Employee;
import com.SHOP.TaTa.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(queryWrapper);
        if(one == null){
            return R.error("登录失败");
        }
        if(!one.getPassword().equals(password)){
            return R.error("登录失败");
        }
        if(one.getStatus() == 0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",one.getId());
        return R.success(one);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    //新增员工
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("用户信息:{}",employee.toString());
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //Long empId = (Long)request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("成功");
    }
    //员工信息分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pagesize={},name={}",page,pageSize,name);
        Page objectPage = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(objectPage,lambdaQueryWrapper);
        return R.success(objectPage);
    }
    //根据id修改员工信息
    @PutMapping
    public R<String> update(HttpServletRequest httpServletRequest,@RequestBody Employee employee){
        log.info(employee.toString());
        long id = Thread.currentThread().getId();
        log.info("线程id为:{}",id);
        //employee.setUpdateTime(LocalDateTime.now());
        //Long employee1 = (Long) httpServletRequest.getSession().getAttribute("employee");
        //employee.setUpdateUser(employee1);
        employeeService.updateById(employee);
        return R.success("用户信息已修改");
    }
    @PutMapping("/password")
    public R<String> updateall(HttpServletRequest httpServletRequest,@RequestBody Employee employee){
        log.info(employee.toString());
        long id = Thread.currentThread().getId();
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        log.info("线程id为:{}",id);
        //employee.setUpdateTime(LocalDateTime.now());
        //Long employee1 = (Long) httpServletRequest.getSession().getAttribute("employee");
        //employee.setUpdateUser(employee1);
        employeeService.updateById(employee);
        return R.success("用户信息已修改");
    }
    //根据id查询员工信息
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("查询用户信息");
        Employee byId = employeeService.getById(id);
        if(byId != null) {
            return R.success(byId);
        }
        return R.error("没有查询到");
    }
}
