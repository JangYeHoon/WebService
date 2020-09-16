package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	public UserDao() {
		DBUtil.loadDriver();
	}
	//로그인
	public int UserLogin(UserBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "select UserPW, UserNum from UserInfo where UserID = ?";
		int result = -1;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, bean.getUserId());
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				if (resultSet.getString("UserPW").equals(bean.getUserPW())) {
					bean.setUserNum(resultSet.getInt(2));
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {}
		}
		
		return result;		
	}
	
	//회원 가입 
	public Boolean UserJoin(UserBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "insert into UserInfo (usernum, userid, userpw, username, userphone) values (seq.nextval, ?, ?, ?, ?)";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, bean.getUserId());
			statement.setString(2, bean.getUserPW());
			statement.setString(3, bean.getUserName());
			statement.setString(4, bean.getUserPhone());
			statement.executeQuery();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {}
		}
	}
	
	//중복 확인
	public int confirmId(String userId) {
		connection = DBUtil.makeConnection();
		String sql = "select userid from userinfo where userid = ?" ;
		int check = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userId);
			resultSet=statement.executeQuery();
			
			if(resultSet.next()) check = 0;
			else check = 1;
			return check;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				DBUtil.closeRs(resultSet);
				DBUtil.closePstmt(statement);
				DBUtil.closeCon(connection);
			} catch (Exception e) {}
		}
	}
}
