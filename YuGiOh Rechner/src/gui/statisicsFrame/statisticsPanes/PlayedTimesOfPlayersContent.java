package gui.statisicsFrame.statisticsPanes;

import java.util.Stack;

import database.ado.Player;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class PlayedTimesOfPlayersContent extends BorderPane {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PlayedTimesOfPlayersContent() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Spielzeit pro Spieler");
		xAxis.setLabel("Spieler");       
		yAxis.setLabel("Dauer");

		Stack<Player> stack = Player.getAllPlayersAsStack();
		String description = "Zeit in Min";
		
		XYChart.Series[] series = new XYChart.Series[stack.size()];
		
		for (int i = 0; i < series.length; i++) {
			series[i] = new XYChart.Series();
			series[i].setName("" + stack.lastElement().getName());       
			series[i].getData().add(new XYChart.Data(description, stack.remove(stack.size() - 1).getPlaytimeAsMinutes()));
		}
		for (int i = 0; i < series.length; i++) {			
			bc.getData().add(series[i]);
		}
		setCenter(bc);
	}
	
}
