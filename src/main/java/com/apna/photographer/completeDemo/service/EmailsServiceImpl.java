package com.apna.photographer.completeDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apna.photographer.completeDemo.dao.EmailsRepository;
import com.apna.photographer.completeDemo.entity.Emails;

@Service
public class EmailsServiceImpl implements EmailsService {

	private EmailsRepository emailsRepository;

	@Autowired
	public EmailsServiceImpl(EmailsRepository theEmailsRepository) {
		this.emailsRepository = theEmailsRepository;
	}

	@Transactional
	public List<Emails> findAll() {
		return emailsRepository.findAll();
	}

	@Transactional
	public Emails findById(int theId) {

		Optional<Emails> result = emailsRepository.findById(theId);

		Emails theEmails = null;

		if (result.isPresent()) {
			theEmails = result.get();
		} else {
			// we did not find the employee
			throw new RuntimeException("Did not find Emails Id - " + theId);
		}
		return theEmails;
	}

	
	@Transactional
	public void save(Emails theEmails) {
		emailsRepository.save(theEmails);

	}

	@Transactional
	public void deleteById(int theId) {
		emailsRepository.deleteById(theId);
	}

}
