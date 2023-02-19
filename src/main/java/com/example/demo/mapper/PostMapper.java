package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Post;

@Mapper
public interface PostMapper {

	 public List<Post> selectAll(int uid);
	 
	 public List<Post> selectAllPost ();
	 
	 public void addPost(Post post);
	 
	 public void settled_post(Post post);
	 
	 public void DelPost(Post post);
}
