package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Answer;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;

@Controller
public class MainController {
	
	private int logined_userid;
	private String logined_username; 
	private String logined_password; 
	
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    PostMapper postMapper;
    
    @Autowired
    AnswerMapper answerMapper;
    
    @RequestMapping(value="/")
    public String index(Model model) {
        List<User> list = userMapper.selectAll();
        model.addAttribute("users",list);
        return "index";
    }
    
    @RequestMapping(value="/signup")
    public String signup(User user) {
        userMapper.Signup_User(user);
        return "redirect:/";
    }
    
    @RequestMapping(value="/signin")
    public String signin(@RequestParam(required = false) Integer uid, @RequestParam(required = false) String name,@RequestParam(required = false) String password, Model model,User user) {
    	List<User> logined_user = userMapper.Signin_User(name,password);
    	List<Post> Post_list = postMapper.selectAll(logined_user.get(0).getUid());
        model.addAttribute("user",logined_user);
        
        try {
        	logined_username = logined_user.get(0).getName();
            logined_password = logined_user.get(0).getPassword();
            logined_userid = logined_user.get(0).getUid();
        }catch(IndexOutOfBoundsException e) {
        	System.out.println("ログイン済です。");
        	model.addAttribute("uid",logined_userid);
        	model.addAttribute("user_name",logined_username);
        	model.addAttribute("user_password",logined_password);
            model.addAttribute("post_list",Post_list);
        	return "top";
        }
        model.addAttribute("uid",logined_userid);
    	model.addAttribute("user_name",logined_username);
    	model.addAttribute("user_password",logined_password);
        model.addAttribute("post_list",Post_list);
        return "top";
    }
    
    @RequestMapping(value="/addPost")
    public String AddPost(Post post, @RequestParam(required = false)  Integer uid ,Model model, RedirectAttributes redirectAttributes) {
    	post.setUid(uid);
    	postMapper.addPost(post);
    	model.addAttribute("user_name",logined_username);
    	redirectAttributes.addAttribute("uid",logined_userid);
    	redirectAttributes.addAttribute("name",logined_username);
    	redirectAttributes.addAttribute("password",logined_password);
    	return "redirect:/signin";
    }
 
    @RequestMapping(value="/create_post")
    public String Create_Post(Post post, @RequestParam int uid,Model model, RedirectAttributes redirectAttributes) {
    	post.setUid(uid);
    	postMapper.addPost(post);
    	model.addAttribute("user_name",logined_username);
    	redirectAttributes.addAttribute("name",logined_username);
    	redirectAttributes.addAttribute("password",logined_password);
    	return "redirect:/signin";
    }
    
    @RequestMapping(value="post_list")
    public String post_list(Model model) {
    	List<Post> AllPost = postMapper.selectAllPost();
    	model.addAttribute("uid",logined_userid);
    	model.addAttribute("password",logined_password);
    	model.addAttribute("AllPost_list",AllPost);
    	return "post_list";
    }
    
    @RequestMapping(value="post_answer")
    public String post_answer(Model model,Post post,@RequestParam(required = false) int uid,@RequestParam String title,@RequestParam String content,@RequestParam int pid) {
    	Answer addanswer = new Answer();
    	addanswer.setUid((Integer)uid);
    	addanswer.setPid(pid);
    	addanswer.setTitle(title);
    	addanswer.setContent(content);
    	postMapper.settled_post(post);
    	answerMapper.addAnswer(addanswer);
    	model.addAttribute("uid",logined_username);
    	return "post_answer";
    }
    
    @RequestMapping(value="post_del")
    public String post_del(Model model,Post post,@RequestParam Integer uid,@RequestParam String title,@RequestParam String content,@RequestParam int pid) {
    	postMapper.DelPost(post);
    	List<Post> AllPost = postMapper.selectAllPost();
    	model.addAttribute("uid",logined_userid);
    	model.addAttribute("password",logined_password);
    	model.addAttribute("AllPost_list",AllPost);
    	return "post_list";
    }
    
    @RequestMapping(value="top")
    public String top(Model model,@RequestParam int uid,@RequestParam String password) {
    	List<Post> logineduser_post = postMapper.selectAll(uid);
    	model.addAttribute("post_list",logineduser_post);
    	model.addAttribute("user_name",logined_username);
    	return "top";
    }
    
}