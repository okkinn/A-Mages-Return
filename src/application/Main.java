/***********************************************************
 * 	
 * CMSC 22 Project - A Mage's Return
 * 
 * Embark on a magical journey in "A Mage's Return," an 
 * shooter action game that follows the story of a mage 
 * transported to an unfamiliar world riddled with various 
 * enemies. The mage's ultimate goal is to amass enough 
 * magical energy in order to cast a powerful portal spell 
 * to return to his home world.
 * 
 * A Mini-Project for CMSC 22
 * 
 * @author Nico De Castro
 * @created_date 2023-12-23 23:18
 * 
 * 
 * References
 * https://docs.oracle.com/javafx/2/api/
 * https://docs.oracle.com/javase/8/javafx/api/overview-summary.html
 * https://stackoverflow.com/questions/28382371/how-to-set-a-text-inside-a-button-to-drop-the-shadow-in-javafx
 * https://stackoverflow.com/questions/23325488/add-timer-for-images-in-javafx
 * https://stackoverflow.com/questions/33620587/how-to-include-resource-folders-in-javafx-deployment
 * https://stackoverflow.com/questions/21936585/transparent-background-of-a-textarea-in-javafx-8
 * https://gamedev.stackexchange.com/questions/168035/javafx-how-do-i-create-a-health-bar
 * https://www.youtube.com/watch?v=N2EmtYGLh4U
 * 
 * Font
 * https://www.dafont.com/ancient-modern-tales.font
 * 
 * Image Sources
 * Backgrounds
 * Menu Background
 * https://www.deviantart.com/zedotagger/art/FF-Black-Mage-648585416
 * 
 * Map/Tileset
 * https://ansimuz.itch.io/patreons-top-down-collection
 * 
 * Winning Scene Background
 * https://medium.com/ph-d-stories/castles-and-dreams-or-robots-28cddc08207f?responsesOpen=true&sortBy=REVERSE_CHRON
 * 
 * Sprites
 * Mage Player
 * https://greenpixels.itch.io/pixel-art-assets-5
 * 
 * Bat
 * https://elthen.itch.io/bat-sprite-pack
 * 
 * Arcane Archer
 * https://astrobob.itch.io/arcane-archer
 * 
 * Fireball
 * https://nyknck.itch.io/pixelarteffectfx017
 * 
 * Teleportation Portal
 * https://pimen.itch.io/magical-animation-effects
 * 
 * Hotbar
 * https://mounirtohami.itch.io/pixel-art-gui-elements
 * 
 * Health & Mana Icons
 * https://hochupitsu.itch.io/heart-and-mana
 * 
 * Coin
 * https://laredgames.itch.io/gems-coins-free
 * 
 * Potions
 * https://admurin.itch.io/admurins-potions
 * 
 * 
 ***********************************************************/

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.Menu;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		Menu menu = new Menu();
		menu.setStage(primaryStage);
	}

}
