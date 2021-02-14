package gui.statisicsFrame;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import database.ado.Player;
import main.Main;

public class StatisticsDataCollector {

	private static Connection connection = Main.databaseConnection;

	public static int selectAllGamesCount() {
		int count = 0;
		try {
			Statement statement = connection.createStatement();
			String sql = "select count(*) from Matches";
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
	
	public static String selectTotalPlaytime() {
		String playtime = "";

		Player tmp = new Player();
		
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from Matches";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				tmp.addPlaytime(rs.getTime(4) + "");
			}
			playtime = tmp.getPlaytime().getTimeString();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return playtime;
	}
	
	public static String selectMaxMatchtime() {
		String playtime = "";

		Player tmp = new Player();
		
		try {
			Statement statement = connection.createStatement();
			String sql = "select max(playtime) from Matches";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				tmp.addPlaytime(rs.getTime(1) + "");
			}
			playtime = tmp.getPlaytime().getTimeString();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return playtime;
	}
	public static String selectMaxPlayertime() {
		String playtime = "";

		Player tmp = new Player();
		
		try {
			Statement statement = connection.createStatement();
			String sql = "select max(playtime) from Player";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				tmp.addPlaytime(rs.getTime(1) + "");
			}
			playtime = tmp.getPlaytime().getTimeString();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return playtime;
	}

	public static double selectBestWinLostValue() {
		
		ArrayList<Double> winLostValues = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from Player";
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				winLostValues.add(getWinLostValue(new Player(rs.getInt(1))));
			}
			Collections.sort(winLostValues);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return winLostValues.get((winLostValues.size() - 1));
	}
	
	
	public static double getWinLostValue(Player player) {
		double value = 0;
		if (player.getLosts() == 0) {
			return -1;
		}
		value = ((double)player.getWins()) / ((double)player.getLosts());
		return value;
	}

	public static int selectMostLpGet() {
		int lifePoints = 0;
		try {
			Statement statement = connection.createStatement();
			String sql = "select max(totallPlus) from Player";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				lifePoints = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lifePoints;
	}

	public static int selectMostLpLost() {
		int lifePoints = 0;
		try {
			Statement statement = connection.createStatement();
			String sql = "select max(totalMinus) from Player";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				lifePoints = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lifePoints;
	}
}
