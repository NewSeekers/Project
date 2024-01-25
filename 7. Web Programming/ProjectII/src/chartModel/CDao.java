package chartModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class CDao {
	DataSource dataSource;
	private static CDao instance;
	
	public CDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static CDao getInstance() {
		if( instance == null) {
			instance = new CDao();
		}
		return instance;
	}
	
	
	// 범죄율 차트 json
	public JSONArray getArRate(String guNameValue) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray list = new JSONArray();
		
		try {
			con = dataSource.getConnection();
			String query = "SELECT total_ar_rate, year FROM gu_crime WHERE gu_crime.year IN (2004, 2007, 2010, 2013, 2016, 2019, 2022) AND gu_crime.local IN ( SELECT gu_name.local FROM gu_name WHERE guname =?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, guNameValue);
			rs = pstmt.executeQuery();
			System.out.println("getArRate 쿼리문 실행");
			
			while(rs.next()) {
				
				JSONObject json = new JSONObject();
				json.put("year", rs.getInt("year"));
				//json.put("local", rs.getInt("local"));
				json.put("total_ar_rate", rs.getInt("total_ar_rate"));
			
				list.put(json);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	
	public JSONArray getChart() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		JSONArray list = new JSONArray();
		
		try {
			con = dataSource.getConnection();
			String query = "select * from gupage_chart";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int year = rs.getInt("year");
				int local = rs.getInt("local");
				int population = rs.getInt("population");
				int gu_secugrade = rs.getInt("gu_secugrade");
								
				JSONObject json = new JSONObject();
				json.put("year", year);
				json.put("local", local);
				json.put("population", population);
				json.put("gu_secugrade", gu_secugrade);
				json.put("gu_cctv",rs.getInt("gu_cctv"));
				json.put("gu_light", rs.getInt("gu_light"));
				json.put("gu_police", rs.getInt("gu_police"));
				json.put("ar_rate", rs.getInt("ar_rate"));
				list.put(json);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}
