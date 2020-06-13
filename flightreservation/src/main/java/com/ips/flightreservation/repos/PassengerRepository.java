package com.ips.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ips.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
