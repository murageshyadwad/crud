package com.demo.service;

import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

import com.demo.model.PatientModel;
import com.demo.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository repository;
	
	@Autowired
	EntityManager en;

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}
	
	public enum enumEdoc {
		Edocserviceisstopped(0), Edocserviceisrunning(1), Edocserviceinprogress(2),
		Sevicehasbeenstoppedbutpendingtaskcompletionisinprogresspleasetrylater(3), Edocserviceisstart(4),
		Edocserviceisalreadystop(5), Edocservicewillbestoppedaftercompletion(6), EdocserviceIsstop(7);
		private final Integer value;

		private enumEdoc(Integer value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	@Override
	public PatientModel createPatient(@RequestBody PatientModel model) {
		return repository.save(model);
	}
	
	
	@Override
	public String getScheduleTime() {

		String res = "";
		try {
			
			StoredProcedureQuery query = getEn().createStoredProcedureQuery("get_servicescheduletime");
			
			res = (String) query.getSingleResult();
			
		} catch (Exception e) {

			System.out.println(e.toString());

			// e.printStackTrace();
		} finally {
			getEn().close();
		}
		return res;
	}

	

	
	@Override
	public List<PatientModel> listPatient() {
		
		  List<PatientModel> pt = (List<PatientModel>) repository.findAll();
		  System.out.println(pt);
		System.out.println("Muragesh");
		 return pt;
	}




}
