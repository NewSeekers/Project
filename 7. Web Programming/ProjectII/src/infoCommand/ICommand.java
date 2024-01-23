package infoCommand;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
}
