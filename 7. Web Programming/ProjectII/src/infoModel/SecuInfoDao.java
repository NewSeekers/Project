package infoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class SecuInfoDao {
	
			
			DataSource dataSource;

	public SecuInfoDao() {
		System.out.println("=[DB]====SecuIndexDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONArray getSecuInfo(String year) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		JSONArray lists = new JSONArray();
				
		try {
		con = dataSource.getConnection();
		String query = "SELECT row_number, guname, "+year;
		query += " FROM (SELECT row_number() OVER (ORDER BY si."+year+" DESC) AS row_number, gu.guname, si."+year;
		query += " FROM gu_name gu JOIN secuIndex si ON gu.local = si.local) gu_query WHERE row_number <= 5";
		
		pstmt = con.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery();
		System.out.println("=[DB]==[secuinfo]==쿼리문 실행완료");
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("rowNumber", (rs.getInt("row_number")));
			obj.put("guName", (rs.getString("guname")));
			obj.put("secuValue", (rs.getDouble(year)));
			lists.put(obj);
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
