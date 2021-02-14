package gui.playerframe;

import java.sql.SQLException;
import java.util.Optional;

import org.controlsfx.dialog.ExceptionDialog;
import org.controlsfx.glyphfont.Glyph;

import customizedClasses.CustomizedStage;
import database.ado.Player;
import gui.mainframe.MainFrame;
import gui.statisicsFrame.PlayerAnalysisFrame;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;


public class PlayerFrame extends CustomizedStage {
	ContextMenu contextMenu = new ContextMenu();

	BorderPane pane = new BorderPane();
	ButtonBar buttonBar = new ButtonBar();
	Button buttonCreatePlayer = new Button("Spieler anlegen", new Glyph("FontAwesome", "PLUS"));
	Button buttonEditPlayer = new Button("Spieler bearbeiten", new Glyph("FontAwesome", "EDIT"));
	Button buttonDeletePlayer = new Button("Spieler löschen", new Glyph("FontAwesome", "TRASH"));
	Button buttonBack = new Button("Zurück zum Hauptmenü", new Glyph("FontAwesome", "ARROW_LEFT"));

	static TableView<Player> table = new TableView<>();

	static Player player = new Player();
	
	public PlayerFrame() {
		table.setItems(null);
		
		System.out.println("Dies ist ein Text");
		
		setTableColumsAndData();

		setButtonEvents();
		
		setPopupMenu();

		ButtonBar.setButtonData(buttonBack, ButtonData.LEFT);
		buttonBar.getButtons().addAll(buttonCreatePlayer, buttonEditPlayer, buttonDeletePlayer, buttonBack);
		buttonBar.setPadding(new Insets(10, 10, 10, 10));
		
		pane.setCenter(table);
		pane.setBottom(buttonBar);
		
		table.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

			@Override
			public void handle(ContextMenuEvent event) {
				contextMenu.show(table, event.getSceneX(), event.getSceneY());
			}
		});

		setScene(new Scene(pane));
		setTitle("Spielerübersicht");
		setFullScreen(false);
		setSizeToCompleteScreen();
	}

	private void setPopupMenu() {
		MenuItem editItem = new MenuItem("Spieler bearbeiten", new Glyph("FontAwesome", "EDIT"));
		MenuItem deleteItem = new MenuItem("Spieler löschen", new Glyph("FontAwesome", "TRASH"));
		MenuItem analyseItem = new MenuItem("Spieler analysieren", new Glyph("FontAwesome", "SEARCH"));
		
		editItem.setOnAction(e ->{
			EditPlayerFrame editPlayerFrame = new EditPlayerFrame(table.getSelectionModel().getSelectedItem());
			editPlayerFrame.show();
		});
		deleteItem.setOnAction(e ->{
			deletePlayerAction();
		});
		analyseItem.setOnAction(e ->{
			PlayerAnalysisFrame playerAnalysisFrame = new PlayerAnalysisFrame(table.getSelectionModel().getSelectedItem());
			playerAnalysisFrame.show();
		});
		
		contextMenu.getItems().addAll(editItem, deleteItem, analyseItem);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setTableColumsAndData() {
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(280);
		nameCol.setCellValueFactory( new PropertyValueFactory<Player, String>("name"));

		TableColumn shortNameCol = new TableColumn("Spitzname");
		shortNameCol.setMinWidth(200);
		shortNameCol.setCellValueFactory( new PropertyValueFactory<Player, String>("shortname"));

		TableColumn timeCol = new TableColumn("Spielzeit");
		timeCol.setMinWidth(150);
		timeCol.setCellValueFactory( new PropertyValueFactory<Player, String>("playtime"));

		TableColumn matchesCol = new TableColumn("Gespielte Spiele");
		matchesCol.setMinWidth(120);
		matchesCol.setCellValueFactory( new PropertyValueFactory<Player, String>("matchcounter"));
		
		TableColumn winsCol = new TableColumn("Gewonnene Spiele");
		winsCol.setMinWidth(120);
		winsCol.setCellValueFactory( new PropertyValueFactory<Player, String>("wins"));

		TableColumn lostsCol = new TableColumn("Verlorene Spiele");
		lostsCol.setMinWidth(120);
		lostsCol.setCellValueFactory( new PropertyValueFactory<Player, String>("losts"));

		TableColumn lostLifepointsCol = new TableColumn("Verlorene Lebenspunkte");
		lostLifepointsCol.setMinWidth(190);
		lostLifepointsCol.setCellValueFactory( new PropertyValueFactory<Player, String>("totalMinus"));

		TableColumn addesLifepointsCol = new TableColumn("Hinzugefügte Lebenspunkte");
		addesLifepointsCol.setMinWidth(190);
		addesLifepointsCol.setCellValueFactory( new PropertyValueFactory<Player, String>("totalPlus"));

		TableColumn imgCol = new TableColumn("Spielerbild ID");
		imgCol.setMinWidth(120);
		imgCol.setCellValueFactory( new PropertyValueFactory<Player, String>("imdgId"));

		if (table.getColumns().isEmpty()) {

            table.setEditable(false);
            table.getColumns().addAll(nameCol, shortNameCol , timeCol, matchesCol, winsCol, lostsCol, addesLifepointsCol,
				lostLifepointsCol, imgCol);
            setTableData();
            table.getSelectionModel().select(0);

       }else {
    	   setTableData();
       }
	}

	public static void setTableData() {
		try {
			table.setItems(player.getAllPlayersAsObservableList());
		} catch (SQLException e) {
			ExceptionDialog exceptionDialog = new ExceptionDialog(e);
			exceptionDialog.show();
		}
	}

	public void setButtonEvents() {
		buttonBack.setOnAction(e -> {
			MainFrame.primaryStage.show();
			close();
		});
		buttonEditPlayer.setOnAction(e ->{
			EditPlayerFrame editPlayerFrame = new EditPlayerFrame(table.getSelectionModel().getSelectedItem());
			editPlayerFrame.show();
		});
		buttonCreatePlayer.setOnAction(e -> {
			InsertPlayerFrame insertPlayerFrame = new InsertPlayerFrame();
			insertPlayerFrame.show();
		});
		buttonDeletePlayer.setOnAction(e -> {
			deletePlayerAction();
		});
	}

	private void deletePlayerAction() {
		Player tempPlayer = table.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sicherheitsabfrage");
		alert.setHeaderText(null);
		alert.setContentText("Wollen sie den Spieler '" + tempPlayer.getName() + "' wirklich löschen?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				tempPlayer.delete();
				PlayerFrame.setTableData();
			} catch (SQLException e1) {
				e1.printStackTrace();
				ExceptionDialog exceptionDialog = new ExceptionDialog(e1);
				exceptionDialog.show();
			}
		} else {
			alert.close();
		}
	}
}
