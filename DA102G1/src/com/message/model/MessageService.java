package com.message.model;

import java.util.List;


public class MessageService {
	
	private MessageDAO_interface dao;

	public MessageService() {
		dao = new MessageJNDI();
	}

	public MessageVO addMsg(String member_id, String article_id, String msg_content
			) {

		MessageVO msgVO = new MessageVO();
		msgVO.setMember_id(member_id);
		msgVO.setArticle_id(article_id);
		msgVO.setMsg_content(msg_content);
		dao.insert(msgVO);
		
		return msgVO;
	}

	public MessageVO updateMsg(String message_id,String member_id, String article_id, String msg_content){

		MessageVO msgVO = new MessageVO();
		
		msgVO.setMessage_id(message_id);
		msgVO.setMember_id(member_id);
		msgVO.setArticle_id(article_id);
		msgVO.setMsg_content(msg_content);
		dao.update(msgVO);
		
		return msgVO;
	}

	public void deleteEmp(String message_id) {
		dao.delete(message_id);
	}

	public MessageVO getOneEmp(String message_id) {
		return dao.findByPrimaryKey(message_id);
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}
	
	public List<MessageVO> getOne_Msg(String article_id ) {
		return dao.getMsg(article_id );
	}
	
	public static void main(String[] args) {

		MessageService  dao = new MessageService();	
//		dao.addMsg("A001","AR000001","快做完了爽");
		dao.updateMsg("ME000032","A001","AR000001","快做完了爽爽爽爽爽爽");
	}
		
}
