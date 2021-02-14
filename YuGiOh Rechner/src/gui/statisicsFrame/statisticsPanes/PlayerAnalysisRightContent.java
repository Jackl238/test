package gui.statisicsFrame.statisticsPanes;

import java.util.ArrayList;
import java.util.Iterator;

import org.controlsfx.tools.Borders;

import database.ado.Match;
import database.ado.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerAnalysisRightContent extends VBox {

	VBox backBox = new VBox();
	
	VBox gamesBox = new VBox();
	VBox linechartBox = new VBox();
	Label labelWinLostValue = new Label("Wins/Losts: ");
	
	Player player;
	
	public PlayerAnalysisRightContent(Player player) {
		this.player = player;
		
		labelWinLostValue.setText(labelWinLostValue.getText() + player.getKD());
		
		Node wrappedLabel = Borders.wrap(labelWinLostValue).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedPieChartPiece = Borders.wrap(gamesBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedLineChart = Borders.wrap(linechartBox).lineBorder().color(Color.GRAY).buildAll();
		
		gamesBox.getChildren().addAll(getPieChart(), wrappedLabel);
		linechartBox.getChildren().add(getLineChart());
		getChildren().addAll(wrappedPieChartPiece, wrappedLineChart);
	}

	private PieChart getPieChart() {
		ArrayList<PieChart.Data> list = new ArrayList<>();
		list.add(new PieChart.Data("Siege", player.getWins()));
		list.add(new PieChart.Data("Niederlagen", player.getLosts()));
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(list);
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Übersicht Siege / Niederlagen");
		return chart;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private LineChart getLineChart() {
		NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Spiel");
        //creating the chart
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis,yAxis);
                
        lineChart.setTitle("Restliche Lebenspunkte aus den letzten Spielen");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Lebenspunkte");
        //populating the series with data
        ArrayList<Match> matchesList = Match.getMatchesOfPlayerAsArrayList(player.getId());
        for (Iterator iterator = matchesList.iterator(); iterator.hasNext();) {
			Match match = (Match) iterator.next();			
			series.getData().add(new XYChart.Data("" + match.getId(), match.getLifePoints(player.getId())));
			
		}
        
        lineChart.getData().add(series);
        
        return lineChart;
	}
	
}
