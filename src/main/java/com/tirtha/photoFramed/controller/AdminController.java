package com.tirtha.photoFramed.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tirtha.photoFramed.model.CommentModel;
import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.UserModel;

import com.tirtha.photoFramed.service.CommentService;
import com.tirtha.photoFramed.service.CommentServiceImpl;
import com.tirtha.photoFramed.service.ImageService;
import com.tirtha.photoFramed.service.ImageServiceImpl;
import com.tirtha.photoFramed.service.UserService;
import com.tirtha.photoFramed.service.UserServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	ImageServiceImpl imageService;
	
	@Autowired 
	CommentServiceImpl commentService;

	public static final Logger log = Logger.getLogger(AdminController.class);
	
	public static final String REDIRECT_TO_ADMIN_HOME = "redirect:/admin/adminHome";
	
//	method to display the admin home page
	 @RequestMapping("/admin/adminHome") 
	 public ModelAndView getAdminHome(HttpSession session, ModelAndView model, Principal principal) {
					
		session.setAttribute("name", principal.getName() );
		List<UserModel> userModelList = userService.getAllUsers();
		model.addObject("userList", userModelList);
		model.setViewName("adminHome");
		return model;
	}
	 
// method to delete a inactive user by admin
	@RequestMapping("/admin/adminHome/deleteUser")
	public ModelAndView deleteUser( HttpSession session,
									@RequestParam("userId") int userId, 
									@RequestParam("active") String activeStatus,
									@RequestParam("role") String role,
									ModelAndView model) {
		
		if(role.equals("ROLE_USER")) {
			if(activeStatus.equals("Active")) {
				session.setAttribute("deletionStatus", "User is still active");
			}	
			else if(activeStatus.equals("Inactive")) {
//				List<UserModel> userModelList = userService.getAllUsers();
//				model.addObject("userList", userModelList);
				userService.deleteUserWithUserIdByAdmin(userId);
				session.setAttribute("deletionStatus", "User deleted successfully!");
			}
		}
		else if(role.equals("ROLE_ADMIN")) {
			userService.deleteUserWithUserIdByAdmin(userId);
			session.setAttribute("deletionStatus", "Admin deleted successfully!");
		}
		
		model.setViewName(REDIRECT_TO_ADMIN_HOME);
		return model;
	}
	
//	method to check all posts uploaded by a user
	@RequestMapping("/admin/adminHome/checkAllImages")
	public ModelAndView checkAllImagesWithUserIdByAdmin(@RequestParam("userId") int userId, ModelAndView model) {
		
		log.trace("checking all the images");
		List<ImageModel> imageModelList = imageService.getImageModelByUserId(userId);
		List<CommentModel> commentModelList = commentService.getCommentModels();
		
		model.addObject("userId", userId);
		model.addObject("commentModelList", commentModelList);
		model.addObject("imageModelList", imageModelList);
		model.setViewName("checkAllImages");
		return model;
	}
	
//	method to delete a post of a user which goes against company policies 
	@PostMapping(path = "/checkAllImages/deletePost")
	public ModelAndView imageDeletedByAdmin(@RequestParam("imageId") int imageId, 
											HttpSession session,
											ModelAndView model) {
		
		log.trace("deleting the post");
		ImageModel imageModel = imageService.getImageModelByImageId(imageId);
		
		int noOfPosts = imageModel.getUserModel().getPosts();
		int userId= imageModel.getUserModel().getUserId();
		
		List<ImageModel> imageModelList = imageService.getImageModelByUserId(userId);
		
		userService.saveUpdatedPostsCount(noOfPosts-1, userId);
		imageService.deleteImageByImageId(imageId);
		
		model.addObject("userId", userId);
		model.addObject("imageModelList", imageModelList);
		model.setViewName("/checkAllImages");
		
		return model;
	}

//	method to create a new admin by existing admin
	@PostMapping(path = "/admin/createNewAdmin")
	public String createNewAdmin(@ModelAttribute("user") UserModel userModel, HttpSession session) {
		
		log.trace("inside create new admin in admin controller");
//		if (errors.hasErrors()) {
//			return "signup";
//		} 
//		else {
//			System.out.println("No errors");
//			String result = userService.validateUserSignup(userModel);
//			return "userHome";
//		}
		if(userModel.getUsername().equals("") || userModel.getEmail().equals("") || userModel.getPassword().equals("")) {
			session.setAttribute("addAdminWarning", "Please enter the required information!");
		}
		else {
			if(userModel.getUsername().contains("admin")) {
				userModel.setRole("ROLE_ADMIN");
			}
			else {
				userModel.setRole("ROLE_USER");			
			}
			userService.validateUserSignup(userModel);
		}
		return REDIRECT_TO_ADMIN_HOME;
	}
	
//	method to upload a default profile pic by admin
//	@PostMapping(path = "/admin/adminHome/uploadDefaultProfilePic")
//	public String uploadDefaultProfilePicture(@RequestParam("profilePic") CommonsMultipartFile file, HttpSession session, Model m) {
//		log.trace("inside fileUploadController");
//		log.info(file.getOriginalFilename());
//		byte[] data = file.getBytes();
//		
//		String path = session.getServletContext().getRealPath("/")
//				+ "WEB-INF" + File.separator + "resources" + File.separator + "images" + File.separator + file.getOriginalFilename();
//		log.info(path);
//		
//		try(FileOutputStream fos = new FileOutputStream(path)) {
//			fos.write(data);
//			log.trace("File uploaded!");		
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//			log.trace("Error while uploading!");
//		}
//		
//		
//		return ADMIN_HOME;
//	}
	
}
