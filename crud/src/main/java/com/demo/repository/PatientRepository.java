package com.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.PatientModel;
@Repository
public interface PatientRepository extends PagingAndSortingRepository<PatientModel, Long> {

	

	PatientModel findByid(long id);

	//boolean existsById(String id);
	

}
