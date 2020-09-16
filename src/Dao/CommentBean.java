package Dao;

public class CommentBean {
	private int CommentNum;
	private int BoardNum;
	private int UserNum;
	private String CommentName;
	private String CommentMemo;
	private String CommentDate;
	
	public int getCommentNum() {
		return CommentNum;
	}
	public void setCommentNum(int commentNum) {
		CommentNum = commentNum;
	}
	public int getBoardNum() {
		return BoardNum;
	}
	public void setBoardNum(int boardNum) {
		BoardNum = boardNum;
	}
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getCommentName() {
		return CommentName;
	}
	public void setCommentName(String commentName) {
		CommentName = commentName;
	}
	public String getCommentMemo() {
		return CommentMemo;
	}
	public void setCommentMemo(String commentMemo) {
		CommentMemo = commentMemo;
	}
	public String getCommentDate() {
		return CommentDate;
	}
	public void setCommentDate(String commentDate) {
		CommentDate = commentDate;
	}
}
