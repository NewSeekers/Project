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

public class SecuFaciDao {
	DataSource dataSource;

	public SecuFaciDao() {
		System.out.println("=[DB]====SecuFaciDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONArray getSecuFaci() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		JSONArray lists = new JSONArray();
		
		
		try {
		con = dataSource.getConnection();
		String query = "select * from seoul_secufacil where year in (2004,2007,2010,2013,2016,2019,2022)";
		pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("[DB]쿼리문 실행완료");
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("year", (rs.getInt("year")));
			obj.put("lights", (rs.getInt("lights")));
			obj.put("cctv", (rs.getInt("cctv")));
			obj.put("policestation", (rs.getInt("policestation")));
			obj.put("policeman", (rs.getInt("policeman")));
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
