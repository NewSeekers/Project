package infoCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import infoModel.PoliceStationDao;

public class PoliceStationApiData {
	 public PoliceStationApiData() {
         PoliceStationDao dao = PoliceStationDao.getInstance();
         try {
              // Open API에서 JSON 데이터 가져오기
            //고혈압
              String policeStationApiUrl = "https://api.odcloud.kr/api/15054711/v1/uddi:9097ad1f-3471-42c6-a390-d85b5121816a?page=1&perPage=243&serviceKey=ZMC3yM4jjMpWPdEfL3Hl2BlzqhQUeN8herjSp2HWSNyV4aUEh7HaJliCLEyGGi2Fn38GRXeQeCiE1WAPXDJljA%3D%3D";
              String policeStationData = fetchDataFromApi(policeStationApiUrl);
         
              // JSON 데이터 파싱
              JSONObject policeStationDataJson = new JSONObject(policeStationData);
              JSONArray policeStationArray = policeStationDataJson.getJSONArray("data");
         
//              System.out.println(policeStationArray);
     
       
              
              
              // 데이터베이스에 저장
              dao.saveDataToDatabase(policeStationArray);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      private String fetchDataFromApi(String apiUrl) throws IOException {
          HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
          connection.setRequestMethod("GET");

          try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
              StringBuilder apiData = new StringBuilder();
              String line;

              while ((line = reader.readLine()) != null) {
                  apiData.append(line);
              }

              return apiData.toString();
          }
      }
}
