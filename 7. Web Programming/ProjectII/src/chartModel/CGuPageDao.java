package chartModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import chartCommand.GuPageSecuGrade;

public class CGuPageDao {
	DataSource dataSource;
	private static CGuPageDao instance;
	
	public CGuPageDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static CGuPageDao getInstance() {
		if( instance == null) {
			instance = new CGuPageDao();
		}
		return instance;
	}

	// 범죄율 차트 json
	public JSONArray getArRate(String guNameValue) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray ar_rate = new JSONArray();
		
		try {
			con = dataSource.getConnection();
			String query = "SELECT total_ar_rate, year FROM gu_crime WHERE gu_crime.year IN (2004, 2007, 2010, 2013, 2016, 2019, 2022)"
					+ " AND gu_crime.local IN ( SELECT gu_name.local FROM gu_name WHERE guname =?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, guNameValue);
			rs = pstmt.executeQuery();
			System.out.println("getArRate 쿼리문 실행");
			
			while(rs.next()) {
				
				JSONObject json = new JSONObject();
				json.put("year", rs.getInt("year"));
				//json.put("local", rs.getInt("local"));
				json.put("total_ar_rate", rs.getInt("total_ar_rate"));
			
				ar_rate.put(json);
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
		return ar_rate;
	}
	
	public JSONObject getPerceivedSafety(String year, String guNameValue) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    JSONObject rank = new JSONObject();
	   
	    try {
	        con = dataSource.getConnection();
	        String query = "select "+year+"_rank from (SELECT local, "+year+", RANK() OVER (ORDER BY "+year+" DESC) AS "+year+"_rank FROM perceivedsafety) where local in(select local from gu_name where guname = ?)";
	        
	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, guNameValue);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            rank.put("rank", rs.getInt(1));
	            System.out.println(rs.getInt(1));
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in the finally block to ensure proper cleanup
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch(Exception e1) {
	            e1.printStackTrace();
	        }
	    }

	    return rank;
	}
	
	public JSONObject getSecufacil(String guNameValue) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    JSONObject secufacil = new JSONObject();
	   
	    try {
	        con = dataSource.getConnection();
	        String query = "SELECT  trunc(AVG(cctv)) as avg_cctv, trunc(AVG(policestation))as avg_policestation, trunc(AVG(lights))as avg_lights, MAX(CASE WHEN guname = ? THEN cctv END)as cctv, MAX(CASE WHEN guname = ? THEN policestation END) as policestation, MAX(CASE WHEN guname = ? THEN lights END) as lights FROM  gu_secufacil JOIN  gu_name ON gu_secufacil.local = gu_name.local WHERE  year = 2022";
	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, guNameValue);
	        pstmt.setString(2, guNameValue);
	        pstmt.setString(3, guNameValue);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	        	secufacil.put("cctv", rs.getString("cctv"));
	        	secufacil.put("lights", rs.getString("lights"));
	        	secufacil.put("policestation", rs.getString("policestation"));
	        	secufacil.put("avg_cctv", rs.getString("avg_cctv"));
	        	secufacil.put("avg_lights", rs.getString("avg_lights"));
	        	secufacil.put("avg_policestation", rs.getString("avg_policestation"));
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in the finally block to ensure proper cleanup
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch(Exception e1) {
	            e1.printStackTrace();
	        }
	    }
		return secufacil;
	}


	public JSONObject getSecugrade(String guNameValue) {
		Connection con;
		PreparedStatement pstmt;
		ResultSet rs;
		JSONObject secuGrade = new JSONObject();
		try {
			con = dataSource.getConnection();
			String query = "SELECT gu_name.guname, gu_env.population, gu_secugrade.y2022 FROM gu_name JOIN gu_env ON gu_name.local = gu_env.local"
					+ " JOIN gu_secugrade ON gu_name.local = gu_secugrade.local WHERE gu_name.guname = ? and gu_env.year='2022'";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, guNameValue);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				secuGrade.put("guname",rs.getString("guname"));
				secuGrade.put("population",rs.getString("population"));
				secuGrade.put("secugrade",rs.getString("y2022"));
			}
			if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		} 
		return secuGrade;
	}
}
