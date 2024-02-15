package application;

import scenes.GameProper;

public class Player extends Sprite {
	// attributes
	private final int MAX_HEALTH = 100;
	private final int MAX_MANA = 50;
	private final int STARTING_GOLD = 0;
	private final int STARTING_HEALTH = 100;
	private final int STARTING_MANA = 50;
	
	private String name;
	private int health;
	private int mana;
	private int gold;
	private double regenManaCounter = 5;
	
	private Spell spells[];
	private Spell activeSpell;
	private int spellCounter;
	private final static int MAX_SPELLS = 2;
	
	private int status;
	public final static int PLAYER_IDLE = 0;
	public final static int PLAYER_RUNNING = 1;
	
	// constructor
	public Player(String name) {
		this.setStatus(PLAYER_IDLE);
		this.name = name;
		this.health = STARTING_HEALTH;
		this.mana = STARTING_MANA;
		this.gold = STARTING_GOLD;
		this.spellCounter = MAX_SPELLS;
		this.spells = new Spell[MAX_SPELLS];
		this.spells[Spell.FIREBALL_ID] = new Fireball();
		this.spells[Spell.TELEPORTATION_ID] = new Teleportation();
		this.activeSpell = this.spells[0];
		this.setPosition(GameProper.WINDOW_WIDTH/2-25, GameProper.WINDOW_HEIGHT/2-25);
		System.out.println("Created new player...");
	}
	
	
	// methods
	public Fireball castFireball(double mouseX, double mouseY) {
		double playerX = this.getPositionX();
		double playerY = this.getPositionY();
		
        // Calculate angle between player and mouse position
		double fireballAngle = Math.atan2(mouseY - playerY, mouseX - playerX);
		
		// generate fireball object
		Fireball fireball = new Fireball(playerX, playerY, fireballAngle);

		// update cooldown and mana
		this.getActiveSpell().setCooldown(Fireball.COOLDOWN);
		this.setMana(this.getMana() - fireball.getManaCost());
		
		
		// flip image if mouse clicked left of player
		if(mouseX < playerX) {
        	fireball.setFlip(-1);
        } else {
        	fireball.setFlip(1);
        }
			
//		System.out.println(this.name+" casts "+spell.spellName+"...");
			
		return fireball;
	}
	
	public Teleportation castTeleportation() {
		// choose random (x, y) coordinate within forest at edge
		Double portalX = 510 * Math.random() + 80;
		Double portalY = 370 * Math.random() + 80; 
		double randInt = Math.random();
		if( randInt <= 0.25)	// spawn at upper forest
			portalY = 80.0;
		else if( randInt <= 0.5 )		// spawn at right forest
			portalX = 590.0;
		else if( randInt <= 0.75 )	// spawn at lower forest
			portalY = 450.0;
		else 		// spawn at left forest
			portalX = 80.0;
		
		// spawn portal
		Teleportation portal = new Teleportation(portalX, portalY);

		// update player
		this.getActiveSpell().setCooldown(Teleportation.COOLDOWN);
		this.setMana(this.getMana() - portal.getManaCost());
		
		return portal;
	}
	
	
	public void update(double elapsedTime) {
		super.update(elapsedTime);
		this.updateCooldowns(elapsedTime);
		this.regenMana(elapsedTime);
	}
	
	// viewState method
	public void viewState() {
		System.out.println("\n=============== VIEW STATE ===============");
		System.out.println("\tPLAYER NAME: "+this.name);
		System.out.println("\tHEALTH: "+this.health);
		System.out.println("\tMANA: "+this.mana);
		System.out.println("\tGOLD: "+this.gold);
		System.out.println("\tSPELL COUNTER: "+this.spellCounter);
		System.out.println("\tSPELLS: ");
		for(int i=0; i<this.spellCounter; i++) System.out.println("\t\t" + this.spells[i].getSpellName());
		System.out.println("\tACTIVE SPELLS: "+this.activeSpell.getSpellName());
		System.out.println("==========================================\n");
	}
	
	
	// setter and getter methods
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	
	public void decreaseHealth(int damage) {
		this.health -= damage;
	}
	

	public int getMana() {
		return this.mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getGold() {
		return this.gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}


	public Spell getActiveSpell() {
		return this.activeSpell;
	}

	public void setActiveSpell(int activeSpell) {
		if(activeSpell != this.activeSpell.hotbarPos) {
			System.out.println("Set active spell to "+this.spells[activeSpell].getSpellName()+"...");
			this.activeSpell = this.spells[activeSpell];			
		}
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getStatus() {
		return this.status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public void addVelocity(double x, double y) {
        this.setStatus(PLAYER_RUNNING);
        if(this.getPositionX() < 100 && x < 0) x = 0;
        if(this.getPositionX() > GameProper.WINDOW_WIDTH - 105 && x > 0) x = 0;
        if(this.getPositionY() < 65 && y < 0) y = 0;
    	if(this.getPositionY() > GameProper.WINDOW_HEIGHT-110 && y > 0) y = 0;
    	
    	super.addVelocity(x, y);
	}

	
	public void updateCooldowns(double elapsedTime) {
		for(int i=0; i<this.spellCounter; i++) {
			if(this.spells[i].getCooldown() > 0) {
				this.spells[i].decreaseCooldown(elapsedTime);				
			}
		}
	}


	public void addGold(int gold) {
		this.gold += gold;
	}


	public int getMAX_HEALTH() {
		return MAX_HEALTH;
	}


	public int getMAX_MANA() {
		return MAX_MANA;
	}
	
	
	public int getMAX_SPELLS() {
		return MAX_SPELLS;
	}


	public void regenMana(double elapsedTime) {
		if(this.getMana() < this.getMAX_MANA()) 
			this.regenManaCounter -= elapsedTime;
		if(this.regenManaCounter <= (double) 0) {
			this.mana += 2;		// regen 2 mana
			if(this.getMana() > this.getMAX_MANA()) this.mana = this.getMAX_MANA();
			this.regenManaCounter = 5;		// every 5 seconds
		}
	}
	
	
	public Spell getSpell(int spellCounter) {
		return this.spells[spellCounter];
	}
	
	
	public void restoreHealth(int health) {
		this.health += health;
		if(this.health > this.MAX_HEALTH) {
			this.health = this.MAX_HEALTH;
		}
	}
	
	
	public void restoreMana(int mana) {
		this.mana += mana;
		if(this.mana > this.MAX_MANA) {
			this.mana = this.MAX_MANA;
		}
	}
}
