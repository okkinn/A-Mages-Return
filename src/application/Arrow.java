package application;

public class Arrow extends Sprite {
	// attributes
	private int damage = Archer.DAMAGE;
	private final int ARROW_SPEED = 150;
		
	// constructor
	public Arrow(double positionX, double positionY, double arrowAngle) {
//		System.out.println("Created new arrow projectile...");
		this.setImage("ArcaneArcherArrow.png");
		this.setPosition(positionX, positionY);
		this.setVelocity(ARROW_SPEED*Math.cos(arrowAngle), ARROW_SPEED*Math.sin(arrowAngle));
	}

	public int getDamage() {
		return damage;
	}
}
