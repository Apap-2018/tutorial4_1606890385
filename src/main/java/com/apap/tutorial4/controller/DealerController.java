package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/dealer/add", method= RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value="/dealer/add", method=RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}
	
	@RequestMapping(value="/dealer/view", method=RequestMethod.GET)
	private String viewDealer(@RequestParam ("dealerId") long id, Model model) {
		if(dealerService.getDealerDetailById(id).isPresent()) {
			
			DealerModel dealer = dealerService.getDealerDetailById(id).get();
			model.addAttribute("dealer", dealer);
			/*model.addAttribute("car", dealer.getListCar());*/
			System.out.println(dealer.getListCar().size());
			List<CarModel> car = dealer.getListCar();
			model.addAttribute("car", car);
			return "viewDealer";
		}
		else {
			model.addAttribute("exception", "Data tidak ditemukan");
			return "exception";
		}
		
	}
	
	//Method untuk menghapus dealer
	@RequestMapping(value="/dealer/delete", method=RequestMethod.GET)
	private String deleteDealer(@RequestParam ("dealerId") long id, Model model){
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "delete";
		
	}
	
	@RequestMapping(value="/dealer/update/{id}", method= RequestMethod.GET)
	private String updateDealer(@PathVariable String id, Model model) {
		long dealerId = Long.parseLong(id);
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		model.addAttribute("dealer", dealer);
		return "updateDealer";
	}
	
	@RequestMapping(value="/dealer/update/{id}", method=RequestMethod.POST)
	private String updateDealer(@PathVariable (value="id") long id, @ModelAttribute DealerModel dealer) {
		dealerService.updateDealer(dealer, id);
		return "update";
	}
	
	@RequestMapping("/viewAll")
	private String viewAll(Model model) {
		List<DealerModel> dealerAll =  dealerService.viewAll();
		model.addAttribute("listDealer", dealerAll);
		return "viewAll";
	}
	
	
	
	
}


