package com.meal.model;

import java.util.Base64;
import java.util.List;
import java.util.Set;

public class MealService {

	private MealDAO_interface dao;

	public MealService() {
		dao = new MealDAO();
	}

	public MealVO addMeal(String meal_id, String meal_name, Integer meal_price, Integer meal_status, String meal_content,
			byte[] meal_pic) {
		
		MealVO mealVO = new MealVO();
		
		mealVO.setMeal_id(meal_id);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_price(meal_price);
		mealVO.setMeal_status(meal_status);
		mealVO.setMeal_content(meal_content);
		mealVO.setMeal_pic(meal_pic);
		dao.insert(mealVO);

		return mealVO;
	}
	
	public MealVO updateMeal(String meal_id, String meal_name, Integer meal_price, Integer meal_status, String meal_content,
			byte[] meal_pic) {
		
		MealVO mealVO = new MealVO();
		mealVO.setMeal_id(meal_id);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_price(meal_price);
		mealVO.setMeal_status(meal_status);
		mealVO.setMeal_content(meal_content);
		mealVO.setMeal_pic(meal_pic);
		
		dao.update(mealVO);
		
		return mealVO;
	}
	
	public MealVO updateMealNotPic(String meal_id, String meal_name, Integer meal_price, Integer meal_status, String meal_content
			) {
		
		MealVO mealVO = new MealVO();
		mealVO.setMeal_id(meal_id);
		mealVO.setMeal_name(meal_name);
		mealVO.setMeal_price(meal_price);
		mealVO.setMeal_status(meal_status);
		mealVO.setMeal_content(meal_content);
		
		dao.updateNotPic(mealVO);
		
		return mealVO;
	}
	
	public List<MealVO> getAll() {
		return dao.getAll();
	}

	public MealVO getOneMeal(String meal_id) {
		return dao.findByPrimaryKey(meal_id);
	}
	
	public void deleteMeal(String meal_id) {
		dao.delete(meal_id);
	}
	 
	public String Base64Img(byte[] byteArray) {
		String encodeBase64 = Base64.getEncoder().encodeToString(byteArray);
			return encodeBase64;
	}
	
	public List<MealVO> getMealsByMeal_status(Integer meal_status){
		return dao.getMealsByMeal_status(meal_status);
	}
	
	public static void main(String[]args) {
		MealService mealSvc = new MealService();
		List<MealVO> list = mealSvc.getMealsByMeal_status(1);
		System.out.println(list.size());
	}
	
}
