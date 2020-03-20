package com.spring.staff.model;

import java.util.List;

public interface StaffDAO_interface {
    public void insert(StaffVO SVO);
    public void update(StaffVO SVO);
    public void delete(String SID);
    public StaffVO findByPrimaryKey(String SID);
    public List<StaffVO> getAll();
    
    
    public StaffVO findByAccount(String account);
    public boolean compare(String sf_account);
}
