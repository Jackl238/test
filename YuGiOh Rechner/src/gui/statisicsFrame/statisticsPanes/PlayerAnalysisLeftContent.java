package gui.statisicsFrame.statisticsPanes;


import org.controlsfx.tools.Borders;

import database.ado.Match;
import database.ado.Player;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerAnalysisLeftContent extends VBox {

	Label labelGamesCount = new Label("Spiele: ");
	Label labelGamesCountValue = new Label("");
	VBox gamesCountBox = new VBox(labelGamesCount, labelGamesCountValue);
	
	Label labelPlaytime = new Label("Spielzeit: ");
	Label labelPlaytimeValue = new Label("");
	VBox playtimeBox = new VBox(labelPlaytime, labelPlaytimeValue);
	
	Label labelEnemy = new Label("Lieblingsgegner: ");
	Label labelEnemyValue = new Label("");
	VBox enemyBox = new VBox(labelEnemy, labelEnemyValue);
	
	Label labelLpGet = new Label("Hinzugefügte LP: ");
	Label labelLpLost = new Label("Verlorene LP: ");
	VBox lpBox = new VBox(labelLpGet, labelLpLost);
	
	public PlayerAnalysisLeftContent(Player player) {
		Font headerLabelFont = Font.font("ARIAL", FontWeight.NORMAL, 18);
		labelGamesCount.setFont(headerLabelFont);
		labelGamesCountValue.setFont(headerLabelFont);
		labelEnemy.setFont(headerLabelFont);
		labelEnemyValue.setFont(headerLabelFont);
		labelPlaytime.setFont(headerLabelFont);
		labelPlaytimeValue.setFont(headerLabelFont);
		labelLpGet.setFont(headerLabelFont);
		labelLpLost.setFont(headerLabelFont);
		
		Insets insets = new Insets(10, 0, 0, 0);
		labelGamesCountValue.setPadding(insets);
		labelEnemyValue.setPadding(insets);
		labelPlaytimeValue.setPadding(insets);
		labelLpGet.setPadding(insets);
		labelLpLost.setPadding(insets);
		
		labelGamesCountValue.setText(player.getMatchcounter() + "");
		labelPlaytimeValue.setText(player.getPlaytime() + "");
		labelLpGet.setText(labelLpGet.getText() + player.getTotalPlus());
		labelLpLost.setText(labelLpLost.getText() + "	" + player.getTotalMinus());
		labelEnemyValue.setText(Match.getEmeyOfPlayer(player).getName());
		
		Node wrappedLabelGamesCount = Borders.wrap(gamesCountBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedLabelPlaytime = Borders.wrap(playtimeBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedEnemy = Borders.wrap(enemyBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedLpBox = Borders.wrap(lpBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedBarChart = Borders.wrap(getBarChart(player)).lineBorder().color(Color.GRAY).buildAll();
	
		getChildren().addAll(wrappedLabelGamesCount, wrappedLabelPlaytime, wrappedEnemy, wrappedLpBox, wrappedBarChart);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private BarChart<Number, String> getBarChart(Player player) {
		CategoryAxis yAxis = new CategoryAxis();
		NumberAxis xAxis = new NumberAxis();
		BarChart<Number, String> bc = new BarChart<Number, String>(xAxis,yAxis);
		bc.setTitle("Spielzeit pro Spieler");
		xAxis.setLabel("Dauer");       
		yAxis.setLabel("Spiel");

		String description = "Zeit in Min";
		
		Match[] array = Match.getFastestAndLongestMatchesOfPlayer(player);
		
		XYChart.Series minSeries = new XYChart.Series();
		XYChart.Series maxSeries = new XYChart.Series();
		
		minSeries.setName(array[0].getMatchName());       
		minSeries.getData().add(new XYChart.Data(array[0].getPlaytimeAsDouble(), description));
		
		maxSeries.setName(array[1].getMatchName());       
		maxSeries.getData().add(new XYChart.Data(array[1].getPlaytimeAsDouble(), description));
				
		bc.getData().addAll(minSeries, maxSeries);
		
		return bc;
	}
	
}
