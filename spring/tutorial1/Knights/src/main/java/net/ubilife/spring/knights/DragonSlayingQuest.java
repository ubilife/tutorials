package net.ubilife.spring.knights;

public class DragonSlayingQuest implements Quest {
	
	@Override
	public void embark() {
		System.out.println("Embarking on a quest to slay a big evil dragon.");
	}
	
}
