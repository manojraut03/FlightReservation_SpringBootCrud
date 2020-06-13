package com.ips.location.services;

import java.util.List;
import java.util.Optional;

import com.ips.location.entity.Location;

public interface LocationService {
	
	Location savelocation(Location location);
	Location updateLocation(Location location);
	void deleteLocation(Location location);
	Optional<Location> getLocation(int id);
	List<Location> getAllLocation();

}
