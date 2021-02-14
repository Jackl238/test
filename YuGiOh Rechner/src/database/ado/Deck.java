package database.ado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import main.Main;

public class Deck {

	private static Connection connection = Main.databaseConnection;
	
	private int id;
	private int playerId;
	private String name;
	private int wins;
	private int losts;
	private String percentageWinning;
	
	public Deck() {
		//DummyConstructor
	}
	public Deck(int id) {
		this.id = id;
		initialize();
	}
	public Deck(int playerId, String name) {
		this.playerId = id;
		this.name = name;
	} 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosts() {
		return losts;
	}
	public void setLosts(int losts) {
		this.losts = losts;
	}
	public String getPercentageWinning() {
		return percentageWinning;
	}
	//Gewinnquote
	public double getPercentWins() {
		double matchCount = wins + losts;
		return ((double) wins / matchCount) * 100;
	}
	public void addWin() {
		wins++;
	}
	public void addLost() {
		losts++;
	}
	private void initialize() {
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from DECKS where id = " + this.id;
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			this.playerId = rs.getInt(2);
			this.name = rs.getString(3);
			this.wins = rs.getInt(4);
			this.losts = rs.getInt(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DecimalFormat formatter = new DecimalFormat("#0.00");
		percentageWinning = formatter.format(getPercentWins()) + "%";
	}
	
	public void insert() { 
		if (!name.equals("")) {
			try {
				Statement statement = connection.createStatement();
				String sql = "insert into DECKS (playerid, name, winns, losts) values "
						+ "(" + playerId
						+ ", '"
						+ name
						+ "', 0, 0)";
				statement.execute(sql);
				System.out.println("Deck " + name + " insert");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void update() {
		if (!name.equals("")) {
			try {
				Statement statement = connection.createStatement();
				String sql = "update DECKS set playerId = " + playerId 
						+ ", name = '" + name 
						+ "', wins = " + wins 
						+ ", losts = " + losts
						+ " where id = " + id;
				statement.execute(sql);
				System.out.println("Deck " + id + " updated");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void delete() {
		if (id != 0) {
			try {
				Statement statement = connection.createStatement();
				String sql = "delete from DECKS where id = " + id;
				statement.execute(sql);
				System.out.println("Deck " + id + " deleted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<Deck> selectAllDecksOfPlayer(int playerId){
		ArrayList<Deck> deckList = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select id from decks where playerid = " + playerId;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				deckList.add(new Deck(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deckList;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + ", PlayerID: " + playerId + 
				", Name: " + name + ", Wins: " + wins + ", Losts: " + losts;
	}
}
