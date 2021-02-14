package gui.statisicsFrame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

public class WinLostStatisticsDataObject {

	private static Connection connection = Main.databaseConnection;

	private String playername;

	private int totalPlus;
	private int totalMinus;
	private int difference;

	private int wins;
	private int losts;
	private double winLostValue;

	public WinLostStatisticsDataObject() {

	}
	public WinLostStatisticsDataObject(String playername, int totalPlus, int totalMinus, double wins, double losts) {
		this.playername = playername;
		
		this.totalPlus = totalPlus;
		this.totalMinus = totalMinus;
		this.difference = (totalPlus - totalMinus);
		this.wins = (int) wins;
		this.losts = (int) losts;
		if (losts != 0) {			
			this.winLostValue = wins / losts;
		}else {
			this.winLostValue = wins;
		}
		System.out.println("WinLostStatisticsDataObject(): " + playername + "__" + totalPlus + "__" + totalMinus + "__" + difference  + "__"+ wins + "__" + losts  + "__"+ winLostValue);
	}

	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public int getTotalPlus() {
		return totalPlus;
	}
	public void setTotalPlus(int totalPlus) {
		this.totalPlus = totalPlus;
	}
	public int getTotalMinus() {
		return totalMinus;
	}
	public void setTotalMinus(int totalMinus) {
		this.totalMinus = totalMinus;
	}
	public int getDifferende() {
		return difference;
	}
	public void setDifferende(int differende) {
		this.difference = differende;
	}
	public double getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public double getLosts() {
		return losts;
	}
	public void setLosts(int losts) {
		this.losts = losts;
	}
	public double getWinLostValue() {
		return winLostValue;
	}
	public void setWinLostValue(double winLostValue) {
		this.winLostValue = winLostValue;
	}

	public static ObservableList<WinLostStatisticsDataObject> getAllWinLostStatisticsDataObjectsAsObservableList(){
		ObservableList<WinLostStatisticsDataObject> list = FXCollections.observableArrayList();

		try {
			Statement statement = connection.createStatement();
			String sql = "select * from PLAYER";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new WinLostStatisticsDataObject(rs.getString(2), rs.getInt(7), rs.getInt(6), rs.getInt(8), rs.getInt(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
