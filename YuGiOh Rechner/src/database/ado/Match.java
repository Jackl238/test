package database.ado;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

import customizedClasses.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Main;

public class Match {

	private static Connection connection = Main.databaseConnection;

	public static final int WINNER_PLAYER_1 = 1;
	public static final int WINNER_PLAYER_2 = 2;
	public static final int DRAW = 0;

	private int id;
	private int player1_id;
	private int player2_id;
	private Time playtime;
	private int winner;
	private Date date;
	private int lifePointsPlayer1;
	private int lifePointsPlayer2;
	private int cubes;
	private int coins;
	private int winningReasonId;
	
	private String winningReason;
	private String namePlayer1;
	private String namePlayer2;

	public Match() {

	}
	public Match(int id) {
		try {
			initialize(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Match(int player1_id, int player2_id, Time playtime2, int winner, Date date, int lifePointsPlayer1,
			int lifePointsPlayer2, int cubes, int coins, int winningReasonId) {
		this(player1_id, player2_id, playtime2, winner, date, lifePointsPlayer1, lifePointsPlayer2, cubes, coins);
		this.winningReasonId = winningReasonId;
	}
	
	public Match(int player1_id, int player2_id, Time playtime2, int winner, Date date, int lifePointsPlayer1,
			int lifePointsPlayer2, int cubes, int coins) {
		this.player1_id = player1_id;
		this.player2_id = player2_id;
		this.playtime = playtime2;
		this.winner = winner;
		this.date = date;
		setLifePointsPlayer1(lifePointsPlayer1);
		setLifePointsPlayer2(lifePointsPlayer2);
		this.cubes = cubes;
		this.coins = coins;
	}

	private void loadPlayerNames() {
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select * from Player where id = " + player1_id;
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			namePlayer1 = rs.getString(2);
			sql = "select * from Player where id = " + player2_id;
			rs = statement.executeQuery(sql);
			rs.next();
			namePlayer2 = rs.getString(2);
		} catch (SQLException e) {

		}
	}
	private void loadWinningReason() {
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select * from winningreason where id = " + winningReasonId;
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			winningReason = rs.getString(2);
		} catch (SQLException e) {

		}
	}
	public int getId() {
		return id;
	}
	public int getPlayer1_id() {
		return player1_id;
	}
	public int getPlayer2_id() {
		return player2_id;
	}
	public Time getPlaytime() {
		return playtime;
	}
	public int getWinner() {
		return winner;
	}
	public Date getDate() {
		return date;
	}
	public String getNamePlayer1() {
		return namePlayer1;
	}
	public String getNamePlayer2() {
		return namePlayer2;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPlayer1_id(int player1_id) {
		this.player1_id = player1_id;
	}
	public void setPlayer2_id(int player2_id) {
		this.player2_id = player2_id;
	}
	public void setPlaytime(Time playtime) {
		this.playtime = playtime;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public int getLifePointsPlayer1() {
		return lifePointsPlayer1;
	}
	public void setLifePointsPlayer1(int lifePointsPlayer) {
		if (lifePointsPlayer < 0) {
			lifePointsPlayer = 0;
		}
		this.lifePointsPlayer1 = lifePointsPlayer;
	}
	public int getLifePointsPlayer2() {
		return lifePointsPlayer2;
	}
	public void setLifePointsPlayer2(int lifePointsPlayer) {
		if (lifePointsPlayer < 0) {
			lifePointsPlayer = 0;
		}
		this.lifePointsPlayer2 = lifePointsPlayer;
	}
	public int getCubes() {
		return cubes;
	}
	public void setCubes(int cubes) {
		this.cubes = cubes;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getWinningReasonId() {
		return winningReasonId;
	}
	public void setWinningReasonId(int winningReasonId) {
		this.winningReasonId = winningReasonId;
	}
	public void setNamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}
	public void setNamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}
	public String getWinningReason() {
		return winningReason;
	}
	public void setWinningReason(String winningReason) {
		this.winningReason = winningReason;
	}
	public void initialize() throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "select * from MATCHES where id = " + this.id;
		ResultSet rs = statement.executeQuery(sql);
		rs.next();

		this.player1_id = rs.getInt(2);
		this.player2_id = rs.getInt(3);
		this.playtime = new Time(rs.getTime(4));
		this.winner = rs.getInt(5);
		this.date = rs.getDate(6);
		this.lifePointsPlayer1 = rs.getInt(7);
		this.lifePointsPlayer2 = rs.getInt(8);
		this.cubes = rs.getInt(9);
		this.coins = rs.getInt(10);
		this.winningReasonId = rs.getInt(11);
		loadPlayerNames();
		loadWinningReason();
	}
	public void initialize(int id) throws SQLException {
		this.id = id;
		initialize();
	}
	public void insertNewMatch() throws SQLException {
		Statement statement = connection.createStatement();

		String sql = "insert into Matches(player1_ID, player2_ID, playtime, winner, date, lifePointsPlayer1, lifePointsPlayer2, cubes, coins, winningreasonid) values "
				+ "(" + this.player1_id + ", " 
				+ this.player2_id + ", '" 
				+ this.playtime + "', "
				+ this.winner + ", '"
				+ this.date + "', "
				+ this.lifePointsPlayer1 + ","
				+ this.lifePointsPlayer2 + ","
				+ this.cubes + ","
				+ this.coins + ","
				+ this.winningReasonId
				+ ")";

		statement.executeUpdate(sql);
	}

	public void update() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "update Matches set "
				+ "player1_id = '" + player1_id + "', "
				+ "player2_id = '" + player2_id + "', "
				+ "playtime = " + playtime + ","
				+ "winner = " + winner + ", "
				+ "date = " + date + ","
				+ "lifePointsPlayer1 = " + lifePointsPlayer1 + ", "
				+ "lifePointsPlayer2 = " + lifePointsPlayer2 + ", "
				+ "cubes = " + cubes + ", "
				+ "coins = " + coins
				+ "winningreasonid = " + winningReasonId
				+ "where id = " + id;

		statement.executeUpdate(sql);
	}

