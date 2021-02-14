package gui.calculatorFrame.center;

import java.sql.SQLException;
import java.util.Date;

import org.controlsfx.dialog.ExceptionDialog;
import org.controlsfx.glyphfont.Glyph;

import customizedClasses.Time;
import database.ado.Match;
import database.ado.Player;
import gui.calculatorFrame.CalculatorFrame;
import gui.calculatorFrame.EndGameReasonFrame;
import gui.calculatorFrame.logic.TimerTask;
import gui.mainframe.MainFrame;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalculatorCenterBorderPane extends BorderPane {

	public static final int GAME_OVER = 0;
	public static final int COIN = 1;
	public static final int CUBE = 2;

	private HBox hboxTimeView = new HBox();
	public static Label timeLabel = new Label("Spielzeit: "); 
	private Label spaceLabelTimer = new Label("  ");
	private Label spaceLabelTimer2 = new Label("  ");
	private Button pauseButton = new Button("", new Glyph("FontAwesome", "Pause"));
	private Button runButton = new Button("", new Glyph("FontAwesome", "PLAY"));
	private Button stopButton = new Button("", new Glyph("FontAwesome", "STOP"));
	private String timeStirng = "00:00";

	StackPane centerStackPane = new StackPane();

	Button mainframeButton = new Button("Hauptmenü");
	Button newGameButton = new Button("Neues Spiel");
	Button showStatisticsButton = new Button("Statistiken");

	Label labelWinner = new Label("Gewinner");
	Label labelLoser = new Label("Verlierer");

	Label labelWinnerName = new Label();
	Label labelLoserName = new Label();

	VBox winnerLoserBox = new VBox();	
	ButtonBar buttonBar = new ButtonBar();

	public static TimerTask timer;

	public CalculatorCenterBorderPane() {

		timeLabel.setText(timeLabel.getText() + timeStirng);
		timeLabel.setMinWidth(165);
		timeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		hboxTimeView.getChildren().addAll(timeLabel, pauseButton, spaceLabelTimer2, runButton, 
				spaceLabelTimer, stopButton);
		hboxTimeView.setPadding(new Insets(10, 0, 0, 290));

		int barButtonHeight = 35;
		int barButtonWidth = 350;
		mainframeButton.setMinSize(barButtonWidth, barButtonHeight);
		mainframeButton.setMaxSize(barButtonWidth, barButtonHeight);
		newGameButton.setMinSize(barButtonWidth, barButtonHeight);
		newGameButton.setMaxSize(barButtonWidth, barButtonHeight);
		showStatisticsButton.setMinSize(barButtonWidth, barButtonHeight);
		showStatisticsButton.setMaxSize(barButtonWidth, barButtonHeight);

		buttonBar.getButtons().addAll(mainframeButton, newGameButton, showStatisticsButton);
		buttonBar.setPadding(new Insets(0, 300, 70, 0));
		buttonBar.setVisible(false);

		labelWinner.setFont(Font.font("Arial", FontWeight.BOLD, 35));
		labelWinnerName.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		labelLoser.setFont(Font.font("Arial", FontWeight.BOLD, 35));
		labelLoserName.setFont(Font.font("Arial", FontWeight.BOLD, 30));

		labelWinner.setTextFill(Paint.valueOf("#00f000"));
		labelWinnerName.setTextFill(Paint.valueOf("#000000"));
		labelLoser.setTextFill(Paint.valueOf("#c50000"));
		labelLoserName.setTextFill(Paint.valueOf("#000000"));

		winnerLoserBox.getChildren().addAll(labelWinner, labelWinnerName, labelLoser, labelLoserName);
		winnerLoserBox.setPadding(new Insets(50, 0, 0, 350));
		winnerLoserBox.setVisible(false);

		setTop(hboxTimeView);
		setCenter(winnerLoserBox);
		setBottom(buttonBar);


		showStatisticsButton.setDisable(true);

		mainframeButton.setOnAction(e -> {
			MainFrame.currentCalculatorFrame.resetGame();
			MainFrame.primaryStage.show();
			MainFrame.currentCalculatorFrame.close();
		});
		newGameButton.setOnAction(e -> {
			MainFrame.currentCalculatorFrame.newGame();
		});

		runButton.setOnAction(e -> {
			timer = new TimerTask();
			timeLabel.textProperty().bind(Bindings.concat(timer.messageProperty()));
			Thread thread = new Thread(timer);
			thread.start();
		});
		pauseButton.setOnAction(e -> {
			if (timer != null) {
				timer.setRunning(false);				
			}
		});
		stopButton.setOnAction(e -> {
			if (timer != null) {				
				timer.setRunning(false);
			}
			EndGameReasonFrame endGameReasonFrame = new EndGameReasonFrame();
			endGameReasonFrame.show();
		});

	}

	public void showCenterInformation(int winningReasonId){
		showGameEnded(winningReasonId);
	}
	private void showGameEnded(int winningReasonId) {
		buttonBar.setVisible(true);
		setWinnerLoserNames(CalculatorFrame.choosePlayerFrame.getSelectedPlayers(), winningReasonId);
		winnerLoserBox.setVisible(true);
		if (timer != null) {			
			timer.setRunning(false);
		}
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}


	public void setWinnerLoserNames(Player[] selectedPlayers, int winningReasonId){
		String playtime = getTimeStringForInsert();
		selectedPlayers[0].addPlaytime(playtime);
		selectedPlayers[1].addPlaytime(playtime);
		int winner = 0;
		if (selectedPlayers[0].isWinner()) {			
			labelWinnerName.setText(selectedPlayers[0].getName());
			labelLoserName.setText(selectedPlayers[1].getName());
			selectedPlayers[0].addWins();
			selectedPlayers[0].update();
			selectedPlayers[1].addLost();
			selectedPlayers[1].update();
			winner = 1;
		}else {
			labelWinnerName.setText(selectedPlayers[1].getName());
			labelLoserName.setText(selectedPlayers[0].getName());
			selectedPlayers[1].addWins();
			selectedPlayers[1].update();
			selectedPlayers[0].addLost();
			selectedPlayers[0].update();
			winner = 2;
		}
		selectedPlayers[0].setWinner(true);
		selectedPlayers[1].setWinner(true);
		Date insertDate = new Date();
		Match match = new Match(selectedPlayers[0].getId(), selectedPlayers[1].getId(), new Time(playtime), winner, new java.sql.Date(insertDate.getTime()),
				selectedPlayers[0].getRemainingLifePoints(), selectedPlayers[1].getRemainingLifePoints(), MainFrame.currentCalculatorFrame.cubes, MainFrame.currentCalculatorFrame.coins, winningReasonId);
		try {
			match.insertNewMatch();
		} catch (SQLException e) {
			ExceptionDialog dialog = new ExceptionDialog(e);
			dialog.show();
		}
	}


	private String getTimeStringForInsert() {
		String s = "00:00:00";
		if (timer != null) {
			timer.setRunning(false);
			String string = timer.getTimeAsString().substring(11);
			String[] values = string.split(":");
			if (values.length == 3) {			
				s = "0" + values[0] + ":" + values[1] + ":" + values[2];
			}else {
				s = "00:" + values[0] + ":" + values[1];
			}
		}
		System.out.println("getTimeStringForInsert() reutrns " + s);
		return s;
	}

	public void finalize() {
		try {
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		timeLabel.textProperty().unbind();
		timeLabel.setText("Spielzeit: 00:00");
		buttonBar.setVisible(false);
		winnerLoserBox.setVisible(false);
	}
}
