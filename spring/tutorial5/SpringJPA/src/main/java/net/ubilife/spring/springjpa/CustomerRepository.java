package net.ubilife.spring.springjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
	List<Customer> findByLastNameOrderByFirstNameAsc(String ln);
	
	List<Customer> findByLastNameAndFirstName(String ln, String fn);
	
	
	
}
