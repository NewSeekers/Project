package infoModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import infoCommand.ReqGlobalCrime;

public class GlobalCrimeDao {
	DataSource dataSource;

	public GlobalCrimeDao() {
		System.out.println("=[DB]====GlobalCrimeDao 실행");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void setGlobalCrime() throws SQLException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		con = dataSource.getConnection();
		System.out.println("DB테이블 존재하는지 확인 시작==================");
		String tableName = "globalCrime";
		DatabaseMetaData metaData = con.getMetaData();
		ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), null);

		if (resultSet.next()) {
			System.out.println("DB테이블 존재, 초기화 시작==================");
			String checkDataQuery = "DELETE FROM GLOBALCRIME";
			pstmt3 = con.prepareStatement(checkDataQuery);
			pstmt3.executeUpdate();
			pstmt3.close();
		} else {
			System.out.println("DB테이블 생성 시작==================");
			String mkTableQuery = "CREATE TABLE GLOBALCRIME (" + "\"LOCAL_NM\" VARCHAR2(50 BYTE) NOT NULL ENABLE, "
					+ "\"YEAR\" NUMBER NOT NULL ENABLE, " + "\"HOMICIDE\" NUMBER(10,2), "
					+ "\"VIOLENCE\" NUMBER(10,2), " + "\"SEXUAL\" NUMBER(10,2), " + "\"ROBBER\" NUMBER(10,2), "
					+ "\"THEFT\" NUMBER(10,2), " + "CONSTRAINT \"GLOBALCRIME_PK\" PRIMARY KEY (\"LOCAL_NM\", \"YEAR\") "
					+ ")";
			pstmt2 = con.prepareStatement(mkTableQuery);
			pstmt2.executeUpdate();
			pstmt2.close();
		}

		String homiDataTable = "T189403025013347";
		String violenceDataTable = "T185963025029492";
		String sexualDataTable = "T185573025062134";
		String robberDataTable = "T187993025051517";
		String theftDataTable = "T189473025047133";
		ReqGlobalCrime rgc = new ReqGlobalCrime();
		ArrayList<GlobalCrimeDto> gcdArr = rgc.getGlobalCrime();
		for (GlobalCrimeDto gcd : gcdArr) {

			if (gcd.getCrimeCode().equals(homiDataTable)) {

				String homiquery = "MERGE INTO globalcrime target "
						+ "USING (SELECT ? AS localName, ? AS year, ? AS newData from dual) source "
						+ "ON (target.local_NM = source.localName AND target.year = source.year) "
						+ "WHEN MATCHED THEN " + "  UPDATE SET target.homicide = source.newData "
						+ "WHEN NOT MATCHED THEN " + "  INSERT (local_NM, year, homicide) VALUES (?, ?, ?)";

				pstmt = con.prepareStatement(homiquery);
				pstmt.setString(1, gcd.getCountry());
				pstmt.setInt(2, gcd.getYear());
				pstmt.setDouble(3, gcd.getCount());
				pstmt.setString(4, gcd.getCountry());
				pstmt.setInt(5, gcd.getYear());
				pstmt.setDouble(6, gcd.getCount());
				pstmt.executeUpdate();
			} else if (gcd.getCrimeCode().equals(robberDataTable)) {
				String homiquery = "MERGE INTO globalcrime target "
						+ "USING (SELECT ? AS localName, ? AS year, ? AS newData from dual) source "
						+ "ON (target.local_NM = source.localName AND target.year = source.year) "
						+ "WHEN MATCHED THEN " + "  UPDATE SET target.robber = source.newData "
						+ "WHEN NOT MATCHED THEN " + "  INSERT (local_NM, year, robber) VALUES (?, ?, ?)";

				pstmt = con.prepareStatement(homiquery);
				pstmt.setString(1, gcd.getCountry());
				pstmt.setInt(2, gcd.getYear());
				pstmt.setDouble(3, gcd.getCount());
				pstmt.setString(4, gcd.getCountry());
				pstmt.setInt(5, gcd.getYear());
				pstmt.setDouble(6, gcd.getCount());
				pstmt.executeUpdate();
			} else if (gcd.getCrimeCode().equals(sexualDataTable)) {
				String homiquery = "MERGE INTO globalcrime target "
						+ "USING (SELECT ? AS localName, ? AS year, ? AS newData from dual) source "
						+ "ON (target.local_NM = source.localName AND target.year = source.year) "
						+ "WHEN MATCHED THEN " + "  UPDATE SET target.sexual = source.newData "
						+ "WHEN NOT MATCHED THEN " + "  INSERT (local_NM, year, sexual) VALUES (?, ?, ?)";

				pstmt = con.prepareStatement(homiquery);
				pstmt.setString(1, gcd.getCountry());
				pstmt.setInt(2, gcd.getYear());
				pstmt.setDouble(3, gcd.getCount());
				pstmt.setString(4, gcd.getCountry());
				pstmt.setInt(5, gcd.getYear());
				pstmt.setDouble(6, gcd.getCount());
				pstmt.executeUpdate();
			} else if (gcd.getCrimeCode().equals(theftDataTable)) {
				String homiquery = "MERGE INTO globalcrime target "
						+ "USING (SELECT ? AS localName, ? AS year, ? AS newData from dual) source "
						+ "ON (target.local_NM = source.localName AND target.year = source.year) "
						+ "WHEN MATCHED THEN " + "  UPDATE SET target.theft = source.newData "
						+ "WHEN NOT MATCHED THEN " + "  INSERT (local_NM, year, theft) VALUES (?, ?, ?)";

				pstmt = con.prepareStatement(homiquery);
				pstmt.setString(1, gcd.getCountry());
				pstmt.setInt(2, gcd.getYear());
				pstmt.setDouble(3, gcd.getCount());
				pstmt.setString(4, gcd.getCountry());
				pstmt.setInt(5, gcd.getYear());
				pstmt.setDouble(6, gcd.getCount());
				pstmt.executeUpdate();
			} else if (gcd.getCrimeCode().equals(violenceDataTable)) {
				String homiquery = "MERGE INTO globalcrime target "
						+ "USING (SELECT ? AS localName, ? AS year, ? AS newData from dual) source "
						+ "ON (target.local_NM = source.localName AND target.year = source.year) "
						+ "WHEN MATCHED THEN " + "  UPDATE SET target.violence = source.newData "
						+ "WHEN NOT MATCHED THEN " + "  INSERT (local_NM, year, violence) VALUES (?, ?, ?)";

				pstmt = con.prepareStatement(homiquery);
				pstmt.setString(1, gcd.getCountry());
				pstmt.setInt(2, gcd.getYear());
				pstmt.setDouble(3, gcd.getCount());
				pstmt.setString(4, gcd.getCountry());
				pstmt.setInt(5, gcd.getYear());
				pstmt.setDouble(6, gcd.getCount());
				pstmt.executeUpdate();
			}
		}
		resultSet.close();
		pstmt.close();
		con.close();
	}
}
