package com.apna.photographer.completeDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apna.photographer.completeDemo.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	
}
 