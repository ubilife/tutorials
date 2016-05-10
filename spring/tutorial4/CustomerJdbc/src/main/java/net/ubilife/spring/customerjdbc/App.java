package net.ubilife.spring.customerjdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		CustomerRepository repo = ctx.getBean(CustomerRepository.class);
		
		Customer c1 = new Customer("John", "Doe");
		Customer c2 = new Customer("Jane", "Doe");
		
		// insert
		c1 = repo.save(c1);
		c2 = repo.save(c2);
		
		for(Customer t : repo.findAll()) {
			System.out.println(t.getFirstName()+", "+t.getLastName());
		}
		 
		// update
		c2.setFirstName("Janet");		
		repo.update(c2);
		
		for(Customer t : repo.findAll()) {
			System.out.println(t.getFirstName()+", "+t.getLastName());
		}
		 
		// delete
		repo.delete(c1);
		
		for(Customer t : repo.findAll()) {
			System.out.println(t.getFirstName()+", "+t.getLastName());
		}
				
		ctx.close();
	}

}
