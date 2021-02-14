package gui.playerframe;


import java.sql.SQLException;

import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.dialog.ExceptionDialog;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import customizedClasses.CustomizedStage;
import database.ado.Player;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;

public class InsertPlayerFrame extends CustomizedStage {
	

	Label nameLabel = new Label("Name:");
	Label shortNameLabel = new Label("Spitzname:");
	
	Label labelError = new Label();
	
	CustomTextField fieldName = (CustomTextField) TextFields.createClearableTextField();
	CustomTextField fieldShortName = (CustomTextField) TextFields.createClearableTextField();
	
	Button saveButton = new Button("Speichern", new Glyph("FontAwesome", "SAVE"));
	
	GridPane pane = new GridPane();
	
	public InsertPlayerFrame() {
		setConstraints();
		
		labelError.setTextFill(Paint.valueOf("#f00000"));
		
		pane.add(nameLabel, 0, 1);
		pane.add(shortNameLabel, 0, 2);
		pane.add(fieldName, 1, 1);
		pane.add(fieldShortName, 1, 2);
		pane.add(labelError, 0, 3);
		pane.add(saveButton, 0, 4);
		
		setFullScreen(false);
		setSize(400, 200);
		
		saveButton.setOnAction(e -> {
			
			ValidationSupport vs = new ValidationSupport();
			
			
			if (fieldName.getText().equals("")) {
				vs.registerValidator(fieldName, Validator.createEmptyValidator("Name ist ein Pflichtfeld"));
				labelError.setText("Bitte Name eingeben");
			}else if (fieldShortName.getText().equals("")) {
				vs.registerValidator(fieldShortName, Validator.createEmptyValidator("Spitzname ist ein Pflichtfeld"));
				labelError.setText("Bitte Spitzname eingeben");
			}else {
				labelError.setText("");
				Player player = new Player();
				player.setName(fieldName.getText());
				player.setShortname(fieldShortName.getText());
				try {
					player.insertNewPlayer();
				} catch (SQLException e1) {
					ExceptionDialog exceptionDialog = new ExceptionDialog(e1);
					exceptionDialog.show();
				}
				PlayerFrame.setTableData();
				close();
			}
		});
		setScene(new Scene(pane));
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Neuen Spieler anlegen");
		setFullScreen(false);
	}

	private void setConstraints() {
		
		int insets = 15;
		
		GridPane.setConstraints(
				nameLabel,            // Knotenelement
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
				fieldName,            // Knotenelement
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
				shortNameLabel,            // Knotenelement
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
				fieldShortName,            // Knotenelement
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
				labelError,            // Knotenelement
			    0,               // Spaltenindex
			    3,               // Zeilenindex
			    2,               // Anzahl überspannter Spalten
			    1,               // Anuahl überspannter Zeilen
			    HPos.CENTER,     // horizonale Anordnung
			    VPos.BOTTOM,     // vertikale Anordnung
			    Priority.ALWAYS, // horizontale Ausdehnung
			    Priority.ALWAYS, // vertikale Ausdehnung
			    new Insets(2)   // Abstand des Inhaltes zum Zellenrand
			);
		GridPane.setConstraints(
				saveButton,            // Knotenelement
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
	}
	
}
