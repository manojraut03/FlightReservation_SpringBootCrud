package com.ips.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ips.flightreservation.dto.ReservationRequest;
import com.ips.flightreservation.entities.Flight;
import com.ips.flightreservation.entities.Reservation;
import com.ips.flightreservation.repos.FlightRepository;
import com.ips.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	private static final Logger LOGGER= LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation() invoked with the flight Id: ");
		Optional<Flight> flight1 = flightRepository.findById(flightId);
		Flight flight= flight1.get();
		modelMap.addAttribute("flight", flight);
		LOGGER.info("Flight is: "+flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap modelMap) {
		LOGGER.info("Inside completeReservation() : "+request);
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation created successfully and the id is"+reservation.getId());
		return "reservationConfirmation";
		
	}

}
