package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Customer;
import com.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String getAllCustomers(Model theModel) {
		List<Customer> allCustomers = customerService.findAll();
		theModel.addAttribute("Customers", allCustomers);
		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer customer = new Customer();

		theModel.addAttribute("Customer", customer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel) {

		Customer customer = customerService.findCustomerById(id);

		theModel.addAttribute("Customer", customer);

		return "Customer-form";

	}

	@RequestMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("first_name") String firstName,
			@RequestParam("last_name") String lastName, @RequestParam("email") String email) {
		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findCustomerById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		} else {
			theCustomer = new Customer();
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		}
		customerService.save(theCustomer);
		return "redirect:/customers/list";
	}

	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerService.deleteById(id);
		return "redirect:/customers/list";
	}

}
