package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableWinningReasosn {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
		Statement statement = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS WINNINGREASON"
				+ "(id INTEGER auto_increment, "
				+ "description VARCHAR(255), " 
				+ "PRIMARY KEY (id))";
		
		statement.executeUpdate(sql);
		System.out.println("Tabelle WINNINGREASON erstellt");
	}
	
}
