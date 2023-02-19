package com.example.demo.entity;
import lombok.Data;

@Data
public class Post {
	
	private Integer pid;
	private Integer uid;
	private Integer category;
	private String title;
	private String content;
	private int settled_flg;
	private java.sql.Timestamp post_date;
}
