package com.spring.order_detail.model;

import java.sql.Connection;
import java.util.List;

public interface Order_DetailDAO_interface {

    public void insert(Order_DetailVO order_DetailVO);
    public void update(Order_DetailVO order_DetailVO);
    public void delete(String detail_id);
    public Order_DetailVO findByPrimaryKey(String detail_id);
    public List<Order_DetailVO> getAll();
    public void insertWithoutMeal(Order_DetailVO order_DetailVO);
    public List<Order_DetailVO> getDetailsByOrder_id(String order_id);
    
    public void insertWithMeal(Order_DetailVO order_DetailVO , Connection con);
    public void insertWithoutMeal(Order_DetailVO order_detailVO , Connection con);
}
