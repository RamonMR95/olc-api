package com.ramonmr95.tiky.olc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ramonmr95.tiky.olc.entities.Topic;

public interface ITopicDao extends CrudRepository<Topic, Long> {

}
