package gui.deckframe;

import org.controlsfx.control.MasterDetailPane;

import customizedClasses.CustomizedStage;
import database.ado.Deck;
import database.ado.Player;
import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeckFrame extends CustomizedStage {

	private MasterDetailPane masterDetail = new MasterDetailPane(Side.BOTTOM);
	private TableView<Player> masterTable = new TableView<>();
	private TableView<Deck> detailTable = new TableView<>();


	public DeckFrame() {

		setMasterTableColumsAndData();
		setDetailTableColumsAndData();

		masterTable.setOnMouseClicked(e -> {
			setDetailTableData(masterTable.getSelectionModel().getSelectedItem().getId());
		}); 

		masterDetail.setMasterNode(masterTable);
		masterDetail.setDetailNode(detailTable);
		masterDetail.setShowDetailNode(true);
		masterDetail.setVisible(true);
		setScene(new Scene(masterDetail));
		setTitle("Deckverwaltung");
		setFullScreen(false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setMasterTableColumsAndData() {
		TableColumn playerLongname = new TableColumn("Name");
		playerLongname.setCellValueFactory( new PropertyValueFactory<Player, String>("name"));
		playerLongname.setMinWidth(400);

		TableColumn playerShortname = new TableColumn("Spitzname");
		playerShortname.setCellValueFactory( new PropertyValueFactory<Player, String>("shortname"));
		playerShortname.setMinWidth(200);

		if (masterTable.getColumns().isEmpty()) {

			masterTable.setEditable(false);
			masterTable.getColumns().addAll(playerLongname, playerShortname);
			setMasterTableData();
			masterTable.getSelectionModel().select(0);

		}else {
			setMasterTableData();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setDetailTableColumsAndData() {
		TableColumn name = new TableColumn("Deck Name");
		name.setCellValueFactory( new PropertyValueFactory<Deck, String>("name"));
		setMinWidth(450);

		TableColumn wins = new TableColumn("Siege");
		wins.setCellValueFactory( new PropertyValueFactory<Deck, String>("wins"));
		setMinWidth(100);

		TableColumn losts = new TableColumn("Niederlagen");
		losts.setCellValueFactory( new PropertyValueFactory<Deck, String>("losts"));
		setMinWidth(100);

		TableColumn percentageWinning = new TableColumn("Gewinnquote");
		percentageWinning.setCellValueFactory( new PropertyValueFactory<Deck, String>("percentageWinning"));

		if (detailTable.getColumns().isEmpty()) {

			detailTable.setEditable(false);
			detailTable.getColumns().addAll(name, wins, losts, percentageWinning);
			setDetailTableData(0);
			detailTable.getSelectionModel().select(0);
			setMinWidth(150);

		}else {
			setDetailTableData(0);
		}
	}

	private void setDetailTableData(int playerId) {
		detailTable.setItems(FXCollections.observableList(
				Deck.selectAllDecksOfPlayer(playerId)));
	}

	private void setMasterTableData() {
		masterTable.setItems(FXCollections.observableList(Player.getAllPlayersAsArrayList()));
	}
}
