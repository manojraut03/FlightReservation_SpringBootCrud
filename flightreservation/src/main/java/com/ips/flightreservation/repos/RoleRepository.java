package com.ips.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ips.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
