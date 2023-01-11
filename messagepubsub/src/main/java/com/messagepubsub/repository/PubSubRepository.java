package com.messagepubsub.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messagepubsub.model.PubSubModel;

@Repository
public interface PubSubRepository extends JpaRepository<PubSubModel, Long>{
//	List<PubSubModel> FindAll();
}
