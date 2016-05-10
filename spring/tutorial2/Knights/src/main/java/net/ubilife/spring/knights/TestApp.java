package net.ubilife.spring.knights;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		Knight knight = ctx.getBean(Knight.class);
		
		knight.embarkOnQuest();
		
		ctx.close();
	}

}
