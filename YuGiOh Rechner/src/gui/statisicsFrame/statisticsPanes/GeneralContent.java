package gui.statisicsFrame.statisticsPanes;



import java.util.ArrayList;

import org.controlsfx.tools.Borders;

import database.ado.Player;
import gui.statisicsFrame.StatisticsDataCollector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GeneralContent extends BorderPane {

	Label longestMatch = new Label("Längstes Match:");
	Label longestMatchValue = new Label();
	VBox longestMatchBox = new VBox();

	Label mostPlaytime = new Label("Spieler mit meister Spielzeit:");
	Label mostPlaytimeValue = new Label();
	VBox mostPlaytimeBox = new VBox();

	Label bestWinLost = new Label("Spieler mit bestem Win/Lost Wert:");
	Label bestWinLostValue = new Label();
	VBox bestWinLostBox = new VBox();

	Label mostLpLost = new Label("Meiste verlorene LifePoints (gesamt):");
	Label mostLpLostValue = new Label();
	VBox mostLpLostBox = new VBox();

	Label mostLpGet = new Label("Meiste hinzugefügte LifePoints (gesamt):");
	Label mostLpGetValue = new Label();
	VBox mostLpGetBox = new VBox();

	Label totalPlaytime = new Label("Gesamte Spielzeit: ");
	Label totalPlaytimeValue = new Label();
	VBox totalPlaytimeBox = new VBox();

	Label totalGames = new Label("Gesamte Spiele: ");
	Label totalGamesValue = new Label();
	VBox totalGamesBox = new VBox();

	VBox left = new VBox();
	VBox right = new VBox();
	HBox bottom = new HBox();

	public GeneralContent() {
		//Area Boxen Border
		Node wrappedLeft = Borders.wrap(left).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedRight = Borders.wrap(right).lineBorder().color(Color.GRAY).buildAll();
//		Node wrappedBottom = Borders.wrap(bottom).lineBorder().color(Color.GRAY).buildAll();
		//Values Boxen Border
		Node wrappedTotalGamesBox = Borders.wrap(totalGamesBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedTotalPlaytimeBox = Borders.wrap(totalPlaytimeBox).lineBorder().color(Color.GRAY).buildAll();
		Node wraooedMostLpGetBox = Borders.wrap(mostLpGetBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedMostLpLostBox = Borders.wrap(mostLpLostBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedBestWinLostBox = Borders.wrap(bestWinLostBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedMostPlaytimeBox = Borders.wrap(mostPlaytimeBox).lineBorder().color(Color.GRAY).buildAll();
		Node wrappedLongestMatchBox = Borders.wrap(longestMatchBox).lineBorder().color(Color.GRAY).buildAll();

		left.getChildren().addAll(wrappedLongestMatchBox, wrappedMostPlaytimeBox, wrappedBestWinLostBox, wrappedMostLpLostBox, wraooedMostLpGetBox);
		right.getChildren().addAll(getPieChart(), wrappedTotalPlaytimeBox, wrappedTotalGamesBox);

		longestMatchBox.getChildren().addAll(longestMatch, longestMatchValue);
		mostPlaytimeBox.getChildren().addAll(mostPlaytime, mostPlaytimeValue);
		bestWinLostBox.getChildren().addAll(bestWinLost, bestWinLostValue);
		totalPlaytimeBox.getChildren().addAll(totalPlaytime);
		totalGamesBox.getChildren().addAll(totalGames);
		mostLpLostBox.getChildren().addAll(mostLpLost, mostLpLostValue);
		mostLpGetBox.getChildren().addAll(mostLpGet, mostLpGetValue);

		totalGames.setText(totalGames.getText() + StatisticsDataCollector.selectAllGamesCount());
		totalPlaytime.setText(totalPlaytime.getText() + StatisticsDataCollector.selectTotalPlaytime());
		longestMatchValue.setText(StatisticsDataCollector.selectMaxMatchtime());
		mostPlaytimeValue.setText(StatisticsDataCollector.selectMaxPlayertime());
		bestWinLostValue.setText(StatisticsDataCollector.selectBestWinLostValue() + "");
		mostLpGetValue.setText(StatisticsDataCollector.selectMostLpGet() + "");
		mostLpLostValue.setText(StatisticsDataCollector.selectMostLpLost() + "");

		setLeft(wrappedLeft);
		setRight(wrappedRight);
//		setBottom(wrappedBottom);
	}

	private Node getPieChart() {
		ArrayList<PieChart.Data> list = new ArrayList<>();
		ArrayList<Player> playerList = Player.getAllPlayersAsArrayList();
		for (int i = 0; i < playerList.size(); i++) {
			list.add(new PieChart.Data(playerList.get(i).getName(), playerList.get(i).getMatchcounter()));
		}
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(list);
		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle("Spieleranteil an allen Spielen");
		chart.setMinSize(500, 420);
		return chart;
	}

}
