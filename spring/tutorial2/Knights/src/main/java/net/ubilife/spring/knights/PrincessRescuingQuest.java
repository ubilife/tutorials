package net.ubilife.spring.knights;

import org.springframework.stereotype.Component;

@Component
public class PrincessRescuingQuest implements Quest{

	@Override
	public void embark() {
		System.out.println("Embarking on a quest to rescue a princess!");
		
	}

}
