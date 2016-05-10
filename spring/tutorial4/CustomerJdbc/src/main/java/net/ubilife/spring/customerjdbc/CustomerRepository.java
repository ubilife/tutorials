package net.ubilife.spring.customerjdbc;

import java.util.List;

public interface CustomerRepository {

	Customer findOne(long id);
	
	Customer save(Customer cust);
	
	List<Customer> findAll();
	
	int update(Customer cust);
	
	int delete (Customer cust);
	
}
