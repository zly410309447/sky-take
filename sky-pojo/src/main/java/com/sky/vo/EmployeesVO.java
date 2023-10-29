package com.sky.vo;

import com.sky.entity.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页查询员工返回的数据格式")
public class EmployeesVO {
    @ApiModelProperty("查询总数")
    private Integer total;

    @ApiModelProperty("查询的数据")
    private List<Employee> records;
}
