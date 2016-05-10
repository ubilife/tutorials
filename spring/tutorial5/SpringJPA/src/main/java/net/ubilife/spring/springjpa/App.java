package net.ubilife.spring.springjpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		CustomerRepository repo = ctx.getBean(CustomerRepository.class);

		// insert
		Customer c1 = repo.save(new Customer("John", "Doe"));
		Customer c2 = repo.save(new Customer("Jane", "Doe"));
		Customer c3 = repo.save(new Customer("Jack", "Smith"));
		
		for(Customer t: repo.findAll()) {
			System.out.println(t.getFirstName()+", "+t.getLastName());
		}
		
		// delete		
//		repo.delete(c1);
		
		// update
		c2.setFirstName("Janet");
		c2 = repo.save(c2);
		
		for(Customer t: repo.findAll()) {
			System.out.println(t.getFirstName()+", "+t.getLastName());
		}
		
		// try our custom methods
		
		for(Customer t: repo.findByLastNameOrderByFirstNameAsc("Doe")) {
			System.out.println(t.getFirstName()+", "+t.getLastName());
		}
		
		ctx.close();
	}

}
