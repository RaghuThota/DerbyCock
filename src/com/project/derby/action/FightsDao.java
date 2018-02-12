package com.project.derby.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.Entries;
import com.bean.Fights;
import com.bean.Players;
import com.derbydb.DBConnection;

public class FightsDao {
	static Connection connection = DBConnection.getDBConnection();
	private PlayerEntriesDao palyerEntriesDao = new PlayerEntriesDao();

	public void insertFights(List<Fights> fightList) throws SQLException {

		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		String insertEntries = "INSERT INTO Fights( FIGHTID,PLAYER,ENTRY,FIGHTSTATUS,POINTS) VALUES \r\n"
				+ "(?,?,?,?,?)";
		try {
			preparedStatement1 = connection.prepareStatement(insertEntries);
			preparedStatement2 = connection.prepareStatement(insertEntries);

			for (Fights fights : fightList) {
				preparedStatement1.setInt(1, fights.getFightId());
				preparedStatement2.setInt(1, fights.getFightId());

				preparedStatement1.setString(2, fights.getPlayer1IdName());
				preparedStatement1.setString(3, fights.getEntry1Id());

				preparedStatement2.setString(2, fights.getPlayer2IdName());
				preparedStatement2.setString(3, fights.getEntry2Id());

				String result = fights.getWonBy();
				if (result.equalsIgnoreCase("PLAYER1 WON")) {

					preparedStatement1.setString(4, fights.getPlayer1IdName() + " WON");
					preparedStatement2.setString(4, fights.getPlayer2IdName() + " LOST");
					preparedStatement1.setInt(5, 2);
					preparedStatement2.setInt(5, 0);
				} else if (result.equalsIgnoreCase("PLAYER2 WON")) {
					preparedStatement1.setString(4, fights.getPlayer1IdName() + " LOST");
					preparedStatement2.setString(4, fights.getPlayer2IdName() + " WON");
					preparedStatement1.setInt(5, 0);
					preparedStatement2.setInt(5, 2);
				} else if (result.equalsIgnoreCase("FIGHT DRAWN")) {
					preparedStatement1.setString(4, "FIGHT DRAWN");
					preparedStatement2.setString(4, "FIGHT DRAWN");
					preparedStatement1.setInt(5, 0);
					preparedStatement2.setInt(5, 0);
				} else {
					preparedStatement1.setString(4, "ABANDONED");
					preparedStatement2.setString(4, "ABANDONED");
					preparedStatement1.setInt(5, 1);
					preparedStatement2.setInt(5, 1);
				}

				int rowCount1 = preparedStatement1.executeUpdate();
				int rowCount2 = preparedStatement2.executeUpdate();

				if (rowCount1 > 0 && rowCount2 > 0) {
					if (updatePlayerEntries(fightList) > 0) {
						palyerEntriesDao.generateFights();
					}
				}

			}
			connection.commit();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement1 != null || preparedStatement2 != null) {
				preparedStatement1.close();
				preparedStatement2.close();
			}
		}
	}

	private int updatePlayerEntries(List<Fights> fightList) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		String insertEntries = "UPDATE ENTRIES SET ACTIVEIND = 0 WHERE ENTRYID IN (?,?)";

		try {
			int i = 0;
			preparedStatement = connection.prepareStatement(insertEntries);

			for (Fights fights : fightList) {

				preparedStatement.setString(1, fights.getEntry1Id().substring(0, fights.getEntry1Id().indexOf('_')));
				preparedStatement.setString(2, fights.getEntry2Id().substring(0, fights.getEntry2Id().indexOf('_')));

				int rowCount = preparedStatement.executeUpdate();
				if (rowCount > 0) {
					i++;
				}
			}
			if (i > 0) {
				return i;
			}
			connection.commit();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;

	}

	public void displayPoints() {

		// TODO Auto-generated method stub
		PointsMappingUI pointsMappingUI = new PointsMappingUI();

		PreparedStatement selectPreparedStatement = null;
		List<Players> pointsList =  new ArrayList<Players>();
		Players players = null;

		String SelectQuery = "SELECT PLAYER,SUM(POINTS) AS POINTS FROM "
				+ "FIGHTS WHERE UPDATE_DT_TM  LIKE (?) \r\n" + 
				"GROUP BY PLAYER ORDER BY POINTS DESC";
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
				players = new Players();
				players.setName(rs.getString("PLAYER"));
				players.setPoints(rs.getInt("POINTS"));
				pointsList.add(players);
			}

			pointsMappingUI.initializeJTable(pointsList);
			selectPreparedStatement.close();

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}
}
