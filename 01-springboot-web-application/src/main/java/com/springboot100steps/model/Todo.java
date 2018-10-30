package com.springboot100steps.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Todo {

	private int id;
	private String name;
	@Size(min=3, message="Please Enter min 3 char.")
	private String desc;
	private Date targetDate;
	private boolean done;
	
	public Todo() {
		super();
	}

	public Todo(int id, String name, String desc, Date targetDate, boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", desc=" + desc + ", targetDate=" + targetDate + ", isDone="
				+ done + "]";
	}
	
}
