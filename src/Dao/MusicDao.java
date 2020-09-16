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

			// �ҷ��� ������ playList (resultSet) �� ���� �߰��Ϸ��� �ϴ� bean�� MusicNum�� ���Ͽ�
			// �̹� ���� ���� ������ false ��ȯ �ƴ� �� true ��ȯ.
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

	// DB�� �÷��̸���Ʈ�� ���� ����
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

	// DB���� �÷��̸���Ʈ �޾ƿ��� �κ�
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

	// ���� ��ŷ ����Ʈ �޾ƿ��� �κ�
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

	// �������� �������� ��õ ����Ʈ �޾ƿ��� �κ�
	public List videoRecommend() {
		connection = DBUtil.makeConnection();
		String query = "select * from musicinfo where MusicVideoPath is not null";
		List list = new ArrayList();
		List list2 = new ArrayList(); // �������� ���� ��̸���Ʈ
		int countOfVideo = 8;
		int same = 0;
		// �ϴ� ��� ������ ��񿡼� �����ͼ� list�� ������.
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

	// �÷��� ����Ʈ���� ����
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
	
	//�� ������ ����� ���� musicnum�޾ƿ���
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
	
	// �÷��� ����Ʈ���� ��� ����
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

	// �������� ��� �޾ƿ��� �κ�
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