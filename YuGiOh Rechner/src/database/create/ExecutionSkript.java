package database.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import database.ado.Player;


public class ExecutionSkript {

	/**
	 * Leerschablone für Commandkette
	 * 
	  	sql = "";
	 	statement.executeUpdate(sql);
	 	System.out.println("Text");
	 * 
	 */
	
	public static void main(String[] args) {
		try { 

			Connection connection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
			Statement statement = connection.createStatement();
			String sql;
			
			statement = connection.createStatement();
			sql = "update decks set playerId = 142 where playerId = 3";
			statement.executeUpdate(sql);
		
		 	
		 	
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
