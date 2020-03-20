package com.spring.dailyBed.model;

import java.sql.Date;
import java.util.*;

public interface DailyBedDAO_interface {

	
    public void insert(DailyBedVO bedMgeVO);
    public void update(DailyBedVO bedMgeVO);
    public DailyBedVO findByPrimaryKey(String dailyBed_id );
    public List<DailyBedVO> getAll();
    public List<DailyBedVO> getAllByLoc_id(String location_id);
    public List<DailyBedVO> getAllByDailyBed_date(Date dailyBed_date);
    public List<DailyBedVO>getAllByLoc_idAndDate(String location_id ,  int year , int month );
    public List<DailyBedVO> getAllByDate( int year , int month , int date);
    public DailyBedVO getLocVOByDate( int year , int month , int date ,String location_id);
    public DailyBedVO getDailyBedVOByFullDate(String location_id , Date date);

    public List<String> getAllDifferentLoc();
}
