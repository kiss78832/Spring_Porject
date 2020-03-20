package com.spring.meal.model;

import java.util.List;

public interface MealDAO_interface {

    public void insert(MealVO mealVO);
    public void update(MealVO mealVO);
    public void delete(String meal_id);
    public MealVO findByPrimaryKey(String meal_id);
    public List<MealVO> getAll();
    public void updateNotPic(MealVO mealVO);
    public List<MealVO> getMealsByMeal_status(Integer meal_status);
}
