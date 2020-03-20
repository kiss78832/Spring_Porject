package com.spring.function.model;

import java.util.List;

public interface FunctionDAO_interface {
    public void insert(FunctionVO FVO);
    public void update(FunctionVO FVO);
    public void delete(String F_num);
    public FunctionVO findByPrimaryKey(String F_num);
    public List<FunctionVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<FunctionVO> getAll(Map<String, String[]> map); 

}
