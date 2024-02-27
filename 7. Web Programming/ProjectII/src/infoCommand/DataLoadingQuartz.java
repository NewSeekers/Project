package infoCommand;

import java.io.IOException;
import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import infoModel.GlobalCrimeDao;

public class DataLoadingQuartz implements Job{

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			new GlobalCrimeDao().setGlobalCrime();
			new PoliceStationApiData();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
