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
	
	public void saveDataToDatabase(JSONArray policeStationArray) {
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      try {
	         con = dataSource.getConnection();
	         String query = "INSERT INTO police_stations (id, police_office, district, sub_district, department, tel,  address) VALUES (?, ?, ?, ?, ?, ?, ?)";
	         pstmt = con.prepareStatement(query);
	         for (int i = 0; i < policeStationArray.length(); i++) {
	        	 JSONObject policeStation =  policeStationArray.getJSONObject(i);
	        	 int id = policeStation.getInt("연번");
	        	
	        	 String police_office = policeStation.getString("시도청");
	        	 String district="";
	        	 if("서울강남".equals(policeStation.getString("경찰서"))) {
	        		 district = "강남구";
	        	 } else if("서울송파".equals(policeStation.getString("경찰서"))) {
	        		 district = "송파구";
	        	 } else if("서울영등포".equals(policeStation.getString("경찰서"))) {
	        		 district = "영등포구";
	        	 } else if("서울성동".equals(policeStation.getString("경찰서"))) {
	        		 district = "성동구";
	        	 } else if("서울노원".equals(policeStation.getString("경찰서"))) {
	        		 district = "노원구";
	        	 } else if("서울강북".equals(policeStation.getString("경찰서"))) {
	        		 district = "강북구";
	        	 } else {
	        		 district = policeStation.getString("경찰서"); 
	        	 }
	        	 
	        	 String sub_district = policeStation.getString("관서명");
	        	 String department = policeStation.getString("구분");
	        	 String tel = policeStation.getString("전화번호");
	        	 String address = policeStation.getString("주소");
	        	 
	            // 파라미터 설정
	        	 pstmt.setInt(1, id);
	        	 pstmt.setString(2, police_office);
	        	 pstmt.setString(3, district);
	        	 pstmt.setString(4, sub_district);
	        	 pstmt.setString(5, department);
	        	 pstmt.setString(6, tel);
	        	 pstmt.setString(7, address);
	        	 
	            // 쿼리 실행
	            pstmt.executeUpdate();
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (con != null) {
	               con.close();
	            }
	            if (pstmt != null) {
	               pstmt.close();
	            }
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	   }
	
}
