package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

public interface CCommandGuPage {
	JSONArray execute(HttpServletRequest request, HttpServletResponse response);
}
