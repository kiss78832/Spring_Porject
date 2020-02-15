package com.room_order.model;

import java.util.*;

import com.order_detail.model.Order_DetailVO;

public interface Room_orderDAO_interface {
	
    public void insert(Room_orderVO  room_orderVO);
    public void update(Room_orderVO  room_orderVO);
    public Room_orderVO findByPrimaryKey(String order_id);
    public List<Room_orderVO> getAll();
    public void insertWithoutGroup(Room_orderVO room_orderVO);
    public Room_orderVO getLastestOne(String member_id);
    
    //get int count
    public int getAllByNotPaid();
    public int getAllByAlreadyPaid();
    
    public void updateOrder_status(Room_orderVO resbedVO);
    
    public List<Room_orderVO> getAllByOrderStatus(Integer order_status);
    
    public List<Room_orderVO> getAllByPaymentStatus(Integer payment_status);
    
    public List<String> getAllDifferentMember();
    public List<Room_orderVO> getAllByMember_id(String member_id);
    
    public List<String> getAllDifferentGroup();
    public List<Room_orderVO> getAllByGroup_id(String group_id);
    
    public List<Room_orderVO> getAllByDateRange(Integer date_range);
    
    public int getCountToday();
    
    
    public void insertWithOrders(Room_orderVO room_orderVO , List<Order_DetailVO> list);
   
}
