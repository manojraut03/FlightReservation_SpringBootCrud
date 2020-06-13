package com.ips.flightreservation.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ips.flightreservation.dto.ReservationRequest;
import com.ips.flightreservation.entities.Flight;
import com.ips.flightreservation.entities.Passenger;
import com.ips.flightreservation.entities.Reservation;
import com.ips.flightreservation.repos.FlightRepository;
import com.ips.flightreservation.repos.PassengerRepository;
import com.ips.flightreservation.repos.ReservationRepository;
import com.ips.flightreservation.util.EmailUtil;
import com.ips.flightreservation.util.PDFGenerator;
@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Value("${com.ips.flightreservation.itinerary.dirpath}")
	private  String ITINERARY_DIR ;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	ReservationRepository 	reservationRepository ;
	@Autowired
	PDFGenerator pdfGenerator;
	@Autowired
	EmailUtil emailUtil;

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		final Logger LOGGER= LoggerFactory.getLogger(ReservationServiceImpl.class);
		
		//Make Payment
		long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for flight id "+flightId);
		Optional<Flight> flightopt = flightRepository.findById(flightId);
		Flight flight=flightopt.get();
		
		Passenger passenger= new Passenger();
		passenger.setFirstname(request.getPassengerFirstName());
		passenger.setLastname(request.getPassengerLastName());
		passenger.setMiddlename(request.getPassengerMiddleName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the Passenger "+passenger);
		Passenger savedpassenger = passengerRepository.save(passenger);
		
		Reservation reservation= new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedpassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving the Reservation "+reservation);
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR+savedReservation.getId()+".pdf";
		LOGGER.info("Generating the Itinerary");
		pdfGenerator.generateItinery(savedReservation, filePath);
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
