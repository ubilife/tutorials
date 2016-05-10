package net.ubilife.spring.knights;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class DragonSlayingQuest implements Quest {
	
	@Override
	public void embark() {
		System.out.println("Embarking on a quest to slay a big evil dragon.");
	}
	
}
