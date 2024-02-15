package application;

import scenes.GameOverScene;
import scenes.GameProper;
import scenes.WinningScene;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameTimer extends AnimationTimer {
	
	private GraphicsContext gc;
	private Scene gameProper;
	private Stage stage;
	
	private LongValue lastNanoTime, startNanoTime;

	private Player player;
	private AnimatedImage playerImg;
	private double mouseX, mouseY;
	private int enemyDamage;
	
	private double batSpawnTimer;
	private double archerSpawnTimer;
	private int batCounter;
	private int archerCounter;
	
	private ArrayList<String> input;
	private ArrayList<Fireball> fireballList;
	private ArrayList<Bat> batList;
	private ArrayList<Archer> archerList;
	private ArrayList<Teleportation> portalList;
	private ArrayList<Potion> potionList;
	private ArrayList<Arrow> arrowList;
	private double hitTimer;
	
	private Image hotbarStateFS, hotbarStateTS;
	
	
	// constructor
	public GameTimer(GraphicsContext gc, Scene gameProper){
		// reset
		this.batCounter = 0;
		this.archerCounter = 0;
		this.batSpawnTimer = 2;
		this.archerSpawnTimer = 5;
		
		this.gc = gc;
		this.gameProper = gameProper;
		
		// for time counters
		this.lastNanoTime = new LongValue( System.nanoTime() );
		this.startNanoTime = new LongValue( System.nanoTime() );
		
		// arraylists
		this.input = new ArrayList<String>();
		this.fireballList = new ArrayList<Fireball>();
		this.batList = new ArrayList<Bat>();
		this.archerList = new ArrayList<Archer>();
		this.portalList = new ArrayList<Teleportation>();
		this.potionList = new ArrayList<Potion>();
		this.arrowList = new ArrayList<Arrow>();
		
		this.hitTimer = 0;		// can only get hit if 0; possible to be hit once every 1.5 secs
		
		// player-related
		this.player = new Player("Player");
		this.playerImg = new AnimatedImage();
		loadPlayerAnimations(playerImg);
		
		// mouse/key events
		this.handleKeyPressEvent();
		this.handleMouseEvent();
		
		player.viewState();
		
		// hotbar-related;
		this.hotbarStateFS = new Image("FireballSelected.gif", 100, 100, true, true);
		this.hotbarStateTS = new Image("TeleportationSelected.gif", 100, 100, true, true);
	}

	
	// methods
	@Override
	public void handle(long currentNanoTime) {
		// reset canvas
		gc.clearRect(0, 0, GameProper.WINDOW_WIDTH, GameProper.WINDOW_HEIGHT);
		
		// calculate time since last update.
        double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
        lastNanoTime.value = currentNanoTime;
        double t = (currentNanoTime - startNanoTime.value) / 1000000000.0;
        
        // game logic
        player.setVelocity(0,0);
        player.setStatus(Player.PLAYER_IDLE);
        checkPlayerMovement();
        spawnEnemy(elapsedTime);
        
        // game over listener; despawn listener
        checkIfPlayerDead();
        
        // update time-related attributes; position; spell cooldown; mana regen
        player.update(elapsedTime);
        
        // collision detection
        fireballDetectCollisions();
        detectCollisions();
    	
    	//show cooldown popup texts
        hitTimer -= elapsedTime;
        renderPopupTexts();
        
        // render sprites
        renderSprites(elapsedTime, t);
                
        // render ui-health, mana, gold, hotbar
        renderUI();
	}
	
	
	// for rednering popup texts when hit and the cooldowns above spells hotbar
	private void renderPopupTexts() {
		if(hitTimer*1.5 > 0) {
    		gc.setFill(Color.RED);
    		gc.setFont( Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 20) );
    		gc.fillText("-"+enemyDamage, player.getPositionX(), player.getPositionY());
    	}
		gc.setFill(Color.RED);
		gc.setFont( Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 22) ); 
		if(player.getSpell(Spell.FIREBALL_ID).getCooldown() > 0)
			gc.fillText(""+Math.round(player.getSpell(Spell.FIREBALL_ID).getCooldown() * 100.0) / 100.0, 161, GameProper.WINDOW_HEIGHT-50);
		if(player.getSpell(Spell.TELEPORTATION_ID).getCooldown() > 0)
			gc.fillText(""+Math.round(player.getSpell(Spell.TELEPORTATION_ID).getCooldown()), 218, GameProper.WINDOW_HEIGHT-50);
	}
	
	
	// for idle and run animations of mage player
	private void loadPlayerAnimations(AnimatedImage playerImg) {
		Image[][] playerImageArray = new Image[2][6];
		for(int i = 1; i <= 6; i++) playerImageArray[0][i-1] = new Image("Idle"+i+".png", 35, 35, true, true);
		for(int i = 1; i <= 6; i++) playerImageArray[1][i-1] = new Image("Run"+i+".png", 35, 35, true, true);
		
		playerImg.frames = playerImageArray;
		playerImg.duration = 0.1;
	}
	
	
	// method for spawning enemies
	private void spawnEnemy(double time) {
		batSpawnTimer -= time;
		if(batSpawnTimer <= 0 && this.batCounter < Bat.MAX_BATS) {	// if timer reach 0
            Bat bat = new Bat();
            this.batCounter++;
            batList.add( bat );
            batSpawnTimer = Bat.SPAWN_TIMER;		// spawn new bat every 4 secs
		}
		
		archerSpawnTimer -= time;
		if(archerSpawnTimer <= 0 && this.archerCounter < Archer.MAX_ARCHERS) {
			Archer archer = new Archer();
			this.archerCounter++;
			archerList.add( archer );
			archerSpawnTimer = Archer.SPAWN_TIMER;		// spawn new archer every 15 secs
		}
	}
	
	
	// detect fireball-sprite collisions
	private void fireballDetectCollisions() {
		// fireball-enemy collision
		Iterator<Fireball> fireballIter = fireballList.iterator();
        while ( fireballIter.hasNext() )
        {
        	Fireball fireball = fireballIter.next();
        	
        	// fireball reached edge of screen
        	if ( fireball.getPositionX() < 0 || fireball.getPositionY() < 0 || fireball.getPositionX() > GameProper.WINDOW_WIDTH || fireball.getPositionY() > GameProper.WINDOW_HEIGHT) {
    			fireballIter.remove();
    		} else {
    			
    			//fireball-bat
    			Iterator<Bat> batIter = batList.iterator();
            	while ( batIter.hasNext() ) {
            		Bat bat = batIter.next();
            		// fireball hit bat
            		if ( fireball.intersects(bat) )
            		{
            			bat.hit(fireball, player);
            			fireballIter.remove();
            			if(bat.getIsDead() == true) {
            				batIter.remove();
            				this.batCounter--;
            				if(Math.random() <= Potion.HEALTH_POTION_DROP_CHANCE) {
    	        				Potion healthPotion = new Potion(bat.getPositionX() + bat.getWidth()/2, bat.getPositionY() + bat.getHeight()/2, Potion.HEALTH_POTION);
    	        				potionList.add(healthPotion);        				
    	        			}
            			}
            			break;
            		}
            	}
            	
            	// fireball-archer
            	Iterator<Archer> archerIter = archerList.iterator();
            	while ( archerIter.hasNext() ) {
            		Archer archer = archerIter.next();
            		// fireball hit archer
            		if ( fireball.intersects(archer) )
            		{
            			archer.hit(fireball, player);
            			fireballIter.remove();
            			if ( archer.getIsDead() == true) {
            				archerIter.remove();
            				this.archerCounter--;
            				if(Math.random() <= Potion.MANA_POTION_DROP_CHANCE) {
            	    			Potion manaPotion = new Potion(archer.getPositionX() + archer.getWidth()/2, archer.getPositionY() + archer.getHeight()/2, Potion.MANA_POTION);
            	    			if(archer.getFlip() == Archer.FACE_LEFT) manaPotion.setPositionX(manaPotion.getPositionX() - 60);
            	    			potionList.add(manaPotion);
            				}
            			}
            			break;
            		}
            	}
            	
    		}
        	
        }
	}
	
	
	// detect player-sprites collisions
	private void detectCollisions() {
        // bat-player collision
        Iterator<Bat> batIter = batList.iterator();
    	while ( batIter.hasNext() ) {
    		Bat bat = batIter.next();
    		// bat hit player
    		if ( player.intersects(bat) && hitTimer <= 0)
    		{		
    			hitTimer = 1.5;
    			player.decreaseHealth(bat.getDamage());
    			enemyDamage = bat.getDamage();
    			System.out.println("Player dealt "+bat.getDamage()+" damage by bat");
    		}
    		// bat reached edge of screen
    		if ( bat.getPositionX() < 0 || bat.getPositionY() < 0 || bat.getPositionX() > GameProper.WINDOW_WIDTH || bat.getPositionY() > GameProper.WINDOW_HEIGHT) {
    			batIter.remove();
    			this.batCounter--;
    		}
    	}
    	
    	// archer-player collision
    	Iterator<Archer> archerIter = archerList.iterator();
    	while ( archerIter.hasNext() ) {
    		Archer archer = archerIter.next();
    		// archer hit player
    		if ( player.intersects(archer) && hitTimer <= 0)
    		{		
    			hitTimer = 1.5;
    			player.decreaseHealth(archer.getDamage());
    			enemyDamage = archer.getDamage();
    			System.out.println("Player dealt "+archer.getDamage()+" damage by archer");
    		}
    	}
    	
    	// arrow-player collision
    	Iterator<Arrow> arrowIter = arrowList.iterator();
    	while ( arrowIter.hasNext() ) {
    		Arrow arrow = arrowIter.next();
    		// archer hit player
    		if ( player.intersects(arrow) && hitTimer <= 0)
    		{		
    			hitTimer = 1.5;
    			player.decreaseHealth(arrow.getDamage());
    			enemyDamage = arrow.getDamage();
    			System.out.println("Player dealt "+arrow.getDamage()+" damage by archer");
    			arrowIter.remove();
    		} else if (arrow.getPositionX() < 0 || arrow.getPositionY() < 0 || arrow.getPositionX() > GameProper.WINDOW_WIDTH || arrow.getPositionY() > GameProper.WINDOW_HEIGHT)
    			arrowIter.remove();
    	}
    	
    	// potion-player collision
    	Iterator<Potion> potionIter = potionList.iterator();
    	while ( potionIter.hasNext() ) {
    		Potion potion = potionIter.next();
    		// check if despawned or player picks up potion
    		if ( potion.getDespawnTimer() <= 0 ) {
				potionIter.remove();
			} else if ( player.intersects(potion) ) {
    			potion.restorePlayer(player);
    			potionIter.remove();
    		}
    	}
    	
    	// player-portal collision
    	Iterator<Teleportation> portalIter = portalList.iterator();
    	while ( portalIter.hasNext() ) {
    		Teleportation portal = portalIter.next();
    		// player enters portal
    		if ( player.intersects(portal) ) {
    			playWinningScene();
    		}
    	}
	}
	
	
	private void renderSprites(double elapsedTime, double t) {
		player.setImage(playerImg.getFrame(t, player.getStatus()));
        player.render( gc );
        
        for (Fireball fireball : fireballList ) {
        	fireball.update(elapsedTime);
            fireball.render( gc );
        }
        
        for (Bat bat : batList ) {
        	bat.update(elapsedTime);
            bat.render( gc );
        }
        
        for (Archer archer : archerList) {
        	archer.update(elapsedTime);
        	if(archer.getShootCooldown() <= 0) {
        		Arrow arrow = archer.shootArrow(player.getPositionX(), player.getPositionY());
        		arrowList.add(arrow);
        	}
        	archer.render( gc );
        }

        for (Teleportation portal : portalList) {
        	portal.render( gc );
        }
        
        for (Potion potion : potionList) {
        	potion.decreaseDespawnTimer(elapsedTime);
        	potion.render( gc );        		
        }
        
        for (Arrow arrow : arrowList) {
        	arrow.update(elapsedTime);
        	arrow.render( gc );
        }
	}
	
	
	private void renderUI() {
		// hotbar
		if(player.getActiveSpell().getSpellName() == "Fireball")
			gc.drawImage(hotbarStateFS, 150, GameProper.WINDOW_HEIGHT-50);
        else if(player.getActiveSpell().getSpellName() == "Teleportation")
        	gc.drawImage(hotbarStateTS, 150, GameProper.WINDOW_HEIGHT-50);
        
		// health, mana, & gold
        // text styling
		gc.setFont( Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 20) );
        gc.setFill(Color.YELLOW);
        
        // gold
        String goldText = "" + player.getGold();
        gc.setStroke( Color.BLACK );
        gc.strokeText(goldText, 36, 79);;
        gc.fillText(goldText, 36, 79);

        // health
        gc.setFill(Color.INDIANRED);
        gc.fillRect(25, 15, ((double) player.getHealth()/player.getMAX_HEALTH())*200.0, 15.0);
        gc.strokeRect(25, 15, 200.0, 15);
        
        // mana
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(25, 40, ((double) player.getMana()/player.getMAX_MANA())*200.0, 15);
        gc.strokeRect(25, 40, 200.0, 15);
        
        // icons (image, position, size)
        gc.drawImage(new Image("HealthIcon.png"), 10, 13, 20, 20);
        gc.drawImage(new Image("ManaIcon.png"), 10, 38, 20, 20);
        gc.drawImage(new Image("Coin.gif"), 13, 63, 15, 20);
	}
	
	
	private void checkIfPlayerDead() {
		if(player.getHealth() <= 0) {		// if player is dead, set game over scene
			this.stop();
			GameOverScene gameOverScene = new GameOverScene();
			gameOverScene.setStage(this.stage);
		}
	}
	
	
	public void setStage(Stage primaryStage) {
		this.stage = primaryStage;
	}
	
	
	private void playWinningScene() {
		this.stop();
		WinningScene winningScene = new WinningScene(player.getGold());
		winningScene.setStage(this.stage);
	}
	
	
	private void checkPlayerMovement() {
		// checks for the specific keyboard inputs and left click
		if (input.contains("A")) {
        	player.addVelocity(-150,0);
        	if(player.getFlip() == 1) player.setPositionX(player.getPositionX()+50);
        	player.setFlip(-1);		// flip image to face left
        }
        if (input.contains("D")) {
            player.addVelocity(150,0);
            if(player.getFlip() == -1) player.setPositionX(player.getPositionX()-50);
        	player.setFlip(1);		// flip image to face right
        }
        if (input.contains("W")) {
            player.addVelocity(0,-150);
        }
        if (input.contains("S")) {
            player.addVelocity(0,150);
    	}
        if (input.contains("UP")) {
            player.viewState();
    	}
        if (input.contains("DIGIT1")) {
        	player.setActiveSpell(0);
        }
        if (input.contains("DIGIT2")) {
        	player.setActiveSpell(1);
        }
        if (input.contains("PRIMARY")) {
        	Spell activeSpell = player.getActiveSpell();
        	if(player.getMana() >= activeSpell.getManaCost() && activeSpell.getCooldown() <= 0) {
        		if(activeSpell.getSpellId() == Spell.FIREBALL_ID) {
	        		Fireball fireball = player.castFireball(mouseX, mouseY);
	        		fireballList.add(fireball);
            	}
            	else if(player.getActiveSpell().getSpellId() == Spell.TELEPORTATION_ID) {
            		Teleportation portal = player.castTeleportation();
            		portalList.add(portal);
            	}
    		}
        	input.remove("PRIMARY");
        }
	}
	
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		gameProper.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
                if ( !input.contains(code) ) {
                    input.add( code );
//                    System.out.println(e.getCode()+" key pressed.");
                }
			}
			
		});
		
		gameProper.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
	        	String code = e.getCode().toString();
	            input.remove( code );;
//	            System.out.println(e.getCode()+" key removed.");
	        }
	    });
    }
	
	
	// method that will listen and handle the mouse click events
	private void handleMouseEvent() {
		gameProper.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				String mouse = e.getButton().toString();
				if ( mouse == "PRIMARY" && !input.contains(mouse) ) {
					input.add( mouse );					
//					System.out.println(mouse+" pressed.");
					mouseX = e.getX();
					mouseY = e.getY();	
				}
			}
        });
	}
}
