package chartModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class PredictDao {

	DataSource dataSource;

	public PredictDao() {
		System.out.println("==[DB]==PredictDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject getPredData(String region) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		JSONArray jsonArrFacil = new JSONArray();
		JSONArray jsonArrCrime = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		JSONObject jsonFacilData = new JSONObject();
		JSONObject jsonCrimeData = new JSONObject();

		ArrayList<Integer> pub = new ArrayList<>();
		ArrayList<Integer> grdp = new ArrayList<>();
		ArrayList<Integer> single = new ArrayList<>();
		ArrayList<Integer> year = new ArrayList<>();
		ArrayList<Integer> population = new ArrayList<>();
		ArrayList<Double> area = new ArrayList<>();
		ArrayList<Integer> popDensity = new ArrayList<>();
		ArrayList<Integer> lights = new ArrayList<>();
		ArrayList<Integer> cctv = new ArrayList<>();
		ArrayList<Integer> policeStation = new ArrayList<>();
		ArrayList<Integer> policeman = new ArrayList<>();
		ArrayList<Integer> homicide = new ArrayList<>();
		ArrayList<Integer> robber = new ArrayList<>();
		ArrayList<Integer> sexual = new ArrayList<>();
		ArrayList<Integer> theft = new ArrayList<>();
		ArrayList<Integer> violence = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String query = "SELECT year, population, area, pop_density, pub, grdp, single " + "FROM gu_env, gu_name "
					+ "WHERE gu_env.year IN (2013,2014,2015,2016,2017,2018,2019,2020,2021,2022) "
					+ "AND gu_env.local = gu_name.local AND gu_name.guname = ? " + "ORDER BY gu_env.year ASC";
			System.out.println("첫번째쿼리" + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, region);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("===[DB:getEnvNum]===쿼리문 실행완료" + query);
			while (rs.next()) {
				pub.add(rs.getInt("pub"));
				grdp.add(rs.getInt("grdp"));
				single.add(rs.getInt("single"));
				year.add(rs.getInt("year"));
				population.add(rs.getInt("population"));
				area.add(rs.getDouble("area"));
				popDensity.add(rs.getInt("pop_density"));
			}
			String query2 = "SELECT year, lights, cctv, policestation, policeman " + "FROM gu_secufacil, gu_name "
					+ "WHERE gu_secufacil.year IN (2013,2014,2015,2016,2017,2018,2019,2020,2021,2022) "
					+ "AND gu_secufacil.local = gu_name.local AND gu_name.guname = ? "
					+ "ORDER BY gu_secufacil.year ASC";
			System.out.println("두번째쿼리" + query2);
			pstmt2 = con.prepareStatement(query2);
			pstmt2.setString(1, region);
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println("===[DB:getSecuFacil]===쿼리문실행완료" + query2);
			while (rs2.next()) {
				lights.add(rs2.getInt("lights"));
				cctv.add(rs2.getInt("cctv"));
				policeStation.add(rs2.getInt("policestation"));
				policeman.add(rs2.getInt("policeman"));
			}

			String query3 = "SELECT year, homicide, robber, sexual, theft, violence " + "FROM gu_crime, gu_name "
					+ "WHERE gu_crime.year IN (2013,2014,2015,2016,2017,2018,2019,2020,2021,2022) "
					+ "AND gu_crime.local = gu_name.local AND gu_name.guname = ? " + "ORDER BY gu_crime.year ASC";
			System.out.println("세번째쿼리" + query3);
			pstmt3 = con.prepareStatement(query3);
			pstmt3.setString(1, region);
			ResultSet rs3 = pstmt3.executeQuery();
			System.out.println("===[DB:getGuCrime]===쿼리문실행완료" + query3);
			while (rs3.next()) {
				homicide.add(rs3.getInt("homicide"));
				robber.add(rs3.getInt("robber"));
				sexual.add(rs3.getInt("sexual"));
				theft.add(rs3.getInt("theft"));
				violence.add(rs3.getInt("violence"));
			}

			String query4 = "select y2022 from gu_secugrade, gu_name where gu_secugrade.local = gu_name.local and gu_name.guname = ? ";
			System.out.println("네번째쿼리" + query4);
			pstmt4 = con.prepareStatement(query4);
			pstmt4.setString(1, region);
			ResultSet rs4 = pstmt4.executeQuery();
			System.out.println("===[DB:getGuSecuGrade]===쿼리문실행완료" + query4);
			while (rs4.next()) {
				jsonObj.put("grade", rs4.getInt("y2022"));
			}

			switch (region) {
			case "강남구":
				jsonFacilData.put("single", single);
				jsonFacilData.put("pub", pub);
				jsonFacilData.put("policeman", policeman);
				jsonFacilData.put("cctv", cctv);
				break;
			case "송파구":
				jsonFacilData.put("policeStation", policeStation);
				jsonFacilData.put("pub", pub);
				jsonFacilData.put("cctv", cctv);
				jsonFacilData.put("policeman", policeman);
				jsonFacilData.put("lights", lights);
				break;
			case "영등포구":
				jsonFacilData.put("lights", lights);
				jsonFacilData.put("pub", pub);
				jsonFacilData.put("cctv", cctv);
				jsonFacilData.put("policeStation", policeStation);
				jsonFacilData.put("policeman", policeman);
				break;
			case "성동구":
				jsonFacilData.put("pub", pub);
				jsonFacilData.put("cctv", cctv);
				jsonFacilData.put("single", single);
				jsonFacilData.put("lights", lights);
				jsonFacilData.put("policeman", policeman);
				break;
			case "노원구":
				jsonFacilData.put("policeStation", policeStation);
				jsonFacilData.put("cctv", cctv);
				jsonFacilData.put("single", single);
				jsonFacilData.put("lights", lights);
				jsonFacilData.put("policeman", policeman);
				break;
			case "강북구":
				jsonFacilData.put("lights", lights);
				jsonFacilData.put("pub", pub);
				jsonFacilData.put("cctv", cctv);
				jsonFacilData.put("single", single);
				jsonFacilData.put("policeStation", policeStation);
				break;
			}

			jsonCrimeData.put("homicide", homicide);
			jsonCrimeData.put("robber", robber);
			jsonCrimeData.put("sexual", sexual);
			jsonCrimeData.put("theft", theft);
			jsonCrimeData.put("violence", violence);

			jsonArrFacil.put(jsonFacilData);
			jsonArrCrime.put(jsonCrimeData);

			jsonObj.put("facilitySelector", jsonArrFacil);
			jsonObj.put("crimeData", jsonArrCrime);
			jsonObj.put("population", population);

			rs.close();
			rs2.close();
			rs3.close();
			rs4.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			pstmt2.close();
			pstmt3.close();
			pstmt4.close();
			con.close();
		}
		return jsonObj;
	}

}
