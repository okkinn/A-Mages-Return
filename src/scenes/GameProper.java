package scenes;

import application.GameTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameProper {
	public static final int WINDOW_HEIGHT = Menu.WINDOW_HEIGHT;
	public static final int WINDOW_WIDTH = Menu.WINDOW_WIDTH;
	
	private Stage stage;
	private Scene scene;
	private Pane root;
	
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gameTimer;
	
	public GameProper() {
		this.root = new Pane();
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gameTimer = new GameTimer(this.gc, this.scene);
	}

	
	private void addBackground() {
		root.setBackground(new Background(new BackgroundImage(new Image("Map.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}
	
	
	public void setStage(Stage primaryStage) {
		this.stage = primaryStage;
		
		this.root.getChildren().add(canvas);
		addBackground();
		
		this.stage.setTitle("A Mage's Return");
		this.stage.setScene(this.scene);
		//invoke the start method of the animation timer
		this.gameTimer.setStage(this.stage);
		this.gameTimer.start();
		this.stage.show();
	}
	
	
}
