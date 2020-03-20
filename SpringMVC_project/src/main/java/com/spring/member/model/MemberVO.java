package com.spring.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable {
	private String member_id;
	private String password;
	private String m_name;
	private Integer gender;
	private Date birthday;
	private String cellphone;
	private String m_email;
	private byte[] m_photo;
	private Integer validation;
	private Date registered;
	private Integer adventure_point;
	private String outdoor_exp;
	private byte[] back_img;
	private String nick_name;
	private Integer raiders_rate;
	
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public byte[] getM_photo() {
		return m_photo;
	}
	public void setM_photo(byte[] m_photo) {
		this.m_photo = m_photo;
	}
	public Integer getValidation() {
		return validation;
	}
	public void setValidation(Integer validation) {
		this.validation = validation;
	}
	public Date getRegistered() {
		return registered;
	}
	public void setRegistered(Date registered) {
		this.registered = registered;
	}
	public Integer getAdventure_point() {
		return adventure_point;
	}
	public void setAdventure_point(Integer adventure_point) {
		this.adventure_point = adventure_point;
	}
	public String getOutdoor_exp() {
		return outdoor_exp;
	}
	public void setOutdoor_exp(String outdoor_exp) {
		this.outdoor_exp = outdoor_exp;
	}
	public byte[] getBack_img() {
		return back_img;
	}
	public void setBack_img(byte[] back_img) {
		this.back_img = back_img;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public Integer getRaiders_rate() {
		return raiders_rate;
	}
	public void setRaiders_rate(Integer raiders_rate) {
		this.raiders_rate = raiders_rate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		return true;
	}
	
	
}
