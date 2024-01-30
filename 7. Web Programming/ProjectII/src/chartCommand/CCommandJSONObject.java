package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public interface CCommandJSONObject {
	JSONObject execute(HttpServletRequest request, HttpServletResponse response);
}
