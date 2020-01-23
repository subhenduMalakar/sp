package com.apna.photographer.completeDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
	
	//define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@Column(name="message")
	private String mess;
	
	//define constructors
	
	public Message() {
		
	}

	public Message(int id, String username, String email, String mess) {
		
		this.id = id;
		this.username = username;
		this.email = email;
		this.mess = mess;
	}

	public Message(String username, String email, String mess) {
		
		this.username = username;
		this.email = email;
		this.mess = mess;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", username=" + username + ", email=" + email + ", message=" + mess + "]";
	}

	
	
	
}
