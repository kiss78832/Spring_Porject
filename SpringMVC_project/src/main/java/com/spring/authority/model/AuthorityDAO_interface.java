package com.spring.authority.model;

import java.util.List;


public interface AuthorityDAO_interface {
    public void insert(AuthorityVO AVO);
    public void update(AuthorityVO AVO);
    public void delete(String sf_id);
    public List<AuthorityVO> findByPrimaryKey(String sf_id);
    public List<AuthorityVO> getAll();
}