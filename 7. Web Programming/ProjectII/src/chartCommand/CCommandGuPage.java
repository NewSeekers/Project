package chartCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CCommandGuPage {
	JSONArray execute(HttpServletRequest request, HttpServletResponse response);
}
