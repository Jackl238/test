package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gui.mainframe.MainFrame;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	
	public static Connection databaseConnection;

	public static MainFrame mainFrame = new MainFrame();
	
	public static void main(String[] args) {
		
		try {
			databaseConnection = DriverManager.getConnection("jdbc:h2:" + "./Database/yugioh", "C:\\Program Files (x86)\\H2\\bin", "1234");
			launch(args);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainFrame.start(primaryStage);
	}
	
}
