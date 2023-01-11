package com.tirtha.photoFramed.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tirtha.photoFramed.model.UserModel;
import com.tirtha.photoFramed.service.UserService;

@Controller
public class UploadController {
	
	public static final Logger log = Logger.getLogger(UploadController.class);
	
	public static String redirectToProfile = "redirect:/profile";
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//	method to display the file form page 
	@RequestMapping(path= "/profile/fileForm")
	public ModelAndView showUploadForm(HttpSession session, ModelAndView model) {
		String username = (String) session.getAttribute("name");
		UserModel userModel = userService.getUserModelByUsername(username);
		model.addObject("userModel", userModel);
		model.setViewName("fileForm");
		return model;
	}
	
//	method to change the account status of the user
	@PostMapping(path = "/profile/fileForm/uploadActive")
	public ModelAndView uploadActiveStatus(@RequestParam("active") String active, HttpSession session, ModelAndView model) {
		String username = (String) session.getAttribute("name");
		UserModel userModel = userService.getUserModelByUsername(username);
		int userId= userModel.getUserId();
		userService.updateActiveStatus(active, userId);			
		model.setViewName(redirectToProfile);
		return model;		
	}
	
//	method to update the mobile number
	@PostMapping(path = "/profile/fileForm/uploadMobileNumber")
	public ModelAndView uploadMobileNumber(@RequestParam("mobileNumber") String mobileNumber, 
											HttpSession session, ModelAndView model) {
		String username = (String) session.getAttribute("name");
		UserModel userModel = userService.getUserModelByUsername(username);
		Integer userId= userModel.getUserId();
		userService.updateMobileNumber(mobileNumber, userId);		
		model.setViewName(redirectToProfile);
		return model;		
	}
	
	
//	method to update the existing password
	@PostMapping(path = "/profile/fileForm/updateNewPassword")
	public ModelAndView updatePassword(@RequestParam("existingPassword") String existingPassword,
										@RequestParam("newPassword") String newPassword,
										HttpSession session, ModelAndView model) {
		String username = (String) session.getAttribute("name");
		UserModel userModel = userService.getUserModelByUsername(username);
		Integer userId= userModel.getUserId();
		String encryptedPassword = passwordEncoder.encode(newPassword);
		userService.updatePassword( encryptedPassword, userId);		
		model.setViewName(redirectToProfile);
		return model;		
	}
	
	//,ethod to upload the user full name
	@PostMapping(path = "/profile/fileForm/uploadFullname")
	public ModelAndView uploadFullname(@RequestParam("fullname") String fullname, HttpSession session, ModelAndView model) {
		String username = (String) session.getAttribute("name");
		UserModel userModel = userService.getUserModelByUsername(username);
		Integer userId= userModel.getUserId();
		userService.updateFullname(fullname, userId);		
		model.setViewName(redirectToProfile);
		return model;		
	}
	
	// method for uploading profile picture
	@PostMapping(path = "/profile/fileForm/uploadProfilePic")
	public String fileUpload(@RequestParam("profilePic") CommonsMultipartFile file, HttpSession session, Model m) {
		log.trace("inside fileUploadController");
		log.info(file.getOriginalFilename());
		byte[] data = file.getBytes();
		
		String path = session.getServletContext().getRealPath("/")
				+ "WEB-INF" + File.separator + "resources" + File.separator + "images" + File.separator + file.getOriginalFilename();
		log.info(path);
		
		try (FileOutputStream fos = new FileOutputStream(path)){
			fos.write(data);
			log.trace("File uploaded!");	
			
			String username = (String) session.getAttribute("name");
			UserModel userModel = userService.getUserModelByUsername(username);
			int userId= userModel.getUserId();
			session.setAttribute("userId", userId) ;
			userService.saveProfilePicture(file.getOriginalFilename(), userId);
			
		}
		catch(IOException e) {
			e.printStackTrace();
			log.trace("Error while uploading!");
		}
		return redirectToProfile;
	}
	
	
}
