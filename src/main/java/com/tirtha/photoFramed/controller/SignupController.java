package com.tirtha.photoFramed.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tirtha.photoFramed.model.UserModel;
import com.tirtha.photoFramed.service.UserService;

@Controller
public class SignupController {
	
	public static final Logger log = Logger.getLogger(SignupController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	method to display user sign up page
	@RequestMapping("/signup")
	public String userSignup(HttpSession session, @ModelAttribute("user") UserModel userModel) {	
		log.trace("Inside Signup page!");
		return "signup";
	}
	
//	method to display admin signup page
//	@RequestMapping("/adminSignup")
//	public String adminSignup(@ModelAttribute("user") UserModel userModel) {		
//		log.trace("Inside Admin Signup page!");
//		return "adminSignup";
//	}

//	method for user account creation
	@PostMapping(path = "/createNewUserAccount")
	public String createUser(HttpSession session, @ModelAttribute("user") UserModel userModel) {
		
		session.removeAttribute("loginMsg");
		log.trace("inside create user in user controller");

//		if (errors.hasErrors()) {
//			System.out.println("Errors are there");
//			return "signup";
//		} 
//		else {
//			System.out.println("No errors");
//			String result = userService.validateUserSignup(userModel);
//			return "userHome";
//		}
		
		if(userModel.getUsername() == "" || userModel.getEmail() == "" || userModel.getPassword() == "") {
			session.setAttribute("signupMsg", "Invalid credentials! Try again ");
			return "redirect:/signup";
		}
		
		if(userModel.getUsername().contains("admin")) {
			userModel.setRole("ROLE_ADMIN");
		}
		else {
			userModel.setRole("ROLE_USER");			
		}
		
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		
		boolean result = userService.validateUserSignup(userModel);
		if(result) {
			session.setAttribute("name", userModel.getUsername());
			return "redirect:/login";			
		}
		else {
			session.setAttribute("signupMsg", "Username or email id already exists! ");
			return "redirect:/signup";
		}
	}
	
//	method for admin account creation
//	@PostMapping(path = "/createNewAdminAccount")
//	public String createAdmin(HttpSession session, @ModelAttribute("user") UserModel userModel) {
//		session.removeAttribute("loginMsg");
//		log.trace("inside create user in user controller");
////		if (errors.hasErrors()) {
////			System.out.println("Errors are there");
////			return "signup";
////		} 
////		else {
////			System.out.println("No errors");
////			String result = userService.validateUserSignup(userModel);
////			return "userHome";
////		}
//		userModel.setRole("ROLE_ADMIN");
//		
//		boolean result = userService.validateUserSignup(userModel);
//		if(result) {
//			session.setAttribute("name", userModel.getUsername());
//			return "redirect:/adminLogin";			
//		}
//		else {
//			session.setAttribute("signupMsg", "Username or email id already exists! ");
//			return "redirect:/adminSignup";
//		}
//	}

}