	public void delete() throws SQLException {
		Statement statement = connection.createStatement();	
		String sql = "delete from Matches where id = " + id;
		statement.execute(sql);
	}

	public ArrayList<Match> getAllMatchesAsArrayList() throws SQLException {
		ArrayList<Match> list = new ArrayList<>();
		Statement statement = connection.createStatement();
		String sql = "select * from Matches where id = " + this.id;
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			list.add(new Match(rs.getInt(1)));
		}
		return list;
	}

	public ArrayList<Match> getAllMatchesOfPlayerIdAsArrayList(int playerId) throws SQLException {
		ArrayList<Match> list = new ArrayList<>();
		Statement statement = connection.createStatement();
		String sql = "select * from Matches where player1_id = " + id;
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			list.add(new Match(rs.getInt(1)));
		}
		sql = "select * from Matches where player2_id = " + id;
		rs = statement.executeQuery(sql);
		while(rs.next()) {
			list.add(new Match(rs.getInt(1)));
		}
		return list;
	}

	public Player getMatchWinner() {
		try {
			Statement statement = connection.createStatement();
			if (winner == WINNER_PLAYER_1) {
				String sql = "select player1_id from Matches where id = " + id;
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				return new Player(rs.getInt(1));
			} else {
				String sql = "select player2_id from Matches where id = " + id;
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				return new Player(rs.getInt(1));
			}
		}catch (SQLException e) {
			//Ja mei is hald aso
			return null;
		}
	}

	public static Stack<Match> getMatchesAsStack(){
		Stack<Match> stack = new Stack<>();

		try {
			Statement statement = connection.createStatement();
			String sql = "select * from Matches";
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				stack.add(new Match(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stack;
	}
	public int getPlaytimeAsMinutes() {
		return playtime.getPlaytimeAsMinutes();
	}
	public String getMatchName() {
		String matchname = "NaN";

		if(player1_id != 0 && player2_id != 0) {
		Player player1 = new Player(player1_id);
		Player player2 = new Player(player2_id);

		matchname = player1.getName() + " VS " + player2.getName() + " - " + date;
		}
		return matchname;
	}
	public static ObservableList<Match> getMatchesAsObservableList() {
		ObservableList<Match> list = FXCollections.observableArrayList();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select * from MATCHES";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new Match(rs.getInt(1)));
			}
		} catch (SQLException e) {

		}
		return list;
	}
	public static ArrayList<Match> getMatchesOfPlayerAsArrayList(int playerId) {
		ArrayList<Match> list = new ArrayList<>();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "select * from MATCHES where player1_id = " + playerId + " or player2_id = " + playerId;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				list.add(new Match(rs.getInt(1)));
			}
		} catch (SQLException e) {

		}
		return list;
	}
	public int getLifePoints(int playerId) {
		if (playerId == player1_id) {
			return lifePointsPlayer1;
		}else if (playerId == player2_id) {
			return lifePointsPlayer2;
		}else {
			return -1;
		}
	}
	public static Player getEmeyOfPlayer(Player player) {
		Player tmpPlayer = new Player();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = 	
					"select playerid, sum(count) from (" +
							"(Select player2_id as playerid, count(player2_id) as count from matches where player1_id = " + player.getId() + " group by player2_id)"
							+ "Union "
							+ "(select player1_id, count(player1_id) from matches where player2_id = " + player.getId() + " group by player1_id)"
							+ "order by 2 desc)"
							+ " group by playerid;"
							;
			ResultSet rs = statement.executeQuery(sql);

			if (rs.next()) {
				tmpPlayer = new Player(rs.getInt(1));
			} else {
				tmpPlayer = player;
			}
		} catch (SQLException e) {
		}
		return tmpPlayer;
	}
	public static Match[] getFastestAndLongestMatchesOfPlayer(Player player) {
		Match[] array = new Match[2];

		Match tmpMatch = new Match();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = 	
					"select id, playtime from matches where (player1_id = " + player.getId() + 
					"or player2_id = " + player.getId() + 
					") and playtime > '00:00:00' order by playtime asc";
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				array[0] = new Match(rs.getInt(1));
				while (rs.next()) {
					tmpMatch = new Match(rs.getInt(1));
					System.out.println("Match.getMinMaxPlaytime:" + tmpMatch);
				}
				array[1] = tmpMatch;
			} else {
				Match dummyMatch = new Match();
				dummyMatch.setPlaytime(new Time("00:00:00"));
				array[0] = dummyMatch;
				array[1] = dummyMatch;
			}


		}catch (Exception e) {

		}
		return array;
	}

	public double getPlaytimeAsDouble() {
		return playtime.getPlaytimeAsMinutesAsDouble();
	}

	@Override
	public String toString() {
		return getMatchName() + " Playtime: " + playtime; 
	}
}
