package com.example.practice.webservice;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.business.ReservationService;
import com.example.practice.business.RoomReservation;
import com.example.practice.data.Guest;
import com.example.practice.data.Room;
import com.example.practice.util.DateUtils;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	private final DateUtils dateUtils;
	private final ReservationService reservationService;
	public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}
	
	@RequestMapping(path="/reservations", method=RequestMethod.GET)
	public List<RoomReservation> getReservations(@RequestParam(value="date", required=false)String dateString){
		
		Date date = this.dateUtils.createDateFromDateString(dateString);
		return this.reservationService.getRoomReservationsForDate(date);
		
	}
	
	//@RequestMapping(path="/guests", method=RequestMethod.GET)
	@GetMapping("/guests")
	public List<Guest> getGuests()
	{
		return this.reservationService.getHotelGuests();
	}

	//@RequestMapping(path="/addguest", method=RequestMethod.POST)
	@PostMapping("/addguest")
	public void addGuest(@RequestBody Guest guest)
	{

		this.reservationService.addGuest(guest);
	}
	
	//@RequestMapping(path="/rooms", method=RequestMethod.GET)
	@GetMapping("/rooms")
	public List<Room> getRooms()
	{
		return this.reservationService.getRooms();
	}
}
