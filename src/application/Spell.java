package application;

public abstract class Spell extends Sprite {
	// attributes
	private String spellName;
	private int manaCost;
	private int damage;
	private double cooldown;
	private int spellId;
	protected int hotbarPos;
	
	public static final int FIREBALL_ID = 0;
	public static final int TELEPORTATION_ID = 1;
	
	// constructor
	public Spell(String spellName, int manaCost, int damage, int spellId) {
		this.spellName = spellName;
		this.manaCost = manaCost;
		this.damage = damage;
		this.spellId = spellId;
		this.hotbarPos = spellId;
//		System.out.println("Created new spell object named "+this.spellName+" ...");
	}
	
	
	// methods
	public int getHotbarPos() {
		return hotbarPos;
	}
	
	public void decreaseCooldown(double elapsedTime) {
		this.cooldown = this.cooldown - elapsedTime;
	}
	
	public double getCooldown() {
		return this.cooldown;
	}
	
	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public String getSpellName() {
		return this.spellName;
	}


	public int getSpellId() {
		return spellId;
	}
}
