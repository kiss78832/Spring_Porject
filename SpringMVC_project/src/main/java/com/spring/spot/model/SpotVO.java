package com.spring.spot.model;

public class SpotVO implements java.io.Serializable {
	private String 	spot_id;			//景點名稱
	private String	spot_name;			//景點敘述
	private String 	spot_intro;			//景點敘述CLOB
	private byte[] 	spot_pic;			//景點圖片BLOB
	private Double 	spot_lng;			//經度
	private Double 	spot_lat;			//緯度
	private Integer spot_point;			//攻略點數
	private Integer spot_cabinpoint;	//住宿點
	private Integer spot_status;		//景點狀態
	private Integer auth_spot;			//需申請景點
	private String 	main_road;			//路線編號

	public SpotVO() {
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public String getSpot_name() {
		return spot_name;
	}

	public void setSpot_name(String spot_name) {
		this.spot_name = spot_name;
	}

	public String getSpot_intro() {
		return spot_intro;
	}

	public void setSpot_intro(String string) {
		this.spot_intro = string;
	}

	public byte[] getSpot_pic() {
		return spot_pic;
	}

	public void setSpot_pic(byte[] spot_pic) {
		this.spot_pic = spot_pic;
	}

	public Double getSpot_lng() {
		return spot_lng;
	}

	public void setSpot_lng(Double spot_lng) {
		this.spot_lng = spot_lng;
	}

	public Double getSpot_lat() {
		return spot_lat;
	}

	public void setSpot_lat(Double spot_lat) {
		this.spot_lat = spot_lat;
	}

	public Integer getSpot_point() {
		return spot_point;
	}

	public void setSpot_point(Integer spot_point) {
		this.spot_point = spot_point;
	}

	public Integer getSpot_cabinpoint() {
		return spot_cabinpoint;
	}

	public void setSpot_cabinpoint(Integer spot_cabinpoint) {
		this.spot_cabinpoint = spot_cabinpoint;
	}

	public Integer getSpot_status() {
		return spot_status;
	}

	public void setSpot_status(Integer spot_status) {
		this.spot_status = spot_status;
	}

	public Integer getAuth_spot() {
		return auth_spot;
	}

	public void setAuth_spot(Integer auth_spot) {
		this.auth_spot = auth_spot;
	}

	public String getMain_road() {
		return main_road;
	}

	public void setMain_road(String main_road) {
		this.main_road = main_road;
	};

	

}