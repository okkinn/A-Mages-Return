package application;

public class Potion extends Sprite {
	// attributes
	protected static final int HEALTH_POTION = 1;
	protected static final int MANA_POTION = 2;
	protected static final double HEALTH_POTION_DROP_CHANCE = 0.3;
	protected static final double MANA_POTION_DROP_CHANCE = 1;
	
	private int potionType;
	private int restoreValue;
	private double despawnTimer = 5;
	
	
	// constructors
	public Potion(double posX, double posY, int potionType) {
		if(potionType == MANA_POTION) 
			this.setImage("ManaPotion.png", 20, 20, true, true);
		else if(potionType == HEALTH_POTION)
			this.setImage("HealthPotion.png", 20, 20, true, true);
		this.setPosition(posX, posY);
		this.potionType = potionType;
		restoreValue = (int) (5 * Math.random() + 5);
	}
	
	
	// methods
	public void restorePlayer(Player player) {
		if(this.potionType == HEALTH_POTION) {
			player.restoreHealth(restoreValue);
			System.out.println("Player was restored " + restoreValue + " health");
		} else if(this.potionType == MANA_POTION) {
			player.restoreMana(restoreValue);
			System.out.println("Player was restored " + restoreValue + " mana");
		}
	}
	
	public int getPotionType() {
		return this.potionType;
	}
	
	public void decreaseDespawnTimer(double elapsedTime) {
		this.despawnTimer -= elapsedTime;
	}
	
	public double getDespawnTimer() {
		return this.despawnTimer;
	}
}
