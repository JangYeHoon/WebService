package Dao;

public class BoardBean {
	private int		boardNum;
	private	String	boardName;		// ����
	private String	boardWriter;	// �۾���
	private int		boardCount;		// ��ȸ��
	private String	boardDate;		// �ۼ���¥
	private String	boardMemo;		// ����
	private int		UserNum;
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardMemo() {
		return boardMemo;
	}
	public void setBoardMemo(String boardMemo) {
		this.boardMemo = boardMemo;
	}
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int UserNum) {
		this.UserNum = UserNum;
	}
}
