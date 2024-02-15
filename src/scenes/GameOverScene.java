package scenes;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class GameOverScene {
	private static final int WINDOW_HEIGHT = Menu.WINDOW_HEIGHT;
	private static final int WINDOW_WIDTH = Menu.WINDOW_WIDTH;
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	
	// death text
	private Pane deathTextPane;
	private Text deathText;
	
	// backBtn
	private Button backBtn;
		
	
	// constructor
	public GameOverScene() {
		this.root = new Pane();
		this.root.setStyle("-fx-background-color: black");
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	
	// methods
	public void setStage(Stage primaryStage) {
		stage = primaryStage;

		// add the created nodes/components to the layout
		addDeathText();
		addBackBtn();
		
		// for dying animation
		Image dying = new Image("Dying.gif", 150, 150, true, true);
		Image dying6 = new Image("Dying6.png", 150, 150, true, true);
		Image decomposing = new Image("Decomposing.gif", 150, 150, true, true);
		ImageView playerCharacter = new ImageView();
		
		// set position
		playerCharacter.setTranslateX(260);
		playerCharacter.setTranslateY(GameProper.WINDOW_HEIGHT - 325);
		
		// animation proper
		new Timeline(
			// dying animation
			new KeyFrame(Duration.ZERO, new KeyValue(playerCharacter.imageProperty(), dying)),
			// static dying image
			new KeyFrame(Duration.millis(1200), new KeyValue(playerCharacter.imageProperty(), dying6)),
			// decomposing animation
			new KeyFrame(Duration.millis(2100), new KeyValue(playerCharacter.imageProperty(), decomposing))
				).play();
		
		// add to root
		this.root.getChildren().add(playerCharacter);
		
		// event handler for button events
		handleButtonEvents();
		
		// set the title, scene, icon, and maximize; display the components; set size as global size
		stage.setTitle("A Mage's Return");
		stage.setScene(this.scene);
		stage.getIcons().add(new Image("Idle2.png"));
		stage.setResizable(false);
		stage.show();
	}
	
	private void addDeathText() {
		deathTextPane = new Pane();
		deathText = new Text("The Mage Has Fallen");
		
		// text styling
		deathText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 64));
		deathText.setFill(Color.YELLOW);
		deathText.setEffect(new DropShadow());
		
		// set position
		deathTextPane.setTranslateX(125);
		deathTextPane.setTranslateY(125);

		// add game title text to game title pane
		deathTextPane.getChildren().addAll(deathText);
		
		// add to root
		root.getChildren().addAll(deathTextPane);
	}
	
	
	private void addBackBtn() {
		backBtn = new Button("Back to Main Menu");

		// button styling
		backBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		backBtn.setEffect(new DropShadow());
		backBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		
		// set position
		backBtn.setTranslateX(250);
		backBtn.setTranslateY(GameProper.WINDOW_HEIGHT - 100);
		
		// add to root
		root.getChildren().add(backBtn);
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