package gui.calculatorFrame;

import java.sql.SQLException;

import org.controlsfx.dialog.ExceptionDialog;

import customizedClasses.CustomizedStage;
import database.ado.Player;
import javafx.collections.ObservableList;
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
import main.Main;

public class ChoosePlayerFrame extends CustomizedStage {

	ComboBox<Player> boxPlayer1 = new ComboBox<>();
	ComboBox<Player> boxPlayer2 = new ComboBox<>();

	Label labelPlayer1 = new Label("Spieler 1");
	Label labelPlayer2 = new Label("Spieler 2");
	Label vsLabel = new Label("VS");
	Label labelError = new Label("");

	Button okButton = new Button("Ok");

	GridPane pane = new GridPane();

	Player player = new Player();

	Player selectedPlayer1;
	Player selectedPlayer2;

	ObservableList<Player> playerList1;
	ObservableList<Player> playerList2;

	@SuppressWarnings("unused")
	private CalculatorFrame calculatorFrame;
	
	static boolean action = true;

	public ChoosePlayerFrame(CalculatorFrame calculatorFrame) {
		this.calculatorFrame = calculatorFrame;
		
		okButton.setOnAction(e ->{
			if (boxPlayer1.getSelectionModel().getSelectedItem() == null || boxPlayer2.getSelectionModel().getSelectedItem() == null) {
				labelError.setText("Bitte 2 Spieler auswählen");
			}else {
				if (boxPlayer1.getSelectionModel().getSelectedIndex() == boxPlayer2.getSelectionModel().getSelectedIndex()) {
					labelError.setText("Bitte 2 verschiedene Spieler auswählen");
				}else {					
					labelError.setText("");
					close();
				}
			}
			
		});

		try {
			playerList1 = player.getAllPlayersAsObservableList();
			playerList2 = player.getAllPlayersAsObservableList();
		} catch (SQLException e) {
			ExceptionDialog exceptionDialog = new ExceptionDialog(e);
			exceptionDialog.showAndWait();
		}

		
		setOnHiding(e -> {
			if ((boxPlayer1.getSelectionModel().getSelectedItem() == null || boxPlayer2.getSelectionModel().getSelectedItem() == null)) {
				close();
				calculatorFrame.close();
				Main.mainFrame.show();
			}
		});
		
		boxPlayer1.setItems(playerList1);
		boxPlayer2.setItems(playerList2);

		boxPlayer1.setPrefSize(160, 20);
		boxPlayer2.setPrefSize(160, 20);

		labelError.setTextFill(Paint.valueOf("#f00000"));

		pane.add(labelPlayer1, 0, 1);
		pane.add(labelPlayer2, 2, 1);
		pane.add(boxPlayer1, 0, 2);
		pane.add(boxPlayer2, 2, 2);
		pane.add(vsLabel, 1, 2);
		pane.add(labelError, 0, 3);
		pane.add(okButton, 0, 4);

		setConstraints();

		setSelectionLogic();

		setScene(new Scene(pane));
		setFullScreen(false);
		setResizable(false);
		setSize(400, 200);
		setTitle("Spielerauswahl");
		setAlwaysOnTop(true);
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL); 
	}

	private void setSelectionLogic() {

		boxPlayer1.setOnAction(e -> {
//			try {	
//				if (boxPlayer1.getSelectionModel().getSelectedIndex() != -1) {
//				selectedPlayer1 = boxPlayer1.getSelectionModel().getSelectedItem();
//				playerList2 = player.getAllPlayersAsObservableList();
//				System.out.println("Selected Index in Box1: " + boxPlayer1.getSelectionModel().getSelectedIndex());
//					playerList2.remove(boxPlayer1.getSelectionModel().getSelectedIndex());
//				}
//				boxPlayer2.setItems(playerList2);
//				boxPlayer2.getSelectionModel().select(selectedPlayer2);
//
//			} catch (SQLException e1) {
//				ExceptionDialog exceptionDialog = new ExceptionDialog(e1);
//				exceptionDialog.show();
//			}
		});

		boxPlayer2.setOnAction(e -> {
//			try {
//				if (boxPlayer2.getSelectionModel().getSelectedIndex() != -1) {
//				selectedPlayer2 = boxPlayer2.getSelectionModel().getSelectedItem();
//				playerList1 = player.getAllPlayersAsObservableList();
//				System.out.println("Selected Index in Box1: " + boxPlayer1.getSelectionModel().getSelectedIndex());
//					playerList1.remove(boxPlayer2.getSelectionModel().getSelectedIndex());
//				}
//				boxPlayer1.setItems(playerList1);
//				boxPlayer1.getSelectionModel().select(selectedPlayer1);
//
//			} catch (SQLException e2) {
//				ExceptionDialog exceptionDialog = new ExceptionDialog(e2);
//				exceptionDialog.show();
//			}
		});
	}

	private void setConstraints() {

		int insets = 10;

		GridPane.setConstraints(
				labelPlayer1,            // Knotenelement
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
				labelPlayer2,            // Knotenelement
				2,               // Spaltenindex
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
				boxPlayer1,            // Knotenelement
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
				boxPlayer2,            // Knotenelement
				2,               // Spaltenindex
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
				vsLabel,            // Knotenelement
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
	}

	public Player[] getSelectedPlayers() {
		Player[] player = {boxPlayer1.getSelectionModel().getSelectedItem(), boxPlayer2.getSelectionModel().getSelectedItem()};
		return player;
	}
}
