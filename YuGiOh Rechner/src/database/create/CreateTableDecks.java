package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTableDecks { 

	public static void main(String[] args) {
		try {

			Connection connection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS DECKS"
					+ "(id INTEGER auto_increment, "
					+ "playerID INTEGER,"
					+ "name Varchar(255),"
					+ "winns INTEGER,"
					+ "losts INTEGER,"
					+ "PRIMARY KEY (id))";

			statement.executeUpdate(sql);
			System.out.println("Tabelle 'Decks' erstellt");

		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
}
