package com.demo;

import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.service.PatientService;




@Component
public class FileScheduler {

	@Autowired
    PatientService patientService;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

	public Runnable LaunchScheduler() {

		System.out.print("In LaunchScheduler:  Start Scheduler to download File scheduler started at :"
				+ dateFormat.format(new Date()) + " ");

		return () -> patientService.listPatient();
			
		

	}
}
