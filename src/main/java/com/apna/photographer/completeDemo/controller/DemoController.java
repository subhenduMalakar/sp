package com.apna.photographer.completeDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.apna.photographer.completeDemo.entity.Emails;
import com.apna.photographer.completeDemo.entity.Product;
import com.apna.photographer.completeDemo.service.ProductService;

@Controller
public class DemoController {

	@Autowired
	private ProductService productService;
	
	
	
	public DemoController(ProductService productService) {
		this.productService = productService;
	}



	@GetMapping("/home")
	public String sayHello(Model theModel) {

	//	Message theMessage = new Message();
		
		Emails theEmails = new Emails();

	//	theModel.addAttribute("message", theMessage);
		
		theModel.addAttribute("emails", theEmails);
		
		
		List<Product> theProduct = productService.findAll();
		
		theModel.addAttribute("product", theProduct);

		return "prod/index";
	}

}
