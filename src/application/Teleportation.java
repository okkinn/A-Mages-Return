package application;

public class Teleportation extends Spell {
	// attributes
	protected final static double COOLDOWN = 100.0;
	private final static String SPELL_NAME = "Teleportation";
	private final static int MANA_COST = 50;
	private final static int DAMAGE = 0;
	
	
	// constructor
	public Teleportation() {
		super(SPELL_NAME, MANA_COST, DAMAGE, Spell.TELEPORTATION_ID);
	}
	
	public Teleportation(double positionX, double positionY) {
		super(SPELL_NAME, MANA_COST, DAMAGE, Spell.TELEPORTATION_ID);
		this.setImage("Portal.gif");
		this.setPosition(positionX, positionY);
	}
}
