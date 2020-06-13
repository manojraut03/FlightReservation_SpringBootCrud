package com.ips.flightreservation.services;

import com.ips.flightreservation.dto.ReservationRequest;
import com.ips.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);
		
	

}
