package gui.calculatorFrame;

import customizedClasses.CustomizedStage;
import database.ado.Player;
import database.ado.WinningReason;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class EndGameReasonFrame extends CustomizedStage{

	GridPane pane = new GridPane();
	
	Player[] activePlayer = CalculatorFrame.choosePlayerFrame.getSelectedPlayers();
	ComboBox<Player> winnerBox = new ComboBox<>(FXCollections.observableArrayList(
			activePlayer[0], activePlayer[1]));
	ComboBox<WinningReason> reasonBox = new ComboBox<>(WinningReason.getAllDataAsObservableListWithoutFirstElement());
	
	
	Button okButton = new Button("Spiel beenden");
	
	Label labelWinner = new Label("Gewinner: ");
	Label labelReason = new Label("Grund: ");
	Label labelError = new Label("");
	
	public EndGameReasonFrame() {
		reasonBox.setMinWidth(ComboBox.USE_COMPUTED_SIZE);
		winnerBox.setMinWidth(ComboBox.USE_COMPUTED_SIZE);
		
		labelError.setTextFill(Paint.valueOf("#f00000"));
		
		pane.add(labelWinner, 0, 1);
		pane.add(winnerBox, 1, 1);
		pane.add(labelReason, 0, 2);
		pane.add(reasonBox, 1, 2);
		pane.add(labelError, 0, 3);
		pane.add(okButton, 0, 4);
		
		setConstraints();
		
		okButton.setOnAction(e -> {
			if (winnerBox.getSelectionModel().getSelectedIndex() == -1) {
				labelError.setText("Bitte einen Gewinner auswählen");
			}else if (reasonBox.getSelectionModel().getSelectedIndex() == -1) {
				labelError.setText("Bitte einen Gewinngrund auswählen");
			}else {
				labelError.setText("");
				
				if (winnerBox.getSelectionModel().getSelectedIndex() == 0) {
					winnerBox.getItems().get(1).setWinner(false);
				} else {
					winnerBox.getItems().get(0).setWinner(false);
				}
				
				CalculatorFrame.centerPane.showCenterInformation(
						reasonBox.getSelectionModel().getSelectedItem().getId());
				close();
			}
		});
		
		setScene(new Scene(pane));
		setFullScreen(false);
		setResizable(false);
		setSize(400, 200);
		setTitle("Spielerauswahl");
		setAlwaysOnTop(true);
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL); 
	}
	
	private void setConstraints() {
		
		int insets = 10;

		GridPane.setConstraints(
				labelWinner,            // Knotenelement
				0,               // Spaltenindex
				1,               // Zeilenindex
				1,               // Anzahl überspannter Spalten
				1,               // Anuahl überspannter Zeilen
				HPos.CENTER,     // horizonale Anordnung
				VPos.BOTTOM,     // vertikale Anordnung
				Priority.ALWAYS, // horizontale Ausdehnung
				Priority.ALWAYS, // vertikale Ausdehnung
				new Insets(insets)   // Abstand des Inhaltes zum Zellenrand
				);
		GridPane.setConstraints(
				winnerBox,            // Knotenelement
				1,               // Spaltenindex
				1,               // Zeilenindex
				1,               // Anzahl überspannter Spalten
				1,               // Anuahl überspannter Zeilen
				HPos.CENTER,     // horizonale Anordnung
				VPos.BOTTOM,     // vertikale Anordnung
				Priority.ALWAYS, // horizontale Ausdehnung
				Priority.ALWAYS, // vertikale Ausdehnung
				new Insets(insets)   // Abstand des Inhaltes zum Zellenrand
				);

		GridPane.setConstraints(
				labelReason,            // Knotenelement
				0,               // Spaltenindex
				2,               // Zeilenindex
				1,               // Anzahl überspannter Spalten
				1,               // Anuahl überspannter Zeilen
				HPos.CENTER,     // horizonale Anordnung
				VPos.BOTTOM,     // vertikale Anordnung
				Priority.ALWAYS, // horizontale Ausdehnung
				Priority.ALWAYS, // vertikale Ausdehnung
				new Insets(insets)   // Abstand des Inhaltes zum Zellenrand
				);
		GridPane.setConstraints(
				reasonBox,            // Knotenelement
				1,               // Spaltenindex
				2,               // Zeilenindex
				1,               // Anzahl überspannter Spalten
				1,               // Anuahl überspannter Zeilen
				HPos.CENTER,     // horizonale Anordnung
				VPos.BOTTOM,     // vertikale Anordnung
				Priority.ALWAYS, // horizontale Ausdehnung
				Priority.ALWAYS, // vertikale Ausdehnung
				new Insets(insets)   // Abstand des Inhaltes zum Zellenrand
				);

		
		GridPane.setConstraints(
				okButton,            // Knotenelement
				0,               // Spaltenindex
				4,               // Zeilenindex
				3,               // Anzahl überspannter Spalten
				1,               // Anuahl überspannter Zeilen
				HPos.CENTER,     // horizonale Anordnung
				VPos.BOTTOM,     // vertikale Anordnung
				Priority.ALWAYS, // horizontale Ausdehnung
				Priority.ALWAYS, // vertikale Ausdehnung
				new Insets(insets)   // Abstand des Inhaltes zum Zellenrand
				);
		
		GridPane.setConstraints(
				labelError,            // Knotenelement
				0,               // Spaltenindex
				3,               // Zeilenindex
				3,               // Anzahl überspannter Spalten
				1,               // Anuahl überspannter Zeilen
				HPos.CENTER,     // horizonale Anordnung
				VPos.BOTTOM,     // vertikale Anordnung
				Priority.ALWAYS, // horizontale Ausdehnung
				Priority.ALWAYS, // vertikale Ausdehnung
				new Insets(2)   // Abstand des Inhaltes zum Zellenrand
				);
		
	}

}
