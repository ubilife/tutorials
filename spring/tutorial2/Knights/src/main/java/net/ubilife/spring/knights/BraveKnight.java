package net.ubilife.spring.knights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BraveKnight implements Knight {

	/*
	 * Here we have ambiguous autowiring because there are two beans that
	 * implement the Quest interface. To solve this ambiguity, you can use
	 * the @Qualifier annotation to tell which Quest class should be autowired.
	 * The string attribute is the class name (e.g., PrincessRescuingQuest) with
	 * first letter in lower-case.
	 */
	@Autowired
	@Qualifier("princessRescuingQuest")
	private Quest quest;

	@Override
	public void embarkOnQuest() {
		quest.embark();
	}

}
