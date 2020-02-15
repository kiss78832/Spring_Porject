package com.location.model;

import java.util.*;

public interface LocationDAO_interface {

    public void insert(LocationVO locationVO);
    public void update(LocationVO locationVO);
    public void delete(String location_id);
    public LocationVO findByPrimaryKey(String location_id);
    public List<LocationVO> getAll();
    public void updateNotPic(LocationVO locationVO);
    public List<LocationVO> getLocsByLoc_Type(Integer loc_type);
    public List<LocationVO> getLocsByLoc_Status(Integer location_status);
    
    /*2019-09-23*/
    public LocationVO findLocationName(String location_name);
}
