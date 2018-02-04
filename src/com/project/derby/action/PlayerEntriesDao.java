package com.project.derby.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.Entries;
import com.bean.Players;
import com.derbydb.DBConnection;

public class PlayerEntriesDao {
	static Connection connection = DBConnection.getDBConnection();
	static Players player;
	static Entries entries;

	public static void main(String[] args) {

		PreparedStatement selectPreparedStatement = null;

		String SelectQuery = "SELECT P.PLAYERID,P.NAME,P.PHONE,P.ACTIVEIND AS PLAYERACTIVE,E.ENTRYID,E.DERBYWEIGHT,E.ACTIVEIND AS ENTRYACTIVE\r\n"
				+ " FROM PLAYERS P\r\n"
				+ "JOIN ENTRIES E ON P.PLAYERID = E.PLAYERID ORDER BY P.PLAYERID";
		try {
			// connection.setAutoCommit(false);

			selectPreparedStatement = connection.prepareStatement(SelectQuery);
			HashMap<String, List<Entries>> playerEntriesMap = new HashMap<String, List<Entries>>();
			List<Entries> entriesList = new ArrayList<Entries>();
			ResultSet rs = selectPreparedStatement.executeQuery();
			while (rs.next()) {

				player = new Players();
				entries = new Entries();

				String playerId = rs.getString("PlAYERID");
				String name = rs.getString("NAME");
				String playerIdName = playerId.concat("-").concat(name);
				player.setName(playerIdName);
				entries.setPalyerId(playerId);
				entries.setEntryId(rs.getString("ENTRYID"));
				entries.setActiveInd(rs.getShort("ENTRYACTIVE"));
				entries.setDerbyWeight(rs.getDouble("DERBYWEIGHT"));
				entriesList.add(entries);
				playerEntriesMap.put(playerIdName, entriesList);
				entriesList.removeAll(entriesList);
				System.out.println("playerIdName : " + player.getName() + "  playerid : "
						+ entries.getPalyerId() + " Entry Id : " + entries.getEntryId()
						+ " entry weight : " + entries.getDerbyWeight() + "avtive ind  : "
						+ entries.getActiveInd());
			}
			selectPreparedStatement.close();

			connection.commit();
		}
		catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

		}

	}

	public String insertPlayer(Players player) throws SQLException {

		PreparedStatement preparedStatement = null;

		String insertPlayer = "INSERT INTO PLAYERS (NAME,MAIL,PHONE) \r\n"
				+ "VALUES (?,?,?)";

		try {

			preparedStatement = connection.prepareStatement(insertPlayer);

			preparedStatement.setString(1, player.getName());
			preparedStatement.setString(2, player.getEmail());
			preparedStatement.setLong(3, player.getPhone());

			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			while (rs.next()) {
				String playerId = rs.getString(1);
				return playerId;
			}
		}
		catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

		}

		return null;
	}

	public List<String> insertEntries(Entries entry) throws SQLException {

		PreparedStatement preparedStatement = null;
		List<String> generatedEntries = new ArrayList<String>();
		String insertEntries = "INSERT INTO ENTRIES (PLAYERID,DERBYWEIGHT)\r\n"
				+ "VALUES (?,?)";

		try {

			preparedStatement = connection.prepareStatement(insertEntries);

			for (Double d : entry.getDerbyWeightList()) {
				preparedStatement.setString(1, entry.getPalyerId());
				preparedStatement.setDouble(2, d);
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				while (rs.next()) {
					String entryName = rs.getString(1);
					System.out.println("entry names : " + entryName);
					generatedEntries.add(entryName);
				}
			}
			connection.commit();
		}
		catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}

		return generatedEntries;
	}

	public void displayRegisteredPlayers() {
		// TODO Auto-generated method stub

		OpponentMappingUI opponentMappingUI = new OpponentMappingUI();
		PreparedStatement selectPreparedStatement = null;
		List<Players> playersList = new ArrayList<Players>();

		String SelectQuery = "SELECT PLAYERID,NAME,PHONE,ACTIVEIND AS PLAYERACTIVE FROM PLAYERS";
		try {
			// connection.setAutoCommit(false);

			selectPreparedStatement = connection.prepareStatement(SelectQuery);
			ResultSet rs = selectPreparedStatement.executeQuery();
			while (rs.next()) {
				player = new Players();
				entries = new Entries();
				String playerId = rs.getString("PlAYERID");
				String name = rs.getString("NAME");
				String playerIdName = playerId.concat("-").concat(name);
				player.setName(playerIdName);
				playersList.add(player);
			}
			opponentMappingUI.initializeJTable(playersList);
			selectPreparedStatement.close();

			connection.commit();
		}
		catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

		}

	}
}
