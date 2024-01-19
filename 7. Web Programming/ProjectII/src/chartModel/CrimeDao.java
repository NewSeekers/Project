package chartModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CrimeDao {

	DataSource dataSource;

	public CrimeDao() {
		System.out.println("=[DB]====CrimeDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public List<CrimeDto> getCrimeNum() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<CrimeDto> lists = new ArrayList<>();
		
		
		con = dataSource.getConnection();
		String query = "SELECT year, homicide, robber, sexual, theft, violence FROM seoul_crime where year in (2004,2007,2010,2013,2016,2019,2022) ORDER BY year ASC";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("=[DB]====쿼리문 실행완료");
		while(rs.next()) {
			CrimeDto cdto = new CrimeDto();
			cdto.setYear(rs.getInt("year"));
			cdto.setHomicide(rs.getInt("homicide"));
			cdto.setRobber(rs.getInt("robber"));
			cdto.setSexual(rs.getInt("sexual"));
			cdto.setTheft(rs.getInt("theft"));
			cdto.setViolence(rs.getInt("violence"));
			lists.add(cdto);
			
		}
		return lists;
	}
	
	
	
}
