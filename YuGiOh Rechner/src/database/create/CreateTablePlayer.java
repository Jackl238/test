package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTablePlayer {

	public static void main(String[] args) {
		try {

			Connection connection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS PLAYER"
					+ "(id INTEGER auto_increment, "
					+ "name VARCHAR(255), "
					+ "shortname VARCHAR(255), "
					+ "matchcounter INTEGER,"
					+ "playtime TIME,"
					+ "totalMinus INTEGER,"
					+ "totallPlus INTEGER,"
					+ "wins INTEGER,"
					+ "losts INTEGER,"
					+ "imgid INTEGER,"
					+ "PRIMARY KEY (id))";
			
			statement.executeUpdate(sql);
			System.out.println("Tabelle 'Player' erstellt");
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
