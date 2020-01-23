package com.apna.photographer.completeDemo.service;

import java.util.List;

import com.apna.photographer.completeDemo.entity.Message;

public interface MessageService {

	public List<Message> findAll();
	
	public Message findById(int theId);
	
	public void save(Message theMessage);
	
	public void deleteById(int theId);
	
	
	
}
