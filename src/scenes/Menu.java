package scenes;

import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;


public class Menu {
	public static int WINDOW_WIDTH = 700;
	public static int WINDOW_HEIGHT = 600;
	
	private final String backgroundBG = "MainBG.gif";
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	
	// title
	private Pane gameTitle;
	private Text gameTitleText;
	
	// menu
	private VBox menuItems;
	private Button newGameBtn;
	private Button aboutBtn;
	private Button developerBtn;
	private Button exitBtn;
	
	
	public Menu() {
		this.root = new Pane();
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
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
	
	
	private void addMenu() {
		menuItems = new VBox(20);
		newGameBtn = new Button("New Game");
		aboutBtn = new Button("About");
		developerBtn = new Button("Developer");
		exitBtn = new Button("Exit");
		
		// set button designs
		newGameBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		newGameBtn.setEffect(new DropShadow());
		newGameBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		aboutBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		aboutBtn.setEffect(new DropShadow());
		aboutBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		developerBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		developerBtn.setEffect(new DropShadow());
		developerBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		exitBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		exitBtn.setEffect(new DropShadow());
		exitBtn.setFont(Font.loadFont(getClass().getResource("AncientModernTales.ttf").toExternalForm(), 30));
		
		// add to menuItems pane; align to center
		menuItems.getChildren().addAll(newGameBtn, aboutBtn, developerBtn, exitBtn);
		menuItems.setAlignment(Pos.CENTER);
		
		// set position of menuItems
		menuItems.setTranslateX(95); // 115
		menuItems.setTranslateY(200);
		
		// add to root
		root.getChildren().add(menuItems);
	}
	
	
	private void addBackground() {
		root.setBackground(new Background(new BackgroundImage(new Image(backgroundBG, WINDOW_WIDTH, WINDOW_HEIGHT, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}

   
	public void setStage(Stage primaryStage) {
		stage = primaryStage;

		// add the created nodes/components to the layout
		addTitle();
		addMenu();
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
	
	private void handleButtonEvents() {
		// button hover
		newGameBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	newGameBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: white;");
		        }
		});
			
		newGameBtn.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	newGameBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		        }
		});
		
		aboutBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	aboutBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: white;");
		        }
		});
			
		aboutBtn.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	aboutBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		        }
		});
			
		developerBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	developerBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: white;");
		        }
		});
			
		developerBtn.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	developerBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		        }
		});
		
		exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	exitBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: white;");
		        }
		});
			
		exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		        	exitBtn.setStyle("-fx-background-radius: 0; -fx-padding: 0; -fx-background-color: transparent; -fx-text-fill: yellow;");
		        }
		});
		
		
		// button clicks
		newGameBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("Starting new game...");
				GameProper gameproper = new GameProper();
				gameproper.setStage(stage);
			}
		});
		
		aboutBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("Showing about info...");
				About about = new About();
				about.setStage(stage);
			}
		});
		
		developerBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("Showing developer info...");
				Developers developers = new Developers();
				developers.setStage(stage);
			}
		});
		
		exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("Thank you for playing! Goodbye!");
				System.exit(0);
			}
		});
	}
}
