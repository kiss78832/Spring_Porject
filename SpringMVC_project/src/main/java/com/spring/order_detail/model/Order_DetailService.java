package com.spring.order_detail.model;

import java.sql.Date;
import java.util.List;

public class Order_DetailService {

	private Order_DetailDAO_interface dao;

	public Order_DetailService() {
		dao = new Order_DetailDAO();
	}

	public Order_DetailVO addOrder_Detail(String order_id, String location_id, Integer bed_num, Integer bedTotal_price,
			Date checkin_date, String meal_id, Integer meal_quantity, Integer mealTotal_price) {

		Order_DetailVO order_DetailVO = new Order_DetailVO();

		order_DetailVO.setOrder_id(order_id);
		order_DetailVO.setLocation_id(location_id);
		order_DetailVO.setBed_num(bed_num);
		order_DetailVO.setBedTotal_price(bedTotal_price);
		order_DetailVO.setCheckin_date(checkin_date);
		order_DetailVO.setMeal_id(meal_id);
		order_DetailVO.setMeal_quantity(meal_quantity);
		order_DetailVO.setMealTotal_price(mealTotal_price);

		dao.insert(order_DetailVO);

		return order_DetailVO;
	}
	
	
	public Order_DetailVO addWithoutMeal(String order_id, String location_id, Integer bed_num, Integer bedTotal_price,
			Date checkin_date) {

		Order_DetailVO order_DetailVO = new Order_DetailVO();

		order_DetailVO.setOrder_id(order_id);
		order_DetailVO.setLocation_id(location_id);
		order_DetailVO.setBed_num(bed_num);
		order_DetailVO.setBedTotal_price(bedTotal_price);
		order_DetailVO.setCheckin_date(checkin_date);

		dao.insertWithoutMeal(order_DetailVO);

		return order_DetailVO;
	}
	
	

	public Order_DetailVO update(String order_id, String location_id, Integer bed_num, Integer bedTotal_price,
			Date checkin_date, String meal_id, Integer meal_quantity, Integer mealTotal_price) {
		
		Order_DetailVO order_DetailVO = new Order_DetailVO();

		order_DetailVO.setOrder_id(order_id);
		order_DetailVO.setLocation_id(location_id);
		order_DetailVO.setBed_num(bed_num);
		order_DetailVO.setBedTotal_price(bedTotal_price);
		order_DetailVO.setCheckin_date(checkin_date);
		order_DetailVO.getMeal_id();
		order_DetailVO.setMeal_quantity(meal_quantity);
		order_DetailVO.getMealTotal_price();

		dao.insert(order_DetailVO);

		return order_DetailVO;

	}

	
	public List<Order_DetailVO> getAll(){
		return dao.getAll();
	}
	
	public Order_DetailVO getOneOrder_Detail(String detail_id) {
		return dao.findByPrimaryKey(detail_id);
		
	}
	
	public void deleteOrder_Detail(String detail_id) {
		dao.delete(detail_id);
	}
	
	public List<Order_DetailVO> getDetailsByOrder_id(String order_id){
		return dao.getDetailsByOrder_id(order_id);
	}
}
