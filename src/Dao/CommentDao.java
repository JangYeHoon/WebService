package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CommentDao {
	private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    
    public CommentDao(){
    	DBUtil.loadDriver();
    }
	public void CommentWrite(CommentBean bean) {
		connection = DBUtil.makeConnection();
		String sql = "insert into TLB_COMMENT"
				+ "(CommentNum, BoardNum, UserNum, CommentName, CommentMemo, CommentDate) "
				+ "values(CommentSeq.nextval, ?, ?, ?, ?, sysdate)";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, bean.getBoardNum());
			statement.setInt(2, bean.getUserNum());
			statement.setString(3, bean.getCommentName());
			statement.setString(4, bean.getCommentMemo());
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
	public List CommentList(int boardNum) {
		List list = new ArrayList();
        connection = DBUtil.makeConnection();
        String sql = "select CommentNum, BoardNum, UserNum, CommentName, CommentMemo, CommentDate "
        		+ "from TLB_COMMENT where BoardNum = ? "
        		+ "order by CommentDate";
        
        try {    
            statement = connection.prepareStatement(sql);
            statement.setInt(1, boardNum);
            resultSet = statement.executeQuery();
            
            while(resultSet.next())
            {
                CommentBean comment = new CommentBean();
                comment.setCommentNum(resultSet.getInt(1));
                comment.setBoardNum(resultSet.getInt(2));
                comment.setUserNum(resultSet.getInt(3));
                comment.setCommentName(resultSet.getString(4));
                comment.setCommentMemo(resultSet.getString(5));
                comment.setCommentDate(resultSet.getString(6));
                list.add(comment);
            }
            return list;
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
	public void deleteComment(int num){
		connection = DBUtil.makeConnection();
		String sql = "delete from tlb_comment where commentNum = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, num);
			statement.executeQuery();
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
}