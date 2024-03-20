package com.spring.newseekers;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.spring.newseekers.borough.service.PoliceStationAPIData;
import com.spring.newseekers.seoul.service.SeoulService;

public class DataLoadingQuartz implements Job{

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {

		
		try {
			new PoliceStationAPIData();
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
