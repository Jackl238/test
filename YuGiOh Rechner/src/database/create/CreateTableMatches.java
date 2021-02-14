package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTableMatches {

	public static void main(String[] args) {
		try {

			Connection connection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS MATCHES"
					+ "(id INTEGER auto_increment, "
					+ "player1_ID INTEGER,"
					+ "player2_ID INTEGER,"
					+ "playtime TIME,"
					+ "winner INTEGER,"
					+ "date DATE,"
					+ "PRIMARY KEY (id))";

			statement.executeUpdate(sql);
			System.out.println("Tabelle 'Matches' erstellt");

		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
