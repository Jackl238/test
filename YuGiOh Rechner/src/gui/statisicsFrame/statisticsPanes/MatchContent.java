package gui.statisicsFrame.statisticsPanes;

import java.sql.SQLException;
import java.util.Optional;

import org.controlsfx.dialog.ExceptionDialog;
import org.controlsfx.glyphfont.Glyph;

import database.ado.Match;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MatchContent extends BorderPane{

	static TableView<Match> table = new TableView<>();
	
	Button deleteButton = new Button("Match löschen", new Glyph("FontAwesome", "TRASH"));
	
	VBox buttonBox = new VBox();
	
	public MatchContent() {
		table.setItems(null);
		buttonBox.getChildren().add(deleteButton);
		buttonBox.setPadding(new Insets(15, 10, 0, 10));
		
		setTableColumsAndData();
		setCenter(table);
		setBottom(buttonBox);
		
		deleteButton.setOnAction(e ->{
			Match tempMatch = table.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Sicherheitsabfrage");
			alert.setHeaderText(null);
			alert.setContentText("Wollen sie das Match '" + tempMatch.getMatchName() + "' wirklich löschen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					table.getSelectionModel().getSelectedItem().delete();
					setTableData();
				} catch (SQLException e1) {
					ExceptionDialog exceptionDialog = new ExceptionDialog(e1);
					exceptionDialog.show();
				}
			}
		});
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTableColumsAndData() {
		TableColumn namePlayer1Col = new TableColumn("Spieler 1");
		namePlayer1Col.setCellValueFactory( new PropertyValueFactory<Match, String>("namePlayer1"));

		TableColumn namePlayer2Col = new TableColumn("Spieler 2");
		namePlayer2Col.setCellValueFactory( new PropertyValueFactory<Match, String>("namePlayer2"));
		
		TableColumn winnerCol = new TableColumn("Spielzeit");
		winnerCol.setCellValueFactory( new PropertyValueFactory<Match, String>("playtime"));

		TableColumn playtimeCol = new TableColumn("Gewinner");
		playtimeCol.setCellValueFactory( new PropertyValueFactory<Match, String>("winner"));
		
		TableColumn dateCol = new TableColumn("Datum");
		dateCol.setCellValueFactory( new PropertyValueFactory<Match, String>("date"));
		
		TableColumn lpPlayer1Col = new TableColumn("LPs Spieler 1");
		lpPlayer1Col.setCellValueFactory( new PropertyValueFactory<Match, String>("lifePointsPlayer1"));
		
		TableColumn lpPlayer2Col = new TableColumn("LPs Spieler 2");
		lpPlayer2Col.setCellValueFactory( new PropertyValueFactory<Match, String>("lifePointsPlayer2"));
		
		TableColumn cubesCol = new TableColumn("Geworfene Würfel");
		cubesCol.setCellValueFactory( new PropertyValueFactory<Match, String>("cubes"));
		
		TableColumn coinsCol = new TableColumn("Münzwürfe");
		coinsCol.setCellValueFactory( new PropertyValueFactory<Match, String>("coins"));
		
		TableColumn winningReasonCol = new TableColumn("Grund");
		winningReasonCol.setCellValueFactory( new PropertyValueFactory<Match, String>("winningReason"));
		
		if (table.getColumns().isEmpty()) {

            table.setEditable(false);
            table.getColumns().addAll(namePlayer1Col, namePlayer2Col, winnerCol, playtimeCol, dateCol,
            		lpPlayer1Col, lpPlayer2Col, cubesCol, coinsCol, winningReasonCol);
            setTableData();
            table.getSelectionModel().select(0);

       }else {
    	   setTableData();
       }
	}
	public static void setTableData() {
		table.setItems(Match.getMatchesAsObservableList());
	}
}
