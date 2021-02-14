package gui.statisicsFrame;



import customizedClasses.CustomizedStage;
import database.ado.Player;
import gui.statisicsFrame.statisticsPanes.PlayerAnalysisLeftContent;
import gui.statisicsFrame.statisticsPanes.PlayerAnalysisRightContent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class PlayerAnalysisFrame extends CustomizedStage {

	BorderPane backgroundPane = new BorderPane();
	
	public PlayerAnalysisFrame(Player player) {
	
		backgroundPane.setRight(new PlayerAnalysisRightContent(player));
		backgroundPane.setLeft(new PlayerAnalysisLeftContent(player));
		
		setScene(new Scene(backgroundPane));
		setFullScreen(false);
		setSizeToCompleteScreen();
		setTitle("Spielerdaten von " + player.getName());
	}

	
	
}
