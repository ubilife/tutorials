package net.ubilife.spring.knights;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public Knight getKnight() {
		// change getPrincessQuest() to getDragonQuest() to change the quest type.
		return new BraveKnight(getPrincessQuest());
	}
	
	@Bean
	public Quest getDragonQuest() {
		return new DragonSlayingQuest();
	}
	
	@Bean
	public Quest getPrincessQuest() {
		return new PrincessRescuingQuest();
	}
	
}
