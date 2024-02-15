package scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WinningScene {
	private static final int WINDOW_HEIGHT = Menu.WINDOW_HEIGHT;
	private static final int WINDOW_WIDTH = Menu.WINDOW_WIDTH;
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	
	private int goldCollected;
	
	// text
	private Pane winTextPane, endTextPane;
	private VBox thankYouTextVBox;
	private Text winText, endText, thankYouText, goldText;
	
	// backBtn
	private Button backBtn;
	
	
	public WinningScene(int gold) {
		this.goldCollected = gold;
		this.root = new Pane();
		this.root.setStyle("-fx-background-color: black");
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	
	// set background of GameOverScene
	private void addBackground() {
		root.setBackground(new Background(new BackgroundImage(new Image("WinningSceneBG.jpg", WINDOW_WIDTH, WINDOW_HEIGHT, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}
	
	
	private void addText() {
		// initialize pane and text node
		winTextPane = new Pane();
		winText = new Text("The Mage Has Teleported...");
		
		// text design
		winText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 64));
		winText.setFill(Color.YELLOW);
		winText.setEffect(new DropShadow());
		
		// add game title text to game title pane
		winTextPane.getChildren().addAll(winText);
		
		winTextPane.setTranslateX(65);
		winTextPane.setTranslateY(125);
		
		// ending animation text
		endTextPane = new Pane();
		endText = new Text("Home?");
		// text design
		endText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 64));
		endText.setFill(Color.YELLOW);
		endText.setEffect(new DropShadow());
		// set position
		endText.setTranslateX(70);
		endText.setTranslateY(125);
		// add to pane & hide
		endTextPane.getChildren().addAll(endText);
		endTextPane.setVisible(false);
	
		// thank you & gold text
		thankYouTextVBox = new VBox();
		thankYouText = new Text("Thanks for Playing!");
		goldText = new Text("You have collected " + goldCollected + " gold!");
		// text design
		thankYouText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 64));
		thankYouText.setFill(Color.YELLOW);
		thankYouText.setEffect(new DropShadow());
		goldText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 24));
		goldText.setFill(Color.YELLOW);
		goldText.setEffect(new DropShadow());
		// set position
		thankYouTextVBox.setTranslateX(50);
		thankYouTextVBox.setTranslateY(75);
		// add to pane & hide
		thankYouTextVBox.getChildren().addAll(thankYouText, goldText);
		thankYouTextVBox.setVisible(false);
		
		// add to root
		root.getChildren().addAll(winTextPane, endTextPane, thankYouTextVBox);
		
	}
	
	
	private void addBackBtn() {
		backBtn = new Button("Back to Main Menu");

		// set button designs
		backBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		backBtn.setEffect(new DropShadow());
		backBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		
		// set position
		backBtn.setTranslateX(450);
		backBtn.setTranslateY(GameProper.WINDOW_HEIGHT - 80);
		
		// add to root
		root.getChildren().add(backBtn);
		
		// hide initially
		backBtn.setVisible(false);
	}
	
	
	public void setStage(Stage primaryStage) {
		stage = primaryStage;

		// add the created nodes/components to the layout
		addText();
		addBackBtn();
		
		// sprites for the ending animation
		Image portal = new Image("Portal.gif", 300, 300, true, true);
		Image idle = new Image("Idle.gif", 75, 75, true, true);
		Image angry = new Image("Stomping.gif", 75, 75, true, true);
		ImageView playerCharacter = new ImageView(idle);
		ImageView portalIV = new ImageView(portal);
		playerCharacter.setVisible(false);
		portalIV.setVisible(false);
		
		// set positions
		playerCharacter.setTranslateX(175);
		playerCharacter.setTranslateY(450);
		portalIV.setTranslateX(315);
		portalIV.setTranslateY(200);
		
		// for the short ending animation
		new Timeline(
			// first scene
			new KeyFrame(Duration.ZERO, e -> portalIV.setVisible(true)),
			
			// second scene
			new KeyFrame(Duration.millis(2500), e -> addBackground() ),
			new KeyFrame(Duration.millis(2500), e -> endTextPane.setVisible(true) ),
			new KeyFrame(Duration.millis(2500), e -> playerCharacter.setVisible(true) ),
			new KeyFrame(Duration.millis(2500), e -> winTextPane.setVisible(false) ),
			new KeyFrame(Duration.millis(2500), e -> portalIV.setVisible(false) ),
			
			// third scene
			new KeyFrame(Duration.millis(7000), e -> playerCharacter.setImage(angry)),
			new KeyFrame(Duration.millis(7000), e -> thankYouTextVBox.setVisible(true) ),
			new KeyFrame(Duration.millis(7000), e -> backBtn.setVisible(true) ),
			new KeyFrame(Duration.millis(7000), e -> endTextPane.setVisible(false) )
				).play();
		
		this.root.getChildren().addAll(portalIV, playerCharacter);
		
		// event handler for button events
		handleButtonEvents();
		
		// set the title, scene, icon, and maximize; display the components; set size as global size
		stage.setTitle("A Mage's Return");
		stage.setScene(this.scene);
		stage.getIcons().add(new Image("Idle2.png"));
		stage.setResizable(false);
		stage.show();
	}
	
	
	// change button color on mouse hover and handle mouse click event
	private void handleButtonEvents() {
		backBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	backBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: white;");
		        }
		});
			
		backBtn.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	backBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		        }
		});
		
		backBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("Returning to Menu...");
				Menu menu = new Menu();
				menu.setStage(stage);
			}
		});
	}
}
