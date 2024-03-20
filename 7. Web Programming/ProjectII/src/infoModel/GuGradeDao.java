package infoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuGradeDao {
	DataSource dataSource;

	public GuGradeDao() {
		System.out.println("=[DB]====CrimeDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public List<GuGradeDto> getGuGradeNum() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<GuGradeDto> lists = new ArrayList<>();
		
		try {
		con = dataSource.getConnection();
		String query = "SELECT y2004, y2007, y2010, y2013, y2016, y2019, y2022 FROM gu_secugrade ORDER BY local DESC";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("=[DB]====쿼리문 실행완료");
		while(rs.next()) {
			GuGradeDto gdto = new GuGradeDto();
			gdto.setY2004(rs.getInt("Y2004"));
			gdto.setY2007(rs.getInt("Y2007"));
			gdto.setY2010(rs.getInt("Y2010"));
			gdto.setY2013(rs.getInt("Y2013"));
			gdto.setY2016(rs.getInt("Y2016"));
			gdto.setY2019(rs.getInt("Y2019"));
			gdto.setY2022(rs.getInt("Y2022"));
			
			lists.add(gdto);
			
		}
		rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		pstmt.close();
		con.close();
		}
		return lists;
	}
	
}
