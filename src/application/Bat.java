package application;

import scenes.GameProper;

public class Bat extends Sprite {
	// attributes
	protected final static int MAX_BATS = 7;
	private final static int FACE_RIGHT = 1;
	private final static int FACE_LEFT = -1;
	protected final static double SPAWN_TIMER = 5.0;
	private final int BAT_SPEED = 150;
	
	private int health;
	private int damage;
	private int xVelocity, yVelocity;
	private double bx, by;
	private boolean isDead;
	
	// constructor
	public Bat() {
//		System.out.println("Spawn bat...");
		this.health = 10;
		this.damage = 10;
		this.isDead = false;
        this.setImage("Bat.gif", 35, 35, true, true);         
        this.spawnFromRandomPos();
        this.setVelocity(xVelocity, yVelocity);
	}


	// methods
	public void hit(Fireball fireball, Player player) {
		this.decreaseHealth(fireball.getDamage());
		if ( this.getHealth() <= 0) {
			player.addGold(1);
			this.isDead = true;
		}
	}
	
	private void spawnFromRandomPos() {
		this.bx = (GameProper.WINDOW_WIDTH - 205) * Math.random() + 100;
        this.by = (GameProper.WINDOW_HEIGHT - 175) * Math.random() + 65; 
        double randInt = Math.random();
        if( randInt <= 0.25) {	// spawn at upper forest
			this.by = 30;
			this.yVelocity = (int) -(50*Math.random() + 50);
			randomizeXVelocity();
		}
		else if( randInt <= 0.5 ) {		// spawn at right forest
			this.bx = GameProper.WINDOW_WIDTH-30;
			this.xVelocity = (int) -(50*Math.random() + 50);
			randomizeYVelocity();
		}
		else if( randInt <= 0.75 ) {		// spawn at lower forest
			this.by = GameProper.WINDOW_HEIGHT-30;
			this.yVelocity = (int) (50*Math.random() + 50);
			randomizeXVelocity();
		}
		else {		// spawn at left forest
			this.bx = 30;
			this.xVelocity = (int) (50*Math.random() + 50);
			randomizeYVelocity();
		}
		setPosition(this.bx,this.by);
		checkVelocityDirection(xVelocity);
	}
	
	
	public void update(double elapsedTime) {
		// hits left border
		if(this.getPositionX() <= 25) {
			randomizeYVelocity();
			checkVelocityDirection(-xVelocity);
			this.xVelocity = this.xVelocity * -1;
			this.setVelocity(xVelocity, yVelocity);
			this.setPositionX(this.getPositionX()+5);
		}
		// hits right border
		if(this.getPositionX() >= GameProper.WINDOW_WIDTH-25) {
			randomizeYVelocity();
			checkVelocityDirection(-xVelocity);
			this.xVelocity = this.xVelocity * -1;
			this.setVelocity(xVelocity, yVelocity);
			this.setPositionX(this.getPositionX()-5);
		}
		// hits top border
		if(this.getPositionY() <= 10) {
			randomizeXVelocity();
			checkVelocityDirection(xVelocity);
			this.yVelocity = this.yVelocity * -1;
			this.setVelocity(xVelocity, yVelocity);
			this.setPositionY(this.getPositionY()+5);
		}
		// hits bottom border
		if(this.getPositionY() >= GameProper.WINDOW_HEIGHT-25) {
			randomizeXVelocity();
			checkVelocityDirection(xVelocity);
			this.yVelocity = this.yVelocity * -1;
			this.setVelocity(xVelocity, yVelocity);
			this.setPositionY(this.getPositionY()-5);
		}
		super.update(elapsedTime);
	}
	
	
	private void checkVelocityDirection(int xVelocity) {
		if(xVelocity > 0) flipOffset(FACE_RIGHT);
		else flipOffset(FACE_LEFT);
	}
	
	
	private void flipOffset(int setFace) {
		if(this.flip == 1 && setFace == -1) {
			this.flip = -1;
		} else {
			if(this.flip == -1 && setFace == 1)
			this.flip = 1;
		}
	}
	
	
	private void randomizeXVelocity() {
		if( Math.random() >= 0.5) {
			this.xVelocity = (int) (100*Math.random() + BAT_SPEED);
		} else {
			this.xVelocity = (int) -(100*Math.random() + BAT_SPEED);
		}
	}
	
	
	private void randomizeYVelocity() {
		if( Math.random() >= 0.5) {
			this.yVelocity = (int) -(100*Math.random() + BAT_SPEED);
		} else {
			this.yVelocity = (int) (100*Math.random() + BAT_SPEED);
		}
	}
	
	
	public int getHealth() {
		return health;
	}


	public void decreaseHealth(int damage) {
		this.health -= damage;
	}


	public int getDamage() {
		return damage;
	}
	
	public boolean getIsDead() {
		return this.isDead;
	}
}
