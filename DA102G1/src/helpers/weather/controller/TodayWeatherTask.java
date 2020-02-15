package helpers.weather.controller;


import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import helpers.weather.model.WeatherVO;

public class TodayWeatherTask extends TimerTask {

	//塔塔加
	static final String URL = "https://www.cwb.gov.tw/V8/C/L/NatPark/NatPark.html?QS=&PID=E013";
	private ServletContext context = null; 

	  public TodayWeatherTask() { 
	  } 
	  public TodayWeatherTask(ServletContext context) { 
	    this.context = context; 
	  } 

	  public void run() { 
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920,1080");
			options.addArguments("-js-flags=--expose-gc");
			WebDriver driver = new ChromeDriver(options);

			
			try {
				driver.get(URL);
				Thread.sleep(100);
				
				
				int k = 8;
				for(int i = 1;i<=5 ;i++) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					//get time
					List<WebElement> row = driver.findElements(By.xpath("//span[@id='GT_Time']"));
					WebElement firstRow0 = row.get(0);
					String time = firstRow0.getText();
					System.out.println(time);
					
					//get title
					List<WebElement> rows = driver.findElements(By.xpath("//span[@class='TypeName']"));
					WebElement firstRow = rows.get(0);
					String ORITitle = firstRow.getText();
					String title = ORITitle.substring(7);
					System.out.println(title);
					

					//get wdiscrip
					List<WebElement> row1 = driver.findElements(By.xpath("//div[@class='table-responsive three-hr-forecast']/table/tbody/tr[1]/td[1]/span/img"));
					
					WebElement firstRow1 = row1.get(0);
					
					String picSrc = firstRow1.getAttribute("src");
					String wdiscrip = firstRow1.getAttribute("title");
					
					System.out.println("scr = "+picSrc);
					System.out.println("title = "+wdiscrip);
					
					
					//get temp
					List<WebElement> row2 = driver.findElements(By.xpath("//span[@id='GT_C_T']"));
					WebElement firstRow2 = row2.get(0);
					String temp = firstRow2.getText();
					
					System.out.println("氣溫 = "+temp);
					
					//get rain rate
					List<WebElement> row3 = driver.findElements(By.xpath("//div[@class='table-responsive three-hr-forecast']/table/tbody/tr[5]/td[1]"));
					WebElement firstRow3 = row3.get(0);
					String rain = firstRow3.getText();
					
					System.out.println("rain rate = "+rain);
					
					
					//get sunrise
					List<WebElement> row4 = driver.findElements(By.xpath("//span[@id='GT_Sunrise']"));
					WebElement firstRow4 = row4.get(0);
					String sunrise = firstRow4.getText();
					
					System.out.println("sunrise = "+sunrise);
					//get sunset GT_Sunset
					List<WebElement> row5 = driver.findElements(By.xpath("//span[@id='GT_Sunset']"));
					WebElement firstRow5 = row5.get(0);
					String sunset = firstRow5.getText();
					
					System.out.println("sunset = "+sunset);
					
					System.out.println("================================");
					
					
					if(i==2) {
						k+=1;
					}if(i==3) {
						k+=3;
					}if(i==4) {
						k+=4;
					}
					System.out.println(k);

					driver.findElement(By.xpath("//select[contains(@id,'PID_ALL')]/option["+k+"]")).click();
					driver.findElement(By.xpath("//div[contains(@id,'mobile_search')]/div[7]/button")).click();

					
					
					WeatherVO weatherVO = new WeatherVO();
					
					weatherVO.setTime(time);
					weatherVO.setTitle(title);
					weatherVO.setWdiscrip(wdiscrip);
					weatherVO.setPicSrc(picSrc);
					weatherVO.setTemperature(temp);
					weatherVO.setRain(rain);
					weatherVO.setSunrise(sunrise);
					weatherVO.setSunset(sunset);
					
					context.setAttribute("W"+i, weatherVO);
					
					
				}//for end
				
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally{
				driver.close();
				driver.quit();
			}
	

	  } 

}
