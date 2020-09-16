package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class BoardDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public BoardDao() {
		DBUtil.loadDriver();
	}
	
	public void setReadCountUpdate(int num) {
		connection = DBUtil.makeConnection();
		String sql = "update board set boardCount = boardCount+1"
				+ "where boardNum = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			statement.executeUpdate();
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
	
	public int getListCount() {
		int x = 0;
		connection = DBUtil.makeConnection();
		String sql = "select count(*) from board";
		
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				x = resultSet.getInt(1);
			}
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
		return x;
	}
	
	public List getBoardList(int page, int limit) {
		connection = DBUtil.makeConnection();
		String sql = "select * from "
				+ "(select rownum rnum, boardnum, boardwriter, boardname, boardmemo,"
				+ "boardCount, boardDate from "
				+ "(select * from board)) "
				+ "where rnum >= ? and rnum <= ?";
		List list = new ArrayList();
		
		int startrow = (page-1)*10+1;
		int endrow = startrow+limit-1;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, startrow);
			statement.setInt(2, endrow);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BoardBean board = new BoardBean();
				board.setBoardNum(resultSet.getInt("boardnum"));
				board.setBoardWriter(resultSet.getString("boardwriter"));
				board.setBoardName(resultSet.getString("boardname"));
				board.setBoardMemo(resultSet.getString("boardMemo"));
				board.setBoardCount(resultSet.getInt("boardCount"));
				board.setBoardDate(resultSet.getString("boardDate"));
				list.add(board);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public BoardBean getBoardView(int num) {
		BoardBean bean = new BoardBean();
		connection = DBUtil.makeConnection();
		String sql = "select boardName, boardMemo, boardWriter, boardNum "
				+ "from board where boardNum = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				bean.setBoardNum(resultSet.getInt("boardNum"));
				bean.setBoardName(resultSet.getString("boardName"));
				bean.setBoardMemo(resultSet.getString("boardMemo"));
				bean.setBoardWriter(resultSet.getString("boardWriter"));
			}
			
			return bean;
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
		
		return null;
	}
	
	public void BoardInsert(BoardBean bean) {
		connection = DBUtil.makeConnection();
		String seq = "select max(BoardNum) from board";
		String sql = "insert into board values(?, ?, ?, 0, sysdate, ?, ?)";
		
		try {
			statement = connection.prepareStatement(seq);
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
				bean.setBoardNum(resultSet.getInt(1)+1);
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getBoardNum());
			statement.setString(2, bean.getBoardName());
			statement.setString(3, bean.getBoardWriter());
			statement.setString(4, bean.getBoardMemo());
			statement.setInt(5, bean.getUserNum());
			resultSet = statement.executeQuery();
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
	}
	
	public void BoardChange(BoardBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "update board set boardWriter = ?, boardName = ?, boardMemo = ?"
				+ " where boardNum = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, bean.getBoardWriter());
			statement.setString(2, bean.getBoardName());
			statement.setString(3, bean.getBoardMemo());
			statement.setInt(4, bean.getBoardNum());
			statement.executeUpdate();
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
	
	public void BoardDelete(int num) {
		connection = DBUtil.makeConnection();
		String sql = "delete from board where boardNum = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			statement.executeUpdate();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {
			}
		}
	}
	
	public boolean isBoardWriter(int boardNum, int userNum) {
		connection = DBUtil.makeConnection();
		String sql = "select userNum from board where boardNum = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, boardNum);
			resultSet = statement.executeQuery();
			
			resultSet.next();
			if (userNum == resultSet.getInt(1)) {
				return true;
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
		
		return false;
	}
}
