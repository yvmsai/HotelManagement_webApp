package com.example.practice.web;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practice.business.ReservationService;
import com.example.practice.business.RoomReservation;
import com.example.practice.data.Guest;
import com.example.practice.util.DateUtils;

@Controller
@RequestMapping("/guests")
public class GuestController {
	
		private final ReservationService reservationService;
		
	
		public GuestController(ReservationService reservationService) {
	        this.reservationService = reservationService;
	    }

	    @RequestMapping(method = RequestMethod.GET)
	    public String getGuests(Model model){
	        model.addAttribute("guests", this.reservationService.getHotelGuests());
	        return "hotel-guests";
	    }

}
