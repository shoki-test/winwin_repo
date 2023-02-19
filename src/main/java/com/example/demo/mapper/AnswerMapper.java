package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Answer;

@Mapper
public interface AnswerMapper {

	public List<Answer> selectAll();
	
	public void addAnswer(Answer answer);
}
