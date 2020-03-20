package com.spring.dailyBed.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.spring.dailyBed.model.DailyBedService;
import com.spring.location.model.LocationService;
import com.spring.location.model.LocationVO;

public class dailyBedListener implements ServletContextListener {

	java.util.Timer timer = new java.util.Timer();

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
		System.out.println("dailyBedListener would be close");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("dailyBedListener open");

		try {

			LocationService locSvc = new LocationService();
			DailyBedService dailyBedSvc = new DailyBedService();
			

			// used for compare
			List<String> Comparelist = dailyBedSvc.getAllDifferentLoc();

			List<LocationVO> list = locSvc.getAll();

			LocalDate now = LocalDate.now();
			LocalDate Day20OfnextMonth = now.with(TemporalAdjusters.lastDayOfMonth()).plusDays(20);

			int thisMonthDays = now.lengthOfMonth();
			int nextMonthDays = now.plusMonths(1).lengthOfMonth();

			// compare

			for (LocationVO locationVO : list) {

				if (Comparelist.contains(locationVO.getLocation_id())) {
					System.out.println("擋住排程了");
					return;
				} else {
					
					

						// add this month days
						try {
							for (int i = 1; i <= thisMonthDays; i++) {

								String location_id = locationVO.getLocation_id();
								Integer remaining_total = locationVO.getBedTotal_num();
								Integer provided_total = 0;
								java.sql.Date dailyBed_date = java.sql.Date.valueOf(now.withDayOfMonth(i));
								System.out.println(dailyBed_date);
								dailyBedSvc.addDailyBed(location_id, remaining_total, provided_total, dailyBed_date);
								System.out.println("come here this month days");
							}
							
							// add next month days	
							for (int i = 1; i <= nextMonthDays; i++) {

								String location_id = locationVO.getLocation_id();
								Integer remaining_total = locationVO.getBedTotal_num();
								Integer provided_total = 0;
								java.sql.Date dailyBed_date = java.sql.Date
										.valueOf(now.plusMonths(1).withDayOfMonth(i));
								System.out.println(dailyBed_date);
								dailyBedSvc.addDailyBed(location_id, remaining_total, provided_total, dailyBed_date);
								System.out.println("come here next month days");
							}
							
	

						} catch (Exception e) {
							e.printStackTrace();
						}

				}

			}
			
			timer.schedule(new TaskSet(), 
					1000 * 60 * 60 * 24 * java.time.temporal.ChronoUnit.DAYS.between(now, Day20OfnextMonth));

			System.out.println("DAYS.between(now, Day20OfnextMonth):"
					+ java.time.temporal.ChronoUnit.DAYS.between(now, Day20OfnextMonth));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class TaskSet extends TimerTask {

		LocationService locSvc = new LocationService();
		DailyBedService dailyBedSvc = new DailyBedService();

		List<LocationVO> list = locSvc.getAll();
		// used for compare
		List<String> Comparelist = dailyBedSvc.getAllDifferentLoc();

		public void run() {
			System.out.println("inininin");

			LocalDate now = LocalDate.now();
			int nextMonthDays = now.plusMonths(1).lengthOfMonth();

			System.out.println("nextMonthDays" + nextMonthDays);

			// add location
			for (LocationVO locationVO : list) {


						
						// add next month days
						try {
							for (int i = 1; i <= nextMonthDays; i++) {

								String location_id = locationVO.getLocation_id();
								Integer remaining_total = locationVO.getBedTotal_num();
								Integer provided_total = 0;
								java.sql.Date dailyBed_date = java.sql.Date
										.valueOf(now.plusMonths(1).withDayOfMonth(i));
								System.out.println(dailyBed_date);
								dailyBedSvc.addDailyBed(location_id, remaining_total, provided_total, dailyBed_date);
								System.out.println("come here next month days");
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
						
						System.out.println("locationVO insert");

					
				}

			
			System.out.println("endendend");
		}

	}

}
