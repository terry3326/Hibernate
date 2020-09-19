package com.onetomany.bean;

import java.sql.Blob;
import java.util.Date;

public class News {
	
	private Integer id; //field
	private String title;
	private String author;
	
	private String desc;
	
	//使用 title + "," + content 可以来描述当前的 News 记录. 
	//即 title + "," + content 可以作为 News 的 desc 属性值
	
	private String content;
	
	private Blob picture;

	public News(Integer id, String title, String author, String desc, String content, Blob picture, Date date) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.desc = desc;
		this.content = content;
		this.picture = picture;
		this.date = date;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}



	private Date date;

	public Integer getId() { //property
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public News(String title, String author, Date date) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
	}
	
	public News() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author
				+ ", date=" + date + "]";
	}
	
	
	
}
