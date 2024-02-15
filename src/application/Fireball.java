package application;

public class Fireball extends Spell {
	// attributes
	protected final static double COOLDOWN = 1.75;
	private final static String SPELL_NAME = "Fireball";
	private final static int MANA_COST = 1;
	private final static int DAMAGE = 10;
	
	
	// constructor
	public Fireball() {
		super(SPELL_NAME, MANA_COST, DAMAGE, Spell.FIREBALL_ID);
	}
	
	public Fireball(double positionX, double positionY, double fireballAngle) {
		super(SPELL_NAME, MANA_COST, DAMAGE, Spell.FIREBALL_ID);
		this.setImage("Fireball.gif");
		this.setPosition(positionX, positionY);
		this.setVelocity(200*Math.cos(fireballAngle), 200*Math.sin(fireballAngle));
	}
}
