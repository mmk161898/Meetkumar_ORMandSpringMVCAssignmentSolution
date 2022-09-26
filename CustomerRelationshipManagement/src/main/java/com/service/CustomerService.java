package com.service;

import java.util.List;

import com.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer findCustomerById(int id);

	public void save(Customer customer);

	public void deleteById(int id);

}
