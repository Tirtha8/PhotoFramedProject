package com.tirtha.photoFramed.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;

import com.tirtha.photoFramed.exception.EmptyPostUploadedException;
import com.tirtha.photoFramed.model.CommentModel;
import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.UserModel;
import com.tirtha.photoFramed.service.CommentService;
import com.tirtha.photoFramed.service.ImageService;
import com.tirtha.photoFramed.service.UserService;


@Controller
public class ImageController {

	public static final Logger log = Logger.getLogger(ImageController.class);
	
	public static String redirectToProfile = "redirect:/profile";
	
	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;
	
	@Autowired
	ImageService imageService;
	
//	method to upload a post by the user
	@PostMapping(path="/profile/uploadPost")
	public ModelAndView addImage(@RequestParam("userPost") CommonsMultipartFile file,
								 @RequestParam("imageTag") String imageTag,
								 HttpSession session, RedirectView view, ModelAndView model ){
		
		try {
			if(file.getOriginalFilename().equals("")) {
				throw new EmptyPostUploadedException("Empty post uploaded!");
			}
			else {
				
				ImageModel imageModel = new ImageModel();
				imageModel.setImageName(file.getOriginalFilename());
				imageModel.setImageTag(imageTag);
			
				byte[] data = file.getBytes();
				
				String path = session.getServletContext().getRealPath("/")
						+ "WEB-INF" + File.separator + "resources" + File.separator + "images" + File.separator + file.getOriginalFilename();
				log.info(path);
			
				try (FileOutputStream fos = new FileOutputStream(path)){
					fos.write(data);
					log.trace("File uploaded!");	
					
					String username = (String) session.getAttribute("name");
					UserModel userModel = userService.getUserModelByUsername(username);
					Integer userId= userModel.getUserId();
					imageModel.setUserModel(userModel);
					
					int noOfPosts = userModel.getPosts();
					userService.saveUpdatedPostsCount(noOfPosts+1, userId);
					userService.saveUploadedPost(imageModel);
					
					model.addObject("userModelDetails", userModel);
					/* session.setAttribute("profile", file); */
				}
				catch(IOException e) {
					log.info("Error while uploading!");
					log.debug(e);
				}
			}
		}
		catch (EmptyPostUploadedException e) {
			log.info("Error while uploading!");
			log.debug(e);
		}
		
		model.setViewName(redirectToProfile);
		return model;
	}
	
	
//	method to edit an uploaded post, by the user
	@PostMapping(path = "/profile/editPost")
	public ModelAndView viewUpdateImage(
										@RequestParam("imageName") String imageName,   
										@RequestParam("imageTag") String imageTag, 
										@RequestParam("imageId") int imageId, 
										ModelAndView model) {
		
		model.addObject("imageTag", imageTag); 
		model.addObject("imageName", imageName);
		model.addObject("imageId", imageId);
		
		model.setViewName("editPost");
		return model;
	}
	
//	method that helps in editing the uploaded post
	@PostMapping(path = "/profile/editPost/postEdited")
	public ModelAndView imageUpdatedByUser(	@RequestParam("imageTag") String imageTag, 
											@RequestParam("imageId") int imageId, 
											ModelAndView model) {
		
		imageService.updateImageByImageTag(imageTag, imageId);
		model.setViewName(redirectToProfile);
		return model;
	}
	
	
//	method to delete a uploaded post, by user
	@PostMapping(path = "/profile/deletePost")
	public ModelAndView imagedeletedByUser(@RequestParam("imageId") int imageId, 
											HttpSession session, ModelAndView model) {
		
		imageService.deleteImageByImageId(imageId);
		
		String username = (String) session.getAttribute("name");
		UserModel userModel = userService.getUserModelByUsername(username);
		int noOfPosts = userModel.getPosts();
		userService.saveUpdatedPostsCount(noOfPosts-1, userModel.getUserId());

		model.setViewName(redirectToProfile);
		return model;
	}
	

//	method that allows the admin to edit an post uploaded by user
	@PostMapping(path = "/admin/adminHome/checkAllImages/editPost")
	public ModelAndView editImageByAdmin(@RequestParam("imageName") String imageName,   
										@RequestParam("imageTag") String imageTag, 
										@RequestParam("imageId") int imageId, 
										@RequestParam("userId") int userId, 
										ModelAndView model) {
							
		model.addObject("userId", userId);
		model.addObject("imageTag", imageTag); 
		model.addObject("imageName", imageName);
		model.addObject("imageId", imageId);
		
		model.setViewName("adminEditPost");
		return model;
	}
	
//	method the helps admin to edit the post uploaded
	@PostMapping(path = "/admin/adminHome/checkAllImages/postEditedByAdmin")
	public ModelAndView imageUpdatedByAdmin(@RequestParam("imageTag") String imageTag, 
											@RequestParam("imageId") int imageId, 
											@RequestParam("userId") int userId, 
											ModelAndView model) {
		
		imageService.updateImageByImageTag(imageTag, imageId);
		
		List<ImageModel> imageModelList = imageService.getImageModelByUserId(userId);
		List<CommentModel> commentModelList = commentService.getCommentModels();
		
		model.addObject("userId", userId);
		model.addObject("commentModelList", commentModelList);
		model.addObject("imageModelList", imageModelList);
		log.trace("post edited by admin");
		model.setViewName("redirect:/admin/adminHome/checkAllImages");
		return model;
	}
	
//	method that allows the admin to delete a post uploaded by user
	@PostMapping(path = "/admin/adminHome/checkAllImages/deletePost")
	public ModelAndView imageDeletedByAdmin(@RequestParam("imageId") int imageId, 
											@RequestParam("userId") int userId, 
											ModelAndView model) {
				
		imageService.deleteImageByImageId(imageId);
		
		List<ImageModel> imageModelList = imageService.getImageModelByUserId(userId);
		List<CommentModel> commentModelList = commentService.getCommentModels();
		
		model.addObject("userId", userId);
		model.addObject("commentModelList", commentModelList);
		model.addObject("imageModelList", imageModelList);
		log.trace("post deleted by admin");
		model.setViewName("redirect:/admin/adminHome/checkAllImages");
		return model;
	}

	
}
