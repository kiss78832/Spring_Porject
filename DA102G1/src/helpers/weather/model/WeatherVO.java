package helpers.weather.model;

public class WeatherVO implements java.io.Serializable {
	private String time;
	private String title;
	private String wdiscrip;
	private String picSrc;
	private String temperature;
	private String rain;
	private String sunrise;
	private String sunset;
	
	public WeatherVO() {
		
	}
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWdiscrip() {
		return wdiscrip;
	}
	public void setWdiscrip(String wdiscrip) {
		this.wdiscrip = wdiscrip;
	}
	public String getPicSrc() {
		return picSrc;
	}
	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	
	
	
	
	
}
