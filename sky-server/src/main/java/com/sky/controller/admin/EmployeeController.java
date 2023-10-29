package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import com.sky.vo.EmployeesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     *
     *
     */
    @PostMapping
    @ApiOperation("新增员工")
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工:{}",employeeDTO);
        int result = employeeService.addEmployee(employeeDTO);
        if(result == 0){
            return Result.error("插入失败");
        }
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询员工信息")
    public Result<EmployeesVO> queryEmployeePage(EmployeePageQueryDTO employeePageQueryDTO){
        System.out.println(employeePageQueryDTO);
        EmployeesVO employeesVO = new EmployeesVO();
        List<Employee> records = employeeService.queryEmoloyeeByPage(employeePageQueryDTO);
        System.out.println(records);
        employeesVO.setRecords(records);
        employeesVO.setTotal(records.size());
        return Result.success(employeesVO);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result updateEmployeeStatus(Integer id, @PathVariable("status")Integer status){
        int result = employeeService.updateStatusById(id,status);
        if(result == 0){
            return Result.error("修改失败");
        }
        return Result.success();
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工信息")
    public Result<Employee> queryEmoloyeeById(@PathVariable("id")Integer id){
        return Result.success(employeeService.queryEmployeeById(id));
    }

    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result updateEmployeeById(@RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployeeById(employeeDTO);
        return Result.success();
    }
}
