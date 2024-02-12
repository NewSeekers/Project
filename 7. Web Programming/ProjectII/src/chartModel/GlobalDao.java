package chartModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class GlobalDao {

	DataSource dataSource;

	public GlobalDao() {
		System.out.println("=[DB]====GlobalDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONArray getGlobal() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		JSONArray lists = new JSONArray();

		try {
			con = dataSource.getConnection();
			String query = "SELECT * " + "FROM globalCrime "
					+ "WHERE (local_nm = '프랑스' OR local_nm = '독일' OR local_nm = '일본' OR local_nm = '대한민국') "
					+ "AND local_nm IS NOT NULL " + "AND year != 2017";

			pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			System.out.println("=[DB]==[callGlobal]==쿼리문 실행완료");
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("name", (rs.getString("LOCAL_NM")));
				obj.put("year", (rs.getInt("YEAR")));
				obj.put("homicide", (rs.getDouble("homicide")));
				obj.put("robber", (rs.getDouble("robber")));
				obj.put("sexual", (rs.getDouble("sexual")));
				obj.put("theft", (rs.getDouble("theft")));
				obj.put("violence", (rs.getDouble("violence")));

				lists.put(obj);
			}
			rs.close();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		return lists;
	}

}
