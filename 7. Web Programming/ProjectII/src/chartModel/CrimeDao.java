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
		
		try {
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
		rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		pstmt.close();
		con.close();
		}
		return lists;
	}
	
	
	
	
	public List<CrimeDto> getArrestNum() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<CrimeDto> lists = new ArrayList<>();
		
		try {
		con = dataSource.getConnection();
		String query = "SELECT year, homi_ar_rate, rob_ar_rate, sex_ar_rate, thef_ar_rate, viol_ar_rate FROM seoul_crime where year in (2004,2007,2010,2013,2016,2019,2022) ORDER BY year ASC";
		System.out.println(query);
		pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("=[DB]====쿼리문 실행완료");
		while(rs.next()) {
			CrimeDto cdto = new CrimeDto();
			cdto.setYear(rs.getInt("year"));
			cdto.setHomiArRate(rs.getInt("homi_ar_rate"));
			cdto.setRobArRate(rs.getInt("rob_ar_rate"));
			cdto.setSexArRate(rs.getInt("sex_ar_rate"));
			cdto.setThefArRate(rs.getInt("thef_ar_rate"));
			cdto.setViolArRate(rs.getInt("viol_ar_rate"));
			lists.add(cdto);
			
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
