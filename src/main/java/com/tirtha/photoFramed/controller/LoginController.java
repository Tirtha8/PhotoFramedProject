package com.tirtha.photoFramed.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tirtha.photoFramed.model.LoginModel;
import com.tirtha.photoFramed.service.UserService;

@Controller
public class LoginController {
	
	public static final Logger log = Logger.getLogger(LoginController.class);
	
	public static String redirectToLogin = "redirect:/login";
	public static String redirectToUserHome = "redirect:/userHome";
	
	public static String redirectToAdminLogin = "redirect:/admin/adminLogin";
	public static String redirectToAdminHome = "redirect:/admin/adminHome";
	
	@Autowired
	UserService userService;
	
//	method to redirect to the login page on server startup
	@RequestMapping("/")
	public String landToLoginPage(@ModelAttribute("userLogin") LoginModel loginModel) {
		return redirectToLogin;
	}
	
//	method to display the login page
	@RequestMapping("/login")
	public String userLogin( HttpSession session, @ModelAttribute("userLogin") LoginModel loginModel) {
		session.removeAttribute("signupMsg");
		return "login";
	}
	
//	method to display the login page with error
	@RequestMapping("/login/{loginErrorMsg}")
	public String userLoginWithErrorMessage(@PathVariable("loginErrorMsg") String errorMsg, HttpSession session, @ModelAttribute("userLogin") LoginModel loginModel) {
		session.setAttribute("loginError", errorMsg);
		session.removeAttribute("signupMsg");
		return "login";
	}
	
//	method to display a specific page in case the access is denied to the user/admin
	@RequestMapping("/accessDenied")
	public String deniedAccess() {
		return "accessDenied";
	}
	
//	method to display the admin login page
	@RequestMapping("/admin/adminLogin")
	public String adminLogin(HttpSession session, @ModelAttribute("userLogin") LoginModel loginModel) {
		session.removeAttribute("signupMsg");
		return "adminLogin";
	}
	
//	method to log out of the session
	@RequestMapping(path = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		log.trace("User logged out successfully!");
		return redirectToLogin;
	}

//	method to validate the user credentials given in the user login page
	@PostMapping(path = "/validateUserLogin")
	public String validateUser(HttpSession session, @ModelAttribute("userLogin") LoginModel loginModel) {

//		if (errors.hasErrors()) {
//			System.out.println("Errors are there");
//			return "login";
//		} 		
		
		session.removeAttribute("signupMsg");
		session.removeAttribute("loginMsg");
		
		boolean result = userService.validateUserLogin(loginModel);
		if (result) {
			log.info("User logged in successfully!");
			return redirectToUserHome;
		} else {
			session.setAttribute("loginMsg", "Invalid username or password! ");
			log.info("The Username or Password is wrong!");
			return redirectToLogin;
		}
	}
	
//	method to validate the admin credentials given in the admin login page
	@PostMapping(path = "/admin/validateAdminLogin")
	public String validateAdmin(HttpSession session, @ModelAttribute("userLogin") LoginModel loginModel, Errors errors){

		if (errors.hasErrors()) {
			System.out.println("Errors are there");
			return "login";
		} 
		
		session.removeAttribute("signupMsg");
		session.removeAttribute("loginMsg");
				
		boolean result = userService.validateUserLogin(loginModel);
		if (result) {
			log.info("Admin logged in successfully!");
			return redirectToAdminHome;
		} 
		else {
			log.info("The Username or Password is wrong!");
			session.setAttribute("loginMsg", "Invalid username or password! ");
			return redirectToAdminLogin;
		}
	}
	
	
	
//	@PostMapping(path = "/validateLogin")
//	public String validateUser(HttpSession session, @ModelAttribute("userLogin") LoginModel loginModel) {
//
////		if (errors.hasErrors()) {
////			System.out.println("Errors are there");
////			return "login";
////		} 
////		else {
//				
//		session.removeAttribute("signupMsg");
//		session.removeAttribute("loginMsg");
//		
//		boolean result = userService.validateUserLogin(loginModel);
//		if (result) {
//			session.setAttribute("name", loginModel.getUsername());
//			if(loginModel.getUsername().contains("admin")) {
//				log.info("Admin logged in successfully!");
//				return("redirect:/adminHome");
//			}
//			else {
//				log.info("User logged in successfully!");
//				return("redirect:/userHome");
//			}
//		} else {
//			session.setAttribute("loginMsg", "Invalid username or password! ");
//			log.info("The Username or Password is wrong!");
//			return redirectToLogin;
//		}
//	}
}

