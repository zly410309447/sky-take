package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param  employeeDTO
     * @return
     */
    int addEmployee(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工信息
     */
    List<Employee> queryEmoloyeeByPage(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改启用禁用账号状态
     */
    int updateStatusById(Integer id,Integer status);

    Employee queryEmployeeById(Integer id);

    int updateEmployeeById(EmployeeDTO employeeDTO);
}
