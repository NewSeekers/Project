package infoCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import infoModel.GlobalCrimeDto;

public class ReqGlobalCrime {

	public ArrayList<GlobalCrimeDto> getGlobalCrime() throws IOException {

    	String homiDataTable = "T189403025013347";
		String violenceDataTable = "T185963025029492";
		String sexualDataTable = "T185573025062134";
		String robberDataTable = "T187993025051517";
		String theftDataTable = "T189473025047133";
		String[] crimeArr = { homiDataTable, violenceDataTable, sexualDataTable, robberDataTable, theftDataTable };
		int[] yearArr = { 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017};
		ArrayList<GlobalCrimeDto> gcdArr = new ArrayList<>();

			for (int j = 0; j < yearArr.length; j++) {
				for (int k = 0; k < crimeArr.length; k++) {
				JSONObject obj = getDataFromAPI(yearArr[j], crimeArr[k]);
				JSONArray arr2 = obj.getJSONArray("Sttsapitbldata").getJSONObject(1).getJSONArray("row");
				for (int i = 0; i < arr2.length(); i++) {
					GlobalCrimeDto gcd = new GlobalCrimeDto();
					JSONObject arr = arr2.getJSONObject(i);
					int yearVal = (arr.isNull("WRTTIME_IDTFR_ID")) ? 0 : arr.getInt("WRTTIME_IDTFR_ID");
					gcd.setYear(yearVal);
					String nameVal = (arr.isNull("ITM_NM")) ? "" : arr.getString("ITM_NM");
					gcd.setCountry(nameVal);
					String crimeKindVal = (arr.isNull("STATBL_ID")) ? "" : arr.getString("STATBL_ID");
					gcd.setCrimeCode(crimeKindVal);
					double dtaVal = (arr.isNull("DTA_VAL")) ? 0.0 : arr.getDouble("DTA_VAL");
					gcd.setCount(dtaVal);
					gcdArr.add(gcd);
				}
			}
		}
		return gcdArr;
	}

	
	
	public JSONObject getDataFromAPI(int year, String crimeTable) throws IOException {
		String urlOrigin = "https://www.kicj.re.kr/crimestats/openapi/Sttsapitbldata.do";
		StringBuilder urlBuilder = new StringBuilder(urlOrigin);
		urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("593096a998f840f889a9dff387e6a4f8", "UTF-8"));/* "" */
		urlBuilder.append("&" + URLEncoder.encode("DTACYCLE_CD", "UTF-8") + "=" + URLEncoder.encode("YY", "UTF-8"));/* "" */
		urlBuilder.append("&" + URLEncoder.encode("STATBL_ID", "UTF-8") + "=" + crimeTable);
		urlBuilder.append("&" + URLEncoder.encode("WRTTIME_IDTFR_ID", "UTF-8") + "=" + year);
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Global reqCrimeData Response code :" + conn.getResponseCode());
		JSONObject obj = null;
		BufferedReader br;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			obj = new JSONObject(sb.toString());
		}
		return obj;
	}

}
