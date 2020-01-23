package com.apna.photographer.completeDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "pname")
	private String pname;

	@Column(name = "price")
	private int price;

	@Column(name = "link")
	private String link;

	@Column(name = "fimage")
	private String fimage;
	
	@Column(name = "simage")
	private String simage;

	@Column(name = "videos")
	private int videos;

	@Column(name = "clicks")
	private int clicks;

	@Column(name = "des")
	private String des;

	public Product() {

	}

	

	public Product(int id, String pname, int price, String link, String fimage, String simage, int videos, int clicks,
			String des) {
		
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.link = link;
		this.fimage = fimage;
		this.simage = simage;
		this.videos = videos;
		this.clicks = clicks;
		this.des = des;
	}



	public Product(String pname, int price, String link, String fimage, String simage, int videos, int clicks,
			String des) {
	
		this.pname = pname;
		this.price = price;
		this.link = link;
		this.fimage = fimage;
		this.simage = simage;
		this.videos = videos;
		this.clicks = clicks;
		this.des = des;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	

	public String getFimage() {
		return fimage;
	}



	public void setFimage(String fimage) {
		this.fimage = fimage;
	}



	public String getSimage() {
		return simage;
	}



	public void setSimage(String simage) {
		this.simage = simage;
	}



	public int getVideos() {
		return videos;
	}

	public void setVideos(int videos) {
		this.videos = videos;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", pname=" + pname + ", price=" + price + ", link=" + link + ", fimage=" + fimage
				+ ", simage=" + simage + ", videos=" + videos + ", clicks=" + clicks + ", des=" + des + "]";
	}

	

}
