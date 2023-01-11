package com.demo.service;


import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import com.demo.model.PatientModel;


@Service
public interface PatientService {
	
	public PatientModel createPatient(PatientModel model);
	public List<PatientModel> listPatient();
	public String getScheduleTime();
	
}
