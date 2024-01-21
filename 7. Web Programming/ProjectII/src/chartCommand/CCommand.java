package chartCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

public interface CCommand {
	void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
