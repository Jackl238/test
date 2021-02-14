package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableDataView {

	public static void main(String[] args) {
		try {

			Connection connection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
			Statement statement = connection.createStatement();
			System.out.println("________ Table Player ________________");
			String sql = 
					"select * from Player";

			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				System.out.println("Spieler ID: " + rs.getInt(1) + " Name: " + rs.getString(2) + " Spitzname: " + rs.getString(3) + " Matchcounter: "+ rs.getInt(4) + " Spielzeit: " + rs.getDate(5)
				+ " TotalMinus: " + rs.getInt(6) + " TotoalPlus: " + rs.getInt(7) + " Wins: " + rs.getInt(8) + " Losts: " + rs.getInt(9));
			}

			System.out.println();
			System.out.println("________ Table Matches ________________");

			sql = "Select * from matches;";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Match ID: " + rs.getInt(1) + " Player1_ID: " + rs.getInt(2) + " Player2_ID: " + rs.getInt(3) + 
						" Playtime: " + rs.getTime(4) + " Winner: " + rs.getInt(5) + " Date: " + rs.getDate(6) + " LP-Player1: " + rs.getInt(7)
						+ " LP-Player2: " + rs.getInt(8)+ " Cubes: " + rs.getInt(9)+ " Coins: " + rs.getInt(10) + " WinningReasonId: " + rs.getInt(11));
			}

			System.out.println();
			System.out.println("________ Table WinningReasons ________________");

			sql = "Select * from WINNINGREASON;";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1) + " Beschreibung: " + rs.getString(2));
			}
			
			System.out.println("________ Liebligsgegner select Test ________________");
			sql = 	
					"select playerid, sum(count) from (" +
					"(Select player2_id as playerid, count(player2_id) as count from matches where player1_id = 140 group by player2_id)"
					+ "Union "
					+ "(select player1_id, count(player1_id) from matches where player2_id = 140 group by player1_id)"
					+ "order by 2 desc)"
					+ " group by playerid;"
					;
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("PlayerID: " + rs.getInt(1) + " Count: " + rs.getInt(2));
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
