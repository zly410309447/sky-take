package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 新增菜品
     */
    void addDish(DishDTO dishDTO);


    List<Dish> queryDishPage(DishPageQueryDTO dishPageQueryDTO);
}
