package board.dto;

public class BoardDTO {
	
	// DTO 부분 table 명 : springboard
	
	private int num;
	private String author, title, content, date;
	private int readcnt; // 조회수
	
	
	public BoardDTO() {
		
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getReadcnt() {
		return readcnt;
	}


	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	

}
