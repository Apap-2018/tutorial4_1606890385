package com.apap.tutorial4.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.apap.tutorial4.service.*;
import com.apap.tutorial4.model.*;


@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value="/car/add/{dealerId}", method= RequestMethod.GET)
	private String add(@PathVariable(value="dealerId") Long dealerId, Model model) {
		CarModel car = new CarModel();
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		
		model.addAttribute("car", car);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add", method=RequestMethod.POST)
	private String addCarSubmit(@ModelAttribute CarModel car) {
		carService.addCar(car);
		return "add";
	}
	
	//Method untuk menghapus carbyId
	@RequestMapping(value="/car/delete/{id}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable String id, Model model){
		long carId = Long.parseLong(id);
		CarModel car = carService.getCarDetailById(carId).get();
		carService.deleteCar(car);
		return "delete";
	}
	
	
	@RequestMapping(value="/car/update/{id}", method= RequestMethod.GET)
	private String updateCar(@PathVariable String id, Model model) {
		long carId = Long.parseLong(id);
		CarModel car = carService.getCarDetailById(carId).get();
		model.addAttribute("car",car);
		return "updateCar";
	}
	
	@RequestMapping(value="/car/update/{id}", method=RequestMethod.POST)
	private String updateCar(@PathVariable (value="id") long id, @ModelAttribute CarModel car) {
		carService.updateCar(id, car);
		return "update";
	}
	
	

}
