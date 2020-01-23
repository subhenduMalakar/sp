package com.apna.photographer.completeDemo.service;

import java.util.List;

import com.apna.photographer.completeDemo.entity.Emails;

public interface EmailsService {

	public List<Emails> findAll();

	public Emails findById(int theId);

	public void save(Emails theEmails);

	public void deleteById(int theId);

}
