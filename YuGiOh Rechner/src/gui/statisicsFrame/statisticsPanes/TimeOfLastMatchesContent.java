package gui.statisicsFrame.statisticsPanes;

import java.util.Stack;

import database.ado.Match;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class TimeOfLastMatchesContent extends BorderPane {

	private int lastMatchesCount = 10;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TimeOfLastMatchesContent() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle("Dauer der letzten Matches");
		xAxis.setLabel("Match");       
		yAxis.setLabel("Dauer");

		Stack<Match> matchesStack = Match.getMatchesAsStack();
		String description = "Zeit in Min";
		
		XYChart.Series[] series = new XYChart.Series[lastMatchesCount];
		
		for (int i = 0; i < lastMatchesCount; i++) {
			series[i] = new XYChart.Series();
			series[i].setName("" + matchesStack.lastElement().getMatchName());       
			series[i].getData().add(new XYChart.Data(description, matchesStack.remove(matchesStack.size() - 1).getPlaytimeAsMinutes()));
		}
		for (int i = 0; i < series.length; i++) {			
			bc.getData().add(series[i]);
		}
		setCenter(bc);
	}

}
