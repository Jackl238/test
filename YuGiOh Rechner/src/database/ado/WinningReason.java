package database.ado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import customizedClasses.ExeptionDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

public class WinningReason {

	private int id;
	private String description;

	public WinningReason() {
		//Dummy Constructor
	}

	public WinningReason(int id) {
		initialize(id);
	}

	public WinningReason(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	private void initialize(int id) {
		this.id = id;
		this.description = selectDescriptionById(id);
	}

	public static String selectDescriptionById(int id) {
		try {
			Statement statement = Main.databaseConnection.createStatement();
			String sql = "select * from winningReason where id = " + id;
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			return rs.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
			@SuppressWarnings("unused")
			ExeptionDialog exeptionDialog = new ExeptionDialog(e);
			return "";
		}
	}
	public void insert() {
		try {
			Statement statement = Main.databaseConnection.createStatement();
			String sql = "insert into winningReason (description) values ('" + description + "')";
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			@SuppressWarnings("unused")
			ExeptionDialog exeptionDialog = new ExeptionDialog(e);
		}
	}
	public void update() {
		try {
			Statement statement = Main.databaseConnection.createStatement();
			String sql = "update winningReason "
					+ "set description =  '" + description + "'"
					+ "where id = " + id;
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			@SuppressWarnings("unused")
			ExeptionDialog exeptionDialog = new ExeptionDialog(e);
		}
	}
	public static ObservableList<WinningReason> getAllDataAsObservableListWithoutFirstElement() {
		ObservableList<WinningReason> list = FXCollections.observableArrayList();
		try {
			Statement statement = Main.databaseConnection.createStatement();
			String sql = "select * from winningreason where id > 1";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				list.add(new WinningReason(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			@SuppressWarnings("unused")
			ExeptionDialog exeptionDialog = new ExeptionDialog(e);
		}
		return list;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
