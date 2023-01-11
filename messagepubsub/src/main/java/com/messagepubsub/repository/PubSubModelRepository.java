package com.messagepubsub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messagepubsub.model.PubSubModel1;

@Repository
public interface PubSubModelRepository extends JpaRepository<PubSubModel1, Long> {

}
