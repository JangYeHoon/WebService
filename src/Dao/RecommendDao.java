package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RecommendDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	double array[][] = new double[100][11];

	public RecommendDao() {
		DBUtil.loadDriver();
	}

	public void setValue() {
		connection = DBUtil.makeConnection();
		int i = 0;
		String sql = "select MusicIdol, MusicBallad, MusicTrot, MusicPop, MusicClassic, MusicJazz, MusicHiphop, MusicRap, MusicInde, MusicAcoustic, MusicDance from musicinfo";
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				array[i][0] = resultSet.getInt(1);
				array[i][1] = resultSet.getInt(2);
				array[i][2] = resultSet.getInt(3);
				array[i][3] = resultSet.getInt(4);
				array[i][4] = resultSet.getInt(5);
				array[i][5] = resultSet.getInt(6);
				array[i][6] = resultSet.getInt(7);
				array[i][7] = resultSet.getInt(8);
				array[i][8] = resultSet.getInt(9);
				array[i][9] = resultSet.getInt(10);
				array[i][10] = resultSet.getInt(11);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}

	// 플레이리스트의 장르 값 빼서 추천 곡 NUM ㄹㅣ턴
	public int recommendMusic(int UserNum) {
		connection = DBUtil.makeConnection();
		String sql = "select sum(MusicIdol), sum(MusicBallad), sum(MusicTrot), sum(MusicPop), sum(MusicClassic), sum(MusicJazz), sum(MusicHiphop), sum(MusicRap), sum(MusicInde), sum(MusicAcoustic), sum(MusicDance), count(MusicIdol) "
				+ "from MusicInfo inner join PlayList on MusicInfo.MusicNum = PlayList.MusicNum where UserNum = ?";
		double array1[] = new double[11];
		int musicnum = 0;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, UserNum);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(12);

				for (int i = 1; i < 12; i++)
					array1[i - 1] = resultSet.getInt(i) / count;

				for (int j = 0; j < 100; j++) {
					for (int k = 0; k < 11; k++) {
						array[j][k] = Math.abs(array[j][k] - array1[k]);
					}
				}
				double least = 0;
				for (int j = 0; j < 11; j++)
					least = least + array[0][j];
				double leastSum = 0;
				for (int j = 1; j < 100; j++) {
					for (int k = 0; k < 11; k++) {
						leastSum = array[j][k] + leastSum;
					}
					if (least >= leastSum)
						least = leastSum;
					leastSum = 0;
				}
				int check[] = new int[100];
				int cnt = 0;
				for (int j = 0; j < 100; j++) {
					for (int k = 0; k < 11; k++) {
						leastSum = array[j][k] + leastSum;
					}
					if (least == leastSum) {
						check[cnt] = j;
						cnt++;
					}
					leastSum = 0;
				}
				Random rand = new Random();
				musicnum = check[rand.nextInt(cnt)];
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return musicnum;
	}

	public void getMusicInfo(int musicNum, MusicBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "select MusicName, MusicArtist from MusicInfo where MusicNum = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, musicNum);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				bean.setMusicArtist(resultSet.getString(2));
				bean.setMusicName(resultSet.getString(1));
				bean.setMusicNum(musicNum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}

	}
}
