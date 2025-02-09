package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.LogVO;

@Repository
public class LogDao {
	
	private Connection con;
	
	public LogDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/mission3", "sa", "abcd");
		}
		catch (Exception e) {
			System.out.println("DB 연결 실패");
		}
	}
	
	public void addLog(String method, String sqlstring, boolean b) {
		addLog(LogVO.builder()
					.method(method)
					.sqlstring(sqlstring)
					.success(b)
					.build()
				);
	}
		
	public void addLog(LogVO logVO) {
		PreparedStatement psmt = null;
		
		try {
			psmt = con.prepareStatement("INSERT INTO DBLOG(method, sqlstring, success) values(?, ?, ?)");
			psmt.setString(1, logVO.getMethod());
			psmt.setString(2, logVO.getSqlstring());
			psmt.setBoolean(3, logVO.isSuccess()); //boolean type의 getter는 is로 만들어줌
			psmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(psmt != null) psmt.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 
}
