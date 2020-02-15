package com.room_order.model;

import java.util.List;

import com.order_detail.model.Order_DetailVO;

public class Room_orderService {

	private Room_orderDAO_interface dao;

	public Room_orderService() {
		dao = new Room_orderDAO();
	}

	public Room_orderVO addRoom_order(String member_id, String group_id, Integer order_price, Integer order_status,
			Integer payment_status, Integer booking_day) {

		Room_orderVO room_orderVO = new Room_orderVO();

		room_orderVO.setMember_id(member_id);
		room_orderVO.setGroup_id(group_id);
		room_orderVO.setOrder_price(order_price);
		room_orderVO.setOrder_status(order_status);
		room_orderVO.setPayment_status(payment_status);
		room_orderVO.setBooking_day(booking_day);
		dao.insert(room_orderVO);

		return room_orderVO;
	}
	
	
	public Room_orderVO addWithoutGroup(String member_id,  Integer order_price, Integer order_status,
			Integer payment_status, Integer booking_day) {

		Room_orderVO room_orderVO = new Room_orderVO();

		room_orderVO.setMember_id(member_id);
		room_orderVO.setOrder_price(order_price);
		room_orderVO.setOrder_status(order_status);
		room_orderVO.setPayment_status(payment_status);
		room_orderVO.setBooking_day(booking_day);
		dao.insertWithoutGroup(room_orderVO);

		return room_orderVO;
	}
	
	
	

	public Room_orderVO updateRoom_order(String member_id, String group_id, Integer order_price, Integer order_status,
			Integer payment_status, Integer booking_day) {
		
		Room_orderVO room_orderVO = new Room_orderVO();

		room_orderVO.setMember_id(member_id);
		room_orderVO.setGroup_id(group_id);
		room_orderVO.setOrder_price(order_price);
		room_orderVO.setOrder_status(order_status);
		room_orderVO.setPayment_status(payment_status);
		room_orderVO.setBooking_day(booking_day);
		dao.update(room_orderVO);

		return room_orderVO;

	}
	
	public List<Room_orderVO>getAll(){
		return dao.getAll();
	}
	
	public Room_orderVO getOneRoom_Order(String order_id){
		return dao.findByPrimaryKey(order_id);
	}
	
	public Room_orderVO getLastestOne(String member_id) {
		return dao.getLastestOne(member_id);
	}
	
	public int getAllByNotPaid() {	
		return dao.getAllByNotPaid();
	}
	
	public int getAllByAlreadyPaid() {
		return dao.getAllByAlreadyPaid();
	}

	
	public void updateOrder_status(Integer order_status,Integer payment_status,String order_id) {
		Room_orderVO room_orderVO = new Room_orderVO();
		
		room_orderVO.setOrder_status(order_status);
		room_orderVO.setPayment_status(payment_status);
		room_orderVO.setOrder_id(order_id);
		dao.updateOrder_status(room_orderVO);
		
		
	}
	
	public List<Room_orderVO> getAllByOrderStatus(Integer order_status){
		return dao.getAllByOrderStatus(order_status);
	}
	
	public List<Room_orderVO> getAllByPaymentStatus(Integer payment_status){
		return dao.getAllByPaymentStatus(payment_status);
	}
	
	public List<String> getAllDifferentMember(){
		return dao.getAllDifferentMember();
	}
	
	public List<Room_orderVO> getAllByMember_id(String member_id) {
		return dao.getAllByMember_id(member_id);
	}
	
	public List<String> getAllDifferentGroup(){
		return dao.getAllDifferentGroup();
	}
	
	public List<Room_orderVO> getAllByGroup_id(String group_id){
		return dao.getAllByGroup_id(group_id);
	}
	
	public List<Room_orderVO> getAllByDateRange(Integer date_range){
		return dao.getAllByDateRange(date_range);
	}
	
	public int getCountToday() {
		return dao.getCountToday();
	}
	
	public void insertWithOrders(Room_orderVO room_orderVO , List<Order_DetailVO> list) {
		 dao.insertWithOrders(room_orderVO, list);
	}
	
	
	public static void main(String[]args) {
		Room_orderService A1 = new Room_orderService();
		int count = A1.getAllByAlreadyPaid();
		System.out.println(count);
		A1.updateOrder_status(4, 2,"O000000015");
		System.out.println("^__^");
		
	}
}
