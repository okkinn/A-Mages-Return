package scenes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Developers {
	private static final int WINDOW_HEIGHT = Menu.WINDOW_HEIGHT;
	private static final int WINDOW_WIDTH = Menu.WINDOW_WIDTH;
	
	private final String backgroundFile = "DimBG.gif";
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	
	// title
	private Pane gameTitle;
	private Text gameTitleText;
	
	// developers
	private VBox developersItems;
	private VBox developersPane;
	private ImageView developerProfile;
	private Text developersText;
	private Button backBtn;
	
	
	// constructor
	public Developers() {
		this.root = new Pane();
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	
	// methods
	public void setStage(Stage primaryStage) {
		stage = primaryStage;

		// add the created nodes/components to the layout
		addTitle();
		addDevelopers();
		addBackground();
		
		// event handler for button events
		handleButtonEvents();
		
		// set the title, scene, icon, and maximize; display the components; set size as global size
		stage.setTitle("A Mage's Return");
		stage.setScene(this.scene);
		stage.getIcons().add(new Image("Idle2.png"));
		stage.setResizable(false);
		stage.show();
	}
	
	
	private void addTitle() {
		gameTitle = new Pane();
		gameTitleText = new Text("A Mage's Return");
		
		// design
		gameTitleText.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 64));
		gameTitleText.setFill(Color.YELLOW);
		gameTitleText.setEffect(new DropShadow());
		
		// set position
		gameTitle.setTranslateX(50);
		gameTitle.setTranslateY(125);
		 
		// add to group; add to root
		gameTitle.getChildren().addAll(gameTitleText);
		root.getChildren().addAll(gameTitle);
	}
	
	
	private void addDevelopers() {
		developersItems = new VBox(10);
		
		// for text
		developersPane = new VBox(20);
		developersPane.setAlignment(Pos.CENTER);
		
		// developer picture
		developerProfile = new ImageView(new Image("DeveloperProfile.jpg"));
		developerProfile.setFitHeight(150);
		developerProfile.setFitWidth(150);
		
		// for text
		developersText = new Text("John Nico T. De Castro is a passionate and driven student with a strong foundation in programming logic. He is currently studying at the University of the Philippines Los Baños as a 2nd year undergraduate computer science student. With interests in cybersecurity and data science and proficiency in several programming languages such as Java, C, python, and R, Nico is committed to deploying high-quality and user-friendly software solutions for users.");

		// text styling
		developersText.setFont(Font.font("Times New Roman", 14));
		developersText.setFill(Color.YELLOW);
		developersText.setEffect(new DropShadow());
		developersText.setWrappingWidth(400);
		developersText.setTextAlignment(TextAlignment.JUSTIFY);
		
		// add profile and text to pane
		developersPane.getChildren().addAll(developerProfile, developersText);
		
		// for button
		backBtn = new Button("Back to Main Menu");
		
		// set button designs
		backBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		backBtn.setEffect(new DropShadow());
		backBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		
		// set position of menuItems
		developersItems.setTranslateX(140);
		developersItems.setTranslateY(190);

		// add to aboutItems pane; align to center
		developersItems.getChildren().addAll(developersPane,backBtn);
		developersItems.setAlignment(Pos.CENTER);
		
		// add to root
		root.getChildren().add(developersItems);
	}
	
	
	private void addBackground() {
		root.setBackground(new Background(new BackgroundImage(new Image(backgroundFile, WINDOW_WIDTH, WINDOW_HEIGHT, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
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