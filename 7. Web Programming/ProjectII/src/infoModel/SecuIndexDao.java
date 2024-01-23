package infoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class SecuIndexDao {
	DataSource dataSource;

	public SecuIndexDao() {
		System.out.println("=[DB]====SecuIndexDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONArray getSecuIndex(String year) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		JSONArray lists = new JSONArray();
		
		
		try {
		con = dataSource.getConnection();
		String query = "SELECT row_number, guname FROM ";
		query += "(SELECT row_number() OVER (ORDER BY si." + year + " DESC) AS row_number, gu.guname ";
		query += "FROM gu_name gu JOIN secuIndex si ON gu.local = si.local) gu_query WHERE row_number <= 5";

		pstmt = con.prepareStatement(query);
		
		
		ResultSet rs = pstmt.executeQuery();
		System.out.println("=[DB]====쿼리문 실행완료");
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("rowNumber", (rs.getInt("row_number")));
			obj.put("guName", (rs.getString("guname")));
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
