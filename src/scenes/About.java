package scenes;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class About {
	private static final int WINDOW_HEIGHT = Menu.WINDOW_HEIGHT;
	private static final int WINDOW_WIDTH = Menu.WINDOW_WIDTH;
	
	private final String backgroundFile = "DimBG.gif";
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	
	// title
	private Pane gameTitle;
	private Text gameTitleText;
	
	// about
	private TextArea textArea;
	private Button backBtn;
	
		
	public About() {
		this.root = new Pane();
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.scene.getStylesheets().add("AboutStylesheet.css");
	}
	
	private void addTitle() {
		gameTitle = new Pane();
		gameTitleText = new Text("A Mage's Return");
		gameTitleText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 64));
		gameTitleText.setFill(Color.YELLOW);
		gameTitleText.setEffect(new DropShadow());
		// add game title text to game title pane
		gameTitle.getChildren().addAll(gameTitleText);
		
		gameTitle.setTranslateX(50);
		gameTitle.setTranslateY(125);
		// add to root
		root.getChildren().addAll(gameTitle);
	}
	
	
	private void addAbout() {
		textArea = new TextArea();
		backBtn = new Button("Back to Main Menu");
		
		// set textArea properties
		textArea.setWrapText(true);
		textArea.setEditable(false);
		textArea.setFont(Font.font("Times New Roman", 14));
		textArea.setEffect(new DropShadow());
		textArea.setPrefHeight(320);
		textArea.setPrefWidth(570);
		
		// set button designs
		backBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		backBtn.setEffect(new DropShadow());
		backBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		
		// add text
		String aboutText = "Embark on a magical journey in \"A Mage's Return,\" a shooter action game that follows the story of " 
				+ "a mage transported to an unfamiliar world riddled with various enemies. The mage's ultimate goal is to amass "
				+ "enough magical energy in order to cast a powerful portal spell to return to his home world."
				+ "\n\nUse W, A, S, D for movement controls, number keys 1 and 2 to select spells, and left click to cast them."
				+ "\nFireball spell costs 1 mana and the teleportation spell to go home costs 50 mana!"
				+ "\nYou gain gold by defeating enemies. They also drop health and mana potions."
				+ "\n\nReferences\n"
				+ "https://docs.oracle.com/javafx/2/api/\r\n"
				+ "https://docs.oracle.com/javase/8/javafx/api/overview-summary.html\r\n"
				+ "https://stackoverflow.com/questions/28382371/how-to-set-a-text-inside-a-button-to-drop-the-shadow-in-javafx\r\n"
				+ "https://stackoverflow.com/questions/23325488/add-timer-for-images-in-javafx\r\n"
				+ "https://stackoverflow.com/questions/33620587/how-to-include-resource-folders-in-javafx-deployment\r\n"
				+ "https://stackoverflow.com/questions/21936585/transparent-background-of-a-textarea-in-javafx-8\r\n"
				+ "https://gamedev.stackexchange.com/questions/168035/javafx-how-do-i-create-a-health-bar\r\n"
				+ "https://www.youtube.com/watch?v=N2EmtYGLh4U\r\n"
				+ "\r\n"
				+ "Font\r\n"
				+ "https://www.dafont.com/ancient-modern-tales.font\r\n"
				+ "\r\n"
				+ "Image Sources\r\n"
				+ "Backgrounds\r\n"
				+ "Menu Background\r\n"
				+ "https://www.deviantart.com/zedotagger/art/FF-Black-Mage-648585416\r\n"
				+ "\r\n"
				+ "Map/Tileset\r\n"
				+ "https://ansimuz.itch.io/patreons-top-down-collection\r\n"
				+ "\r\n"
				+ "Winning Scene Background\r\n"
				+ "https://medium.com/ph-d-stories/castles-and-dreams-or-robots-28cddc08207f?responsesOpen=true&sortBy=REVERSE_CHRON\r\n"
				+ "\r\n"
				+ "Sprites\r\n"
				+ "Mage Player\r\n"
				+ "https://greenpixels.itch.io/pixel-art-assets-5\r\n"
				+ "\r\n"
				+ "Bat\r\n"
				+ "https://elthen.itch.io/bat-sprite-pack\r\n"
				+ "\r\n"
				+ "Arcane Archer\r\n"
				+ "https://astrobob.itch.io/arcane-archer\r\n"
				+ "\r\n"
				+ "Fireball\r\n"
				+ "https://nyknck.itch.io/pixelarteffectfx017\r\n"
				+ "\r\n"
				+ "Teleportation Portal\r\n"
				+ "https://pimen.itch.io/magical-animation-effects\r\n"
				+ "\r\n"
				+ "Hotbar\r\n"
				+ "https://mounirtohami.itch.io/pixel-art-gui-elements\r\n"
				+ "\r\n"
				+ "Health & Mana Icons\r\n"
				+ "https://hochupitsu.itch.io/heart-and-mana\r\n"
				+ "\r\n"
				+ "Coin\r\n"
				+ "https://laredgames.itch.io/gems-coins-free\r\n"
				+ "\r\n"
				+ "Potions\r\n"
				+ "https://admurin.itch.io/admurins-potions\r\n";
		textArea.setText(aboutText);
        
		// set positions
        textArea.setTranslateX(50);
        textArea.setTranslateY(175);
		backBtn.setTranslateX(230);
		backBtn.setTranslateY(500);
		
		// add to root
		root.getChildren().addAll(textArea, backBtn);
	}
	
	
	private void addBackground() {
		root.setBackground(new Background(new BackgroundImage(new Image(backgroundFile, WINDOW_WIDTH, WINDOW_HEIGHT, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}
	
	
	public void setStage(Stage primaryStage) {
		stage = primaryStage;

		// add the created nodes/components to the layout
		addBackground();
		addTitle();
		addAbout();
		
		// event handler for button events
		handleButtonEvents();
		
		// set the title, scene, icon, and maximize; display the components; set size as global size
		stage.setTitle("A Mage's Return");
		stage.setScene(this.scene);
		stage.getIcons().add(new Image("Idle2.png"));
		stage.setResizable(false);
		stage.show();
	}
	
	
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
