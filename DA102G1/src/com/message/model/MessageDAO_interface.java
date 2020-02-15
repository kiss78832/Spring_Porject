package com.message.model;

import java.util.*;

public interface MessageDAO_interface {

	public void insert(MessageVO msgVO);
    public void update(MessageVO msgVO);
    public void delete(String message_num);
    public MessageVO findByPrimaryKey(String message_num);
    public List<MessageVO> getAll();
    /*自訂方法*/
    public List<MessageVO> getMsg(String article_id );
}
