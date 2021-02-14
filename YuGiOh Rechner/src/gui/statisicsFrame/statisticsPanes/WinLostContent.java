package gui.statisicsFrame.statisticsPanes;

import gui.statisicsFrame.WinLostStatisticsDataObject;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class WinLostContent extends BorderPane{

	static TableView<WinLostStatisticsDataObject> table = new TableView<>();
	
	public WinLostContent() {
		table.setItems(null);
		setTableColumsAndData();
		setCenter(table);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTableColumsAndData() {
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("playername"));

		TableColumn getLpsCol = new TableColumn("Hinzugefügte LP");
		getLpsCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("totalPlus"));
		
		TableColumn lostLpsCol = new TableColumn("Verlorene LP");
		lostLpsCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("totalMinus"));

		TableColumn differenceLpsCol = new TableColumn("Differenz");
		differenceLpsCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("difference"));
		
		TableColumn winsCol = new TableColumn("Gewonnene Spiele");
		winsCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("wins"));
		
		TableColumn lostsCol = new TableColumn("Verlorene Spiele");
		lostsCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("losts"));
		
		TableColumn winLostValueCol = new TableColumn("Wins / Losts");
		winLostValueCol.setCellValueFactory( new PropertyValueFactory<WinLostStatisticsDataObject, String>("winLostValue"));
		
		if (table.getColumns().isEmpty()) {

            table.setEditable(false);
            table.getColumns().addAll(nameCol, getLpsCol, lostLpsCol, differenceLpsCol, winsCol, lostsCol, winLostValueCol);
            setTableData();
            table.getSelectionModel().select(0);

       }else {
    	   setTableData();
       }
	}
	public static void setTableData() {
		table.setItems(WinLostStatisticsDataObject.getAllWinLostStatisticsDataObjectsAsObservableList());
	}
}
