package gui.calculatorFrame;

import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.tools.Borders;

import customizedClasses.DatabaseConstats;
import database.ado.Player;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerBox extends VBox {

	Label nameLabel = new Label("");
	Label lpLabel = new Label("8000");
	ProgressBar lpBar = new ProgressBar(1);
	CustomTextField field = (CustomTextField) TextFields.createClearableTextField();
	Button minusButton = new Button("-");
	Button plusButton = new Button("+");

	Player player;
	
	VBox backBox = new VBox();
	GridPane gridPane = new GridPane();

	private double lifePoints = 8000;

	public PlayerBox() {

		minusButton.setOnAction(e -> {
			int minusLifePoints = Integer.parseInt(field.getText());
			if (lifePoints - minusLifePoints <= 0) {
				if (showQestionFrame().equals(ButtonType.OK)) {
					rediceLifePoints(minusLifePoints);
				}
			}else {				
				rediceLifePoints(minusLifePoints);
			}
		});
		plusButton.setOnAction(e -> {
			lifePoints = lifePoints + Integer.parseInt(field.getText());
			player.setRemainingLifePoints((int) lifePoints);
			refreshProgressbarAndLabel();
			player.addPlus(Integer.parseInt(field.getText()));
			player.update();
		});

		plusButton.setPadding(new Insets(5, 35, 5, 35));
		minusButton.setPadding(new Insets(5, 35, 5, 35));

		plusButton.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		minusButton.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

		lpBar.setMinWidth(170);
		lpBar.setMaxWidth(170);

		nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		nameLabel.setTextFill(Paint.valueOf("#ffffff"));
		Node wrappedNameLabel = Borders.wrap(nameLabel).lineBorder().color(Color.GRAY.darker()).buildAll();

		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.add(lpBar, 0, 1);
		gridPane.add(field, 0, 2);
		gridPane.add(minusButton, 0, 3);
		gridPane.add(plusButton, 1, 3);
		Node wrappedGridPane = Borders.wrap(gridPane).lineBorder().color(Color.BLACK).buildAll();

		GridPane.setConstraints(lpBar, 0, 1, 2, 1);
		GridPane.setConstraints(field, 0, 2, 2, 1);

		lpLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lpLabel.setTextFill(Paint.valueOf("#ffffff"));
		Node wrappedLpLabel = Borders.wrap(lpLabel).lineBorder().buildAll();

		backBox.getChildren().addAll(wrappedNameLabel, wrappedLpLabel, wrappedGridPane);
		getChildren().add(backBox);

//		showPlayerSelectionPopup();
	}

	private void rediceLifePoints(int minusLifePoints) {
		lifePoints = lifePoints - minusLifePoints;
		player.setRemainingLifePoints((int) lifePoints);
		if (lifePoints < 0) {
			lifePoints = 0;
		}
		refreshProgressbarAndLabel();
		player.addMinus(Integer.parseInt(field.getText()));
		player.update();
	}

	private ButtonType showQestionFrame() {
		Alert popup = new Alert(AlertType.CONFIRMATION);
		popup.setTitle("Sicherheitsabfrage");
		popup.setHeaderText("Wollen sie wirklich die letzen Lebenspunkte abziehen?");
		popup.setContentText("Die Lebenspunkte von " + player.getName() + " werden zu 0 \n"
				+ "Der Spieler hat das Duell somit verloren");
		return popup.showAndWait().get();
	}
//	private void showPlayerSelectionPopup() {
//		//		TODO: Auwahlframe mit 2 Comboboxen zum auswählen der Spieler mit Ladelogik implementieren
//	}

	private void refreshProgressbarAndLabel() {
		String lpString = "" + lifePoints;
		lpLabel.setText(lpString.substring(0, lpString.length() - 2));
		if (lifePoints > 8000) {
			lpBar.setProgress(1);
		}else {
			lpBar.setProgress( (lifePoints / 8000) );
		}
		if (lifePoints == 0) {
			player.setWinner(false);
			CalculatorFrame.centerPane.showCenterInformation(DatabaseConstats.LIFE_POINTS_ZERO_ID);
		}
	}
	public void setPlayer(Player player) {
		this.player = player;
		if (player != null) {			
			player.setWinner(true);
			nameLabel.setText(player.getName());
			player.addMatch();
			player.update();
		}else {
			nameLabel.setText("");
			lifePoints = 8000;
			refreshProgressbarAndLabel();
			field.setText("");
		}
	}
}
