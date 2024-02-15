package application;

public class Archer extends Sprite {
	// attributes
	protected final static int MAX_ARCHERS = 5;
	protected final static double SPAWN_TIMER = 15.0;
	protected final static int FACE_RIGHT = 1;
	protected final static int FACE_LEFT = -1;
	protected final static double DEFAULT_SHOOT_COOLDOWN = 4.0;
	protected final static int DAMAGE = 20;
	private final int SPAWN_LEFT = 0;
	private final int SPAWN_TOP = 1;
	private final int SPAWN_RIGHT = 2;
	private final int SPAWN_BOTTOM = 3;
	
	private int health;
	private int xVelocity, yVelocity;
	private double bx, by;
	private int spawnLocation;
	private double shootCooldown;
	private Arrow arrow;
	private boolean isDead;
	
	// constructor
	public Archer() {
//		System.out.println("Spawn archer...");
		this.health = 40;
        this.setImage("ArcaneArcherRun.gif", 60, 60, true, true);         
        this.spawnFromRandomPos();
        this.setVelocity(xVelocity, yVelocity);
		this.shootCooldown = DEFAULT_SHOOT_COOLDOWN;
		this.isDead = false;
	}


	// methods
	public void hit(Fireball fireball, Player player) {
		this.decreaseHealth(fireball.getDamage());
		if ( this.getHealth() <= 0) {
			player.addGold(5);
			this.isDead = true;
		}
	}
	
	public Arrow shootArrow(double playerX, double playerY) {
        // Calculate angle between archer and player position
		double arrowAngle = Math.atan2(playerY - this.getPositionY(), playerX - this.getPositionX());
		this.arrow = new Arrow(this.getPositionX(), this.getPositionY(), arrowAngle);
		this.shootCooldown = DEFAULT_SHOOT_COOLDOWN;
		if(playerX < this.getPositionX()) {
        	this.arrow.setFlip(-1);
        } else {
        	this.arrow.setFlip(1);
        }
		return this.arrow;
	}
	
	
	private void spawnFromRandomPos() {
		this.bx = 540 * Math.random() + 100;
        this.by = 420 * Math.random() + 50; 
        double randInt = Math.random();
		if( randInt <= 0.25) {	// spawn at top forest
			this.by = 60;
			this.spawnLocation = SPAWN_TOP;
			randomizeXVelocity();
			checkVelocityDirection(this.xVelocity);
		}
		else if( randInt <= 0.5 ) {		// spawn at right forest
			this.bx = 640;
			this.spawnLocation = SPAWN_RIGHT;
			this.setFlip(FACE_LEFT);
			randomizeYVelocity();
		}
		else if( randInt <= 0.75 ) {		// spawn at bottom forest
			this.by = 470;
			this.spawnLocation = SPAWN_BOTTOM;
			randomizeXVelocity();
			checkVelocityDirection(this.xVelocity);
		}
		else {		// spawn at left forest
			this.bx = 60;
			this.spawnLocation = SPAWN_LEFT;
			this.setFlip(FACE_RIGHT);
			randomizeYVelocity();
		}
		setPosition(this.bx,this.by);
	}
	
	
	public void update(double elapsedTime) {
		// update movement when borders are hit
		if(spawnLocation == SPAWN_LEFT || spawnLocation == SPAWN_RIGHT) {
			// top border
			if(this.getPositionY() < 50) {
				randomizeYVelocity();
				if(this.yVelocity < 0) this.yVelocity = this.yVelocity * -1;
				this.setVelocity(0, yVelocity);
			}
			// bottom border
			if(this.getPositionY() > 480) {
				randomizeYVelocity();
				if(this.yVelocity > 0) this.yVelocity = this.yVelocity * -1;
				this.setVelocity(0, yVelocity);
			}
		} else {	// spawn at top or bottom
			// left border
			if(this.getPositionX() < 100) {
				randomizeXVelocity();
				if(this.xVelocity < 0) this.xVelocity = this.xVelocity * -1;
				checkVelocityDirection(this.xVelocity);
				this.setVelocity(xVelocity, 0);
			}
			// right border
			if(this.getPositionX() > 580) {
				randomizeXVelocity();
				if(this.xVelocity > 0) this.xVelocity = this.xVelocity * -1;
				checkVelocityDirection(this.xVelocity);
				this.setVelocity(this.xVelocity, 0);
			}
		}
		
		/// update shootcooldown
		this.decreaseShootCooldown(elapsedTime);
		
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
			this.xVelocity = (int) (50*Math.random() + 50);
		} else {
			this.xVelocity = (int) -(50*Math.random() + 50);
		}
	}
	
	
	private void randomizeYVelocity() {
		if( Math.random() >= 0.5) {
			this.yVelocity = (int) -(50*Math.random() + 50);
		} else {
			this.yVelocity = (int) (50*Math.random() + 50);
		}
	}
	
	
	public int getHealth() {
		return health;
	}


	public void decreaseHealth(int damage) {
		this.health -= damage;
	}

	public int getDamage() {
		return DAMAGE;
	}
	
	public int getSpawnLocation() {
		return this.spawnLocation;
	}
	
	public void decreaseShootCooldown(double elapsedTime) {
		this.shootCooldown -= elapsedTime;
	}
	
	public double getShootCooldown() {
		return shootCooldown;
	}
	
	protected boolean getIsDead() {
		return this.isDead;
	}
}
