package com.tirtha.photoFramed.controller;


import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tirtha.photoFramed.model.CommentModel;
import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.UserModel;
import com.tirtha.photoFramed.service.CommentService;
import com.tirtha.photoFramed.service.ImageService;
import com.tirtha.photoFramed.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	ImageService imageService;

	@Autowired
	CommentService commentService;
	
	public static final Logger log = Logger.getLogger(UserController.class);
	
//	method to display the error page in case any error occurs
	@RequestMapping("/errorPage")
	public String goToErrorPage() {
		return "errorPage";
	}
	
//	method to display the user home page
	@RequestMapping("/userHome")
	public ModelAndView userHomepage(ModelAndView model, Principal principal, HttpSession session) {
		
		List<UserModel> userModelArray = userService.getAllUsers();
		List<ImageModel> imageModelArray = imageService.getAllImagePosts();
		List<CommentModel> commentModelArray = commentService.getCommentModels();
		
		Collections.reverse(imageModelArray);
		
		model.addObject("userModelList", userModelArray);
		model.addObject("imageModelList", imageModelArray);
		model.addObject("commentModelList", commentModelArray);
		model.setViewName("userHome");	
		
		session.setAttribute("name", principal.getName() );
		
		return model;
	}

//	method to display the user profile page
	@GetMapping(path = "/profile")
	public ModelAndView userProfile(HttpSession session,  Principal principal, ModelAndView model) {
		
		session.setAttribute("name", principal.getName() );
		
		try {
			String username = (String) session.getAttribute("name");						
			UserModel userModel = userService.getUserModelByUsername(username);
			
			List<ImageModel> imageModelArray = imageService.getImageModelByUserId(userModel.getUserId());
			List<CommentModel> commentModelArray = commentService.getCommentModels();
			
			model.addObject("userModelDetails", userModel);
			model.addObject("imageModelList", imageModelArray);
			model.addObject("commentModelList", commentModelArray);
			model.setViewName("profile");
						
			return model;
		} catch (Exception e) {
			log.debug(e);
			model.setViewName("userHome");
			return model;
		}
	}

//	@RequestMapping(path="user/profile/{uname}")
//	public ModelAndView empProfile(@PathVariable("uname") String userName, ModelAndView model) {
//		for(LoginModel userModel : userModelList) {
//			if(userModel.getUsername().equals(userName)) {
//				model.addObject("userDetails", userModel);
//				model.setViewName("userHome");
//			}
//		}
//		return model;
//	}

}
