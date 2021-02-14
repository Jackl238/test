package gui.statisicsFrame;

import org.controlsfx.glyphfont.Glyph;

import customizedClasses.CustomizedStage;
import gui.mainframe.MainFrame;
import gui.statisicsFrame.statisticsPanes.GeneralContent;
import gui.statisicsFrame.statisticsPanes.MatchContent;
import gui.statisicsFrame.statisticsPanes.PlayedTimesOfPlayersContent;
import gui.statisicsFrame.statisticsPanes.TimeOfLastMatchesContent;
import gui.statisicsFrame.statisticsPanes.WinLostContent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class StatisticsFrame extends CustomizedStage {

	BorderPane backPane = new BorderPane();
	TabPane tabPane = new TabPane();
	//Hier werden allgemeine Infos angezeigt
	//Anzahl gesamt gespielter Spiele, Gesamte Spielzeit, Spieler mit meisten Spielen/ Wins/ Losts,...
	Tab general = new Tab("Allgemein"); 
	//Jeder Spieler oder Match wird hier mit seinen Werten aufgeführt
	Tab winLostOverview = new Tab("Wins/Losts");
	Tab playedMatches = new Tab("Matches");
	Tab timeOfLastMatches = new Tab("Match - Zeitübersicht");
	Tab playedTimesOfPlayers = new Tab("Spieler - Zeitübersicht");
	//TODO: StatistikContent über Gewinngründe
	//TODO: Gewinnquote in Prozent bei Sieleranalyse
	//TODO: Tabellarische übsersicht in Sieleranalyse mit Gewinngründen
	Button backButton = new Button("Hauptmenü", new Glyph("FontAwesome", "ARROW_LEFT"));
	Button refreshButton = new Button("Daten laden", new Glyph("FontAwesome", "REFRESH"));
	HBox bottomButtonBox = new HBox();
	
	public StatisticsFrame() {
		bottomButtonBox.setPadding(new Insets(10, 5, 10, 10));
		bottomButtonBox.getChildren().addAll(backButton, new Label("  "), refreshButton);
		refreshButton.setDisable(true);
		
		general.setClosable(false);
		winLostOverview.setClosable(false);
		playedMatches.setClosable(false);
		timeOfLastMatches.setClosable(false);
		playedTimesOfPlayers.setClosable(false);
		
		timeOfLastMatches.setContent(new TimeOfLastMatchesContent());
		playedTimesOfPlayers.setContent(new PlayedTimesOfPlayersContent());
		general.setContent(new GeneralContent());
		winLostOverview.setContent(new WinLostContent());
		playedMatches.setContent(new MatchContent());
		
		tabPane.getTabs().addAll(general, winLostOverview, playedMatches, timeOfLastMatches, 
				playedTimesOfPlayers);
		
		backPane.setCenter(tabPane);
		backPane.setBottom(bottomButtonBox);
		
		setFullScreen(false);
		setSizeToCompleteScreen();
		setScene(new Scene(backPane));
	
		backButton.setOnAction(e -> {
			MainFrame.primaryStage.show();
			close();
		});
	}
	
}
