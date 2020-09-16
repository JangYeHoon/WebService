package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MusicDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public MusicDao() {
		DBUtil.loadDriver();
	}

	public void MusicPlayCountPlus(int num) {
		connection = DBUtil.makeConnection();
		String sql = "update MusicInfo set MusicPlayNum = MusicPlayNum+1 where MusicNum = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}
	
	public void MusicLikeCountPlus(int num) {
		connection = DBUtil.makeConnection();
		String sql = "update MusicInfo set MusicLike = MusicLike+1 where MusicNum = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}

	public boolean checkPlayList(PlaylistBean bean) {
		connection = DBUtil.makeConnection();
		resultSet = null;

		String sql = "select MusicNum from PlayList where UserNum = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getUserNum());
			resultSet = statement.executeQuery();

			// 불러온 유저의 playList (resultSet) 과 현재 추가하려고 하는 bean의 MusicNum을 비교하여
			// 이미 같은 것이 있으면 false 반환 아닐 시 true 반환.
			while (resultSet.next()) {
				if (resultSet.getInt(1) == bean.getMusicNum())
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return true;
	}

	// DB에 플레이리스트에 음악 저장
	public int MusicInsert(PlaylistBean bean) {
		int result = 0;
		connection = DBUtil.makeConnection();
		String sql = "insert into PlayList(MusicNum, UserNum) values (?, ?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getMusicNum());
			statement.setInt(2, bean.getUserNum());
			statement.executeUpdate();
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return result;
	}

	// DB에서 플레이리스트 받아오는 부분
	public List PlayList(int userNum) {
		List list = new ArrayList();
		connection = DBUtil.makeConnection();
		String sql = "select PlayList.MusicNum, MusicName, MusicArtist, MusicPlayNum, MusicVideoPath "
				+ "from MusicInfo inner join PlayList on MusicInfo.MusicNum = PlayList.MusicNum " + "where UserNum = ?";

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userNum);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				MusicBean bean = new MusicBean();
				bean.setMusicNum(resultSet.getInt(1));
				bean.setMusicName(resultSet.getString(2));
				bean.setMusicArtist(resultSet.getString(3));
				bean.setMusicPlayNum(resultSet.getInt(4));
				bean.setMusicVideoPath(resultSet.getString(5));
				list.add(bean);
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

		return list;
	}

	// 음악 랭킹 리스트 받아오는 부분
	public List MusicRank() {
		List list = new ArrayList();
		connection = DBUtil.makeConnection();
		String sql = "select MusicNum, MusicName, MusicArtist, MusicPlayNum, MusicVideoPath, MusicLike  from MusicInfo "
				+ "order by musicplaynum desc";

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				MusicBean bean = new MusicBean();
				bean.setMusicNum(resultSet.getInt(1));
				bean.setMusicName(resultSet.getString(2));
				bean.setMusicArtist(resultSet.getString(3));
				bean.setMusicPlayNum(resultSet.getInt(4));
				bean.setMusicVideoPath(resultSet.getString(5));
				bean.setMusicLike(resultSet.getInt(6));
				list.add(bean);
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
		return list;
	}

	// 뮤직비디오 랜덤으로 추천 리스트 받아오는 부분
	public List videoRecommend() {
		connection = DBUtil.makeConnection();
		String query = "select * from musicinfo where MusicVideoPath is not null";
		List list = new ArrayList();
		List list2 = new ArrayList(); // 랜덤으로 얻어올 어레이리스트
		int countOfVideo = 8;
		int same = 0;
		// 일단 모든 음악을 디비에서 가져와서 list에 저장함.
		try {
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				MusicBean musicbean = new MusicBean();
				musicbean.setMusicNum(resultSet.getInt("MusicNum"));
				musicbean.setMusicArtist(resultSet.getString("MusicArtist"));
				musicbean.setMusicName(resultSet.getString("MusicName"));
				musicbean.setMusicVideoPath(resultSet.getString("MusicVideoPath"));
				list.add(musicbean);
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

		Random random = new Random();
		int[] randomNumber = new int[countOfVideo];

		for (int i = 0; i < countOfVideo; i++) {
			randomNumber[i] = random.nextInt(list.size());
			list2.add(list.get(randomNumber[i]));
		}
		while (same != 0) {
			same = 0;
			for (int i = 0; i < countOfVideo; i++) {
				for (int j = 0; j < i - 1; j++)
					if (randomNumber[i] == randomNumber[j]) {
						same++;
						randomNumber[i] = random.nextInt(list.size());
					}
			}
		}
		return list2;
	}

	// 플레이 리스트에서 삭제
	public void playListDelete(PlaylistBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "delete from playlist where musicNum = ? and usernum = ?";

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getMusicNum());
			statement.setInt(2, bean.getUserNum());
			statement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}
	
	//각 음악을 지우기 위해 musicnum받아오기
	public int getmusicNum(PlaylistBean bean){
		connection = DBUtil.makeConnection();
		String sql = "select musicnum from playlist where usernum = ?";
		int result = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getUserNum());
			resultSet = statement.executeQuery();
			resultSet.next();
			result = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
				DBUtil.closeRs(resultSet);
			} catch (Exception e) {
			}
		}
		return result;
	}
	
	// 플레이 리스트에서 모두 삭제
	public void playListallDelete(PlaylistBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "delete from playlist where userNum = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getUserNum());
			statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}

	// 뮤직비디오 경로 받아오는 부분
	public MusicBean videoPath(int title) {
		connection = DBUtil.makeConnection();
		String sql = "select MusicVideoPath, MusicName, MusicNum, MusicArtist from MusicInfo where MusicNum = ?";
		MusicBean music1 = new MusicBean();
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, title);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String url2 = "//www.youtube.com/embed/" + resultSet.getString(1);
				music1.setMusicVideoPath(url2);
				music1.setMusicName(resultSet.getString(2));
				music1.setMusicNum(resultSet.getInt(3));
				music1.setMusicArtist(resultSet.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(resultSet);
			DBUtil.closePstmt(statement);
			DBUtil.closeCon(connection);
		}

		return music1;
	}
}