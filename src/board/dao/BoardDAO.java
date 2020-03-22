package board.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

// Context(Interface�̴�), InitialContext ��ü
// lookup(ã�����ϴ� �̸�(JNDI��)) -> Ž���⿡�� �˻��ϴ� �Ͱ� ���� ����
import javax.naming.Context;
import javax.naming.InitialContext;
// �߰� (JNDI ���)
// DataSource ��ü -> getConnection()
import javax.sql.DataSource;

import board.dto.BoardDTO;

public class BoardDAO {
	
	DataSource ds; // has a ����
	
	public BoardDAO() {
		
		try {
			// InitialContext ctx = new InitialContext(); �̰͵� ����
			Context ctx = new InitialContext();
			
			// lookup("java:comp/env/ã�����ϴ� JNDI�̸�")
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds: " + ds);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* �� ��� ��ȸ �޼��� */
	public ArrayList list() {
		ArrayList list = new ArrayList();
		
		try {
			String sql = "SELECT * FROM springboard ORDER BY num desc";
			Connection con = ds.getConnection();
			// Connection con = pool.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO data = new BoardDTO();
				
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setDate(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				
				list.add(data);
				
			}
			rs.close(); stmt.close(); con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	/* 글 번호 얻기 메서드 */
	public int getNewNum() {
		int newNum = 1;
		try {
			String sql = "SELECT max(num) FROM sptringboard";
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				// 가상 필드이기 때문에, 필드명을 쓸 수 없어서 인덱스(1)을 사용
				newNum = rs.getInt(1) +1;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newNum;
	}
	
	/* 글 쓰기 메서드 */
	// public void write(BoardCommand boardDTO){} 가 정석
	// 그러나, 3개만 받기위해 아래와 같이 작성
	public void write(String author, String title, String content) {
		
		try {
			int newNum = getNewNum(); // 최대값을 구해오는 메소드 호출
			System.out.println("newNum : " + newNum);
			String sql = "insert into springboard(num, author, title, content) values (?, ?, ?, ?)";
			
			
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, newNum);
			stmt.setString(2, author);
			stmt.setString(3, title);
			stmt.setString(4, content);
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
