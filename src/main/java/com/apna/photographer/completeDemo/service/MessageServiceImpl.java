package com.apna.photographer.completeDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apna.photographer.completeDemo.dao.MessageRepository;
import com.apna.photographer.completeDemo.entity.Message;

@Service
public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepository;

	@Autowired
	public MessageServiceImpl(MessageRepository themessageRepository) {
		this.messageRepository = themessageRepository;
	}

	@Transactional
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Transactional
	public Message findById(int theId) {

		Optional<Message> result = messageRepository.findById(theId);

		Message theMessage = null;

		if (result.isPresent()) {
			theMessage = result.get();
		} else {
			// we did not find the Message
			throw new RuntimeException("Did not find Message Id - " + theId);
		}
		return theMessage;
	}

	
	@Transactional
	public void save(Message theMessage) {
		messageRepository.save(theMessage);

	}

	@Transactional
	public void deleteById(int theId) {
		messageRepository.deleteById(theId);
	}

}
