package com.project.derby.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bean.Entries;
import com.bean.Players;
import com.derbydb.DBConnection;

public class PlayerEntriesDao {
	static Connection connection = DBConnection.getDBConnection();
	static Players player;
	static Entries entries;

	public String insertPlayer(Players player) throws SQLException {

		PreparedStatement preparedStatement = null;

		String insertPlayer = "INSERT INTO PLAYERS (NAME,MAIL,PHONE) \r\n" + "VALUES (?,?,?)";

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
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

		}

		return null;
	}

	public List<String> insertEntries(Entries entry) throws SQLException {

		PreparedStatement preparedStatement = null;
		List<String> generatedEntries = new ArrayList<String>();
		String insertEntries = "INSERT INTO ENTRIES (PLAYERID,DERBYWEIGHT)\r\n" + "VALUES (?,?)";

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
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}

		return generatedEntries;
	}

	public void displayRegisteredPlayers() {
		// TODO Auto-generated method stub

		PlayersMappingUI opponentMappingUI = new PlayersMappingUI();
		PreparedStatement selectPreparedStatement = null;
		List<Players> playersList = new ArrayList<Players>();

		String SelectQuery = "SELECT P.PLAYERID,P.NAME,E.ENTRYID,P.UPDATE_DT_TM FROM PLAYERS P\r\n"
				+ "JOIN ENTRIES E ON E.PLAYERID = P.PLAYERID \r\n" + "WHERE P.UPDATE_DT_TM  LIKE (?)\r\n"
				+ "ORDER BY P.PLAYERID";
		try {
			// connection.setAutoCommit(false);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String stringDate = sdf.format(date);
			String today = stringDate.concat("%");
			selectPreparedStatement = connection.prepareStatement(SelectQuery);
			selectPreparedStatement.setString(1, today);

			ResultSet rs = selectPreparedStatement.executeQuery();
			while (rs.next()) {
				player = new Players();
				entries = new Entries();
				entries.setEntryId(rs.getString("ENTRYID"));
				player.setPlayerId(rs.getString("PLAYERID"));
				player.setName(rs.getString("NAME"));
				player.setEntry(entries);
				playersList.add(player);
			}
			opponentMappingUI.initializeJTable(playersList);
			selectPreparedStatement.close();

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public void generateFights() {

		// TODO Auto-generated method stub

		PreparedStatement selectPreparedStatement = null;
		List<Players> playerList = new ArrayList<Players>();

		String SelectQuery = "SELECT E.ENTRYID,P.PLAYERID,P.NAME,E.DERBYWEIGHT,E.ACTIVEIND FROM ENTRIES E\r\n"
				+ "JOIN PLAYERS P ON P.PLAYERID =  E.PLAYERID\r\n"
				+ " WHERE E.ENTRYID IN (SELECT  MIN(ENTRYID) FROM ENTRIES WHERE ACTIVEIND = 1 GROUP BY (PLAYERID) ORDER BY PLAYERID )"
				+ "AND P.UPDATE_DT_TM  LIKE (?)";
		try {
			// connection.setAutoCommit(false);

			selectPreparedStatement = connection.prepareStatement(SelectQuery);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String stringDate = sdf.format(date);
			String today = stringDate.concat("%");
			selectPreparedStatement.setString(1, today);
			ResultSet rs = selectPreparedStatement.executeQuery();
			while (rs.next()) {
				entries = new Entries();
				player = new Players();
				String playerId = rs.getString("PlAYERID");
				String playerName = rs.getString("NAME");
				String entryId = rs.getString("ENTRYID");
				double cockWeight = rs.getDouble("DERBYWEIGHT");
				String playerIdName = playerId.concat("_").concat(playerName);
				entries.setEntryId(entryId);
				entries.setPalyerId(playerId);
				entries.setDerbyWeight(cockWeight);
				player.setName(playerIdName);
				player.setEntry(entries);
				player.setPlayerId(playerId);
				playerList.add(player);

			}

			createPairs(playerList);
			selectPreparedStatement.close();

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	public void createPairs(List<Players> playerList) {

		OpponentMappingUI opponentMappingUI = new OpponentMappingUI();
		Collections.sort(playerList);
		List<Players> pairList = new ArrayList<Players>();
		if ((playerList.size() == 2) && (playerList.get(1).getEntry().getDerbyWeight()
				- playerList.get(0).getEntry().getDerbyWeight() <= 2.0)) {
			opponentMappingUI.initializeJTable(playerList);
		} else {
			for (int i = 0; i < playerList.size() - 1; i++) {
				if ((playerList.get(i + 1).getEntry().getDerbyWeight()
						- playerList.get(i).getEntry().getDerbyWeight() <= 2.0)) {
					pairList.add(playerList.get(i));
				}
			}
			opponentMappingUI.initializeJTable(pairList);
		}

	}
}
