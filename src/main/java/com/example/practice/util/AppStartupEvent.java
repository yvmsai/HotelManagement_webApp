package com.example.practice.util;

import java.util.Date;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.practice.business.ReservationService;
import com.example.practice.business.RoomReservation;


@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
	private final ReservationService reservationService;
    private final DateUtils dateUtils;
	



	public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils) {
		super();
		this.reservationService = reservationService;
		this.dateUtils = dateUtils;
	}




	@Override
	public void onApplicationEvent(ApplicationReadyEvent event)
	{
		   Date date = this.dateUtils.createDateFromDateString("2022-01-01");
	        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
	        reservations.forEach(System.out::println);
		
	}
}
