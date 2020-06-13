package com.ips.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ips.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
