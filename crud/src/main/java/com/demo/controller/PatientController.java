package com.demo.controller;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.PatientModel;
import com.demo.service.PatientService;
import com.demo.service.PatientServiceImpl.enumEdoc;

@RestController
public class PatientController {

	@Autowired
	PatientService patientService;

//	@Autowired(required = true)
//	ThreadPoolTaskScheduler taskscheduler;

	@Autowired(required = true)
	ThreadPoolTaskScheduler taskScheduler;

	ScheduledFuture<?> schedulerFuture;

	@Autowired
	com.demo.FileScheduler oScheduler;

	@PostMapping("/createPatient")
	public ResponseEntity<?> createPatient(@RequestBody PatientModel model) {
		return new ResponseEntity<PatientModel>(patientService.createPatient(model), HttpStatus.OK);

	}

	@GetMapping("/getTime")

	public String getScheduleTime() {
		return patientService.getScheduleTime();
	}

	private Integer CallFileScheduler(Boolean Flag) {

		try {

			if (Flag) {
				if ((taskScheduler == null) || (schedulerFuture == null)) {

					System.out.println("taskscheduler is null::" + (taskScheduler == null ? "true" : "false"));
					System.out.println("scheduledFuture is null::" + (schedulerFuture == null ? "true" : "false"));

				}
				if ((taskScheduler != null && taskScheduler.getActiveCount() >= 1)
						&& (schedulerFuture != null && schedulerFuture.isCancelled())) {

					System.out.println(
							"\"Sevice is stop but pending task completion is in progress please try again later.\"");
					return enumEdoc.Sevicehasbeenstoppedbutpendingtaskcompletionisinprogresspleasetrylater.getValue();
				} else {

					String schedulerTime = patientService.getScheduleTime();

					if (schedulerTime != "") {
						String[] time = schedulerTime.split(":");
						Date startDate = new Date();
						System.out.println("current date::" + startDate);
						startDate.setHours(Integer.parseInt(time[0]));
						startDate.setMinutes(Integer.parseInt(time[1]));
						startDate.setSeconds(0);
						System.out.println("startDate::" + startDate);

						if (new Date().getTime() > startDate.getTime()) {

							startDate.setHours(24 + Integer.parseInt(time[0]));
							startDate.setMinutes(Integer.parseInt(time[1]));
							System.out.println(
									"set time is less than current time hence next run pushed a day::" + startDate);
						}
						long initDelay = startDate.getTime() - new Date().getTime();
						System.out.println("initDelay::" + initDelay);
						System.out.println("Patient Service final start time is::"
								+ new Date(System.currentTimeMillis() + initDelay));
						schedulerFuture = taskScheduler.scheduleAtFixedRate(oScheduler.LaunchScheduler(), 1000);//run every 10 min
						
						//schedulerFuture = taskScheduler.scheduleAtFixedRate(oScheduler.LaunchScheduler(),
								//new Date(System.currentTimeMillis() + initDelay), 86400000);
						
						System.out.println("\"scheduler service has been started");

					} else {

						System.out.println("SchedulerTime is not set");
					}

					return enumEdoc.Edocserviceisstart.getValue();
				}

			} else {

				if (schedulerFuture == null || schedulerFuture.isCancelled()) {

					System.out.println("service is already stopped");
					return enumEdoc.Edocserviceisalreadystop.getValue();
				} else if (taskScheduler.getActiveCount() >= 1) {
					schedulerFuture.cancel(false);

					System.out.println("Scheduler service will be stopped after completion\r\n");
					return enumEdoc.Edocservicewillbestoppedaftercompletion.getValue();
				} else {
					schedulerFuture.cancel(false);

					System.out.println("Scheduler service has been stopped");
					return enumEdoc.EdocserviceIsstop.getValue();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("(\"Exception in FileSchedulerController");
			return null;
		}
	}

	@PostMapping("/StartScheduler/{Flag}")
	private Integer FileScheduler(@PathVariable Boolean Flag) {
		Integer res = null;
		try {
			res = CallFileScheduler(Flag);

		} catch (Exception e) {

			e.toString();
			System.out.println("start");
			return null;

		}
		return res;
	}
//	@GetMapping("/get")
//public List<PatientModel> listPatient() {
//		
//		return patientService.listPatient();
//	}

}