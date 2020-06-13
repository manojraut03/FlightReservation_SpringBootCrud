package com.ips.location.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ips.location.entity.Location;
import com.ips.location.repository.LocationRepository;
import com.ips.location.services.LocationService;
import com.ips.location.util.EmailUtil;
import com.ips.location.util.ReportUtil;

@Controller
public class LocationController {
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	LocationService service;
	
	@Autowired
	ServletContext sc;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	LocationRepository repository;

	@RequestMapping("/showcreate")
	public String showCreate() {
		return "createlocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelmap) {
		Location locationsave = service.savelocation(location);
		String msg="location saved with id:"+locationsave.getId();
		modelmap.addAttribute("msg", msg);
		emailUtil.sendEmail("test.manoj197@gmail.com", "Location Saved",
				"Location Saved Successfully and about to return a response");
		return "createlocation";
		
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocaton(@RequestParam("id") int id, ModelMap modelMap)
	{
		Optional<Location> locationopt=service.getLocation(id);
		Location location= locationopt.get();
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Optional<Location> locationopt=service.getLocation(id);
		Location location= locationopt.get();
		modelMap.addAttribute("location", location);
		return "updateLocation";
				
	}
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelmap) {
		service.updateLocation(location);
		List<Location> locations = service.getAllLocation();
		modelmap.addAttribute("locations", locations); 
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = sc.getRealPath("/");
		List<Object[]> data = repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";

	}
	
}
