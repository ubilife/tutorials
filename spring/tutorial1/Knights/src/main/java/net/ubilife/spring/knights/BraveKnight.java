package net.ubilife.spring.knights;

public class BraveKnight implements Knight {

	private Quest quest;
	
	public BraveKnight(Quest q) {
		quest = q;
	}
	
	@Override
	public void embarkOnQuest() {
		quest.embark();
	}

}
