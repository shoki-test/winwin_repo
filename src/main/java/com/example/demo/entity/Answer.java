package com.example.demo.entity;
import lombok.Data;

@Data
public class Answer {

	private int uid;
	private int pid;
	private String title;
	private String content;
	private java.sql.Timestamp answer_date;
}

