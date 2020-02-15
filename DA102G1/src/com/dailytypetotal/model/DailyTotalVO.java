package com.dailytypetotal.model;

import java.sql.Date;

public class DailyTotalVO implements java.io.Serializable {
	private String dailyeq_num;
	private String type_eq_num;
	private Date eq_date;
	private Integer daily_eq_qty;
	private Integer start_qty;

	public String getDailyeq_num() {
		return dailyeq_num;
	}

	public void setDailyeq_num(String dailyeq_num) {
		this.dailyeq_num = dailyeq_num;
	}

	public String getType_eq_num() {
		return type_eq_num;
	}

	public void setType_eq_num(String type_eq_num) {
		this.type_eq_num = type_eq_num;
	}

	public Date getEq_date() {
		return eq_date;
	}

	public void setEq_date(Date eq_date) {
		this.eq_date = eq_date;
	}

	public Integer getDaily_eq_qty() {
		return daily_eq_qty;
	}

	public void setDaily_eq_qty(Integer daily_eq_qty) {
		this.daily_eq_qty = daily_eq_qty;
	}

	public Integer getStart_qty() {
		return start_qty;
	}

	public void setStart_qty(Integer start_qty) {
		this.start_qty = start_qty;
	}

}
