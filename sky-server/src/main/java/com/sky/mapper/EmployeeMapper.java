package com.sky.mapper;

import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    int insertEmployee(Employee employee);

    List<Employee> getEmployeePage(String name, Integer page, Integer pageSize);

    @Update("update employee set status = #{status} where id = #{id}")
    int updateStatusById(Integer id,Integer status);

    @Select("select * from employee where id = #{id}")
    Employee getById(Integer id);

    @Update("update employee set name = #{name},username=#{username},phone=#{phone},sex=#{sex},id_number=#{idNumber} where id = #{id}")
    int updateEmployee(Employee employee);
}
