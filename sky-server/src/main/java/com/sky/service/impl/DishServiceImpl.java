package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.service.DishFlavorService;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @Override
    @Transactional
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insertDish(dish);
        Long dishId = dish.getId();
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        // 当dishFlavors非空时将dishFlavors中的多条数据一一插入dish_flavors
        if (dishFlavors.size()>0 && dishFlavors != null){
            dishFlavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            dishFlavorService.addDishFlavor(dishFlavors);
        }
    }


    @Override
    public List<Dish> queryDishPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("dish:{}",dishPageQueryDTO);
        return dishMapper.selectDishPage(dishPageQueryDTO.getName(), dishPageQueryDTO.getCategoryId(), dishPageQueryDTO.getStatus(),
                dishPageQueryDTO.getPage()-1, dishPageQueryDTO.getPageSize());
    }
}
