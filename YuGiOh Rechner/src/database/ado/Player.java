package database.ado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

import org.controlsfx.dialog.ExceptionDialog;

import customizedClasses.Time;
import gui.optionsFrame.OptionsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

public class Player {

	private static Connection connection = Main.databaseConnection;

	private int id;
	private String name;
	private String shortname;
	private Time playtime = new Time("00:00:00");
	private int matchcounter;
	private int totalMinus;
	private int totalPlus;
	private int wins;
	private int losts;
	private int imgId;
	
	private int remainingLifePoints = 8000;

	private boolean winner = false;

	public Player() {
		super();
	}
	public Player(int id) {
		this.id = id;
		try {
			initialize();
		} catch (SQLException e) {
			ExceptionDialog exceptionDialog = new ExceptionDialog(e);
			exceptionDialog.show();
		}
	}
	public Player(String name, String shortname) {
		this.name = name;
		this.shortname = shortname;
		try {
			initializeWithName();
		} catch (SQLException e) {
//			ExceptionDialog exceptionDialog = new ExceptionDialog(e);
//			exceptionDialog.show();
		}
	}
	public Player(String name, String shortname, int imgId) {
		this.name = name;
		this.shortname = shortname;
		this.imgId = imgId;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getShortname() {
		return shortname;
	}
	public Time getPlaytime() {
		return playtime;
	}
	public int getTotalMinus() {
		return totalMinus;
	}
	public int getTotalPlus() {
		return totalPlus;
	}
	public int getWins() {
		return wins;
	}
	public int getLosts() {
		return losts;
	}
	public int getMatchcounter() {
		return matchcounter;
	}
	public void setMatchcounter(int matchcounter) {
		this.matchcounter = matchcounter;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public void setPlaytime(Time playtime) {
		this.playtime = playtime;
	}
	public void setTotalMinus(int totalMinus) {
		this.totalMinus = totalMinus;
	}
	public void setTotalPlus(int totalPlus) {
		this.totalPlus = totalPlus;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public void setLosts(int losts) {
		this.losts = losts;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public int getRemainingLifePoints() {
		System.out.println("Player.getRemainingLifePoints = " + remainingLifePoints + " for Player " + getName());
		return remainingLifePoints;
	}
	public void setRemainingLifePoints(int remainingLifePoints) {
		System.out.println("Player.setRemainingLifePoints = " + remainingLifePoints + " for Player " + getName());
		this.remainingLifePoints = remainingLifePoints;
	}
	public void initialize() throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "select * from PLAYER where id = " + this.id;
		ResultSet rs = statement.executeQuery(sql);
		rs.next();

		this.name = rs.getString(2);
		this.shortname = rs.getString(3);
		this.matchcounter = rs.getInt(4);
		this.playtime = new Time(rs.getTime(5).toString());
		this.totalMinus = rs.getInt(6);
		this.totalPlus = rs.getInt(7);
		this.wins = rs.getInt(8);
		this.losts = rs.getInt(9);
		this.imgId = rs.getInt(10);
	}
	public void initialize(int id) throws SQLException {
		this.id = id;
		initialize();
	}
	public void insertNewPlayer() throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "insert into Player(name, shortname, playtime, img_Id) values "
				+ "('" + this.name + "', '" 
				+ this.shortname + "', "
				+ "'00:00:00', "
				+ this.imgId + ")";

		statement.executeUpdate(sql);
	}

	public void update() {
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "update Player set "
					+ "name = '" + name + "', "
					+ "shortname = '" + shortname + "', "
					+ "playtime = '" + playtime + "', "
					+ "matchcounter = " + matchcounter + ", "
					+ "totalMinus = " + totalMinus + ", "
					+ "totallPlus = " + totalPlus + ", "
					+ "wins = " + wins + ", "
					+ "losts = " + losts + ", "
					+ "img_Id = " + imgId + " "
					+ "where id = " + getId();

			statement.executeUpdate(sql);
		} catch (SQLException e) {
			ExceptionDialog exceptionDialog = new ExceptionDialog(e);
			exceptionDialog.show();
		}
	}

	public ObservableList<Player> getAllPlayersAsObservableList() throws SQLException {
		ObservableList<Player> list = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		String sql = "select * from PLAYER";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			list.add(new Player(rs.getInt(1)));
		}
		return list;
	}

	private void initializeWithName() throws SQLException {
		Statement statement = connection.createStatement();	
		String sql = "select * from PLAYER where name = '" + name + "'";
		ResultSet rs = statement.executeQuery(sql);
		if (rs.next()) {
			initialize(rs.getInt(id));
		} 
	}
	public void delete() throws SQLException {
		Statement statement = connection.createStatement();	
		String sql = "delete from PLAYER where id = " + id;
		statement.execute(sql);
	}

	@Override
	public String toString() {
		return getPrefferedName();
	}

	public void addPlaytime(String time) {
		playtime.addTime(time);
	}
	public void addMinus(int minusLp) {
		totalMinus += minusLp;
	}
	public void addPlus(int plusLp) {
		totalPlus += plusLp;
	}
	public void addLost() {
		losts++;
	}
	public void addWins() {
		wins++;
	}
	public void addMatch() {
		matchcounter++;
	}

	public static ArrayList<Player> getAllPlayersAsArrayList() {
		ArrayList<Player> list = new ArrayList<>();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select * from PLAYER";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new Player(rs.getInt(1)));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Stack<Player> getAllPlayersAsStack() {
		Stack<Player> list = new Stack<>();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select * from PLAYER";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new Player(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int getPlaytimeAsMinutes() {
		return playtime.getPlaytimeAsMinutes();
	}
	public double getKD() {
		return (double)wins / (double)losts;
	}
	public String getPrefferedName() {
		OptionsData options = new OptionsData();
		if (options.isShortnamePreffered()) {
			return shortname;
		} else {
			return name;
		}
	}
}