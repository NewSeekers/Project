package infoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;


public class PoliceStationDao {
	public static PoliceStationDao instance;
	DataSource dataSource;
	
	public PoliceStationDao() {
		try {
			System.out.println("policeDao 들어옴");
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println("policeDao 실패::::::::");
			e.printStackTrace();
		}
	}
	public static PoliceStationDao getInstance() {
		if(instance == null) {
			instance = new PoliceStationDao();
		}
		return instance;
	}
	public JSONArray getPoliceStations(String guNameValue) {
		System.out.println("getPoliceStations()메소드 들어옴");
		JSONArray policeStations = new JSONArray();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=dataSource.getConnection();
			System.out.println("getPoliceStations()의 try");
			String query = "select * FROM police_stations join gu_name on police_stations.district = gu_name.guname where gu_name.guname=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, guNameValue);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject policeStation = new JSONObject();
				policeStation.put("id",rs.getInt("id"));
				policeStation.put("police_office",rs.getString("police_office"));
				policeStation.put("district",rs.getString("district"));
				policeStation.put("sub_district", rs.getString("sub_district"));
				policeStation.put("department",rs.getString("department"));
				policeStation.put("tel",rs.getString("tel"));
				policeStation.put("address",rs.getString("address"));
				policeStations.put(policeStation);
			}
			}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
		      if (rs != null) rs.close();
			  if (pstmt != null) pstmt.close();
			  if (con != null) con.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return policeStations;
	}
	
}
