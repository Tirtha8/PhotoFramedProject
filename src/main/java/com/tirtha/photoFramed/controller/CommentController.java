package com.tirtha.photoFramed.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tirtha.photoFramed.model.CommentModel;
import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.service.CommentService;
import com.tirtha.photoFramed.service.ImageService;

@Controller
public class CommentController {

	public static final Logger log = Logger.getLogger(CommentController.class);
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ImageService imageService;
	
	public static String redirectToUserHome = "redirect:/userHome";
	
	
//	method to add a comment by the user
	@RequestMapping("/userHome/addComment")
	public ModelAndView addCommentFromHomePost(@RequestParam("postComment") String comment, 
							 					@RequestParam("commentorUsername") String commentorUsername,
							 					@RequestParam("imageId") int imageId, 
							 					ModelAndView model){
		try {
			if( !comment.equals("")) {
				ImageModel imageModel = imageService.getImageModelByImageId(imageId);
				CommentModel commentModel = new CommentModel();
				commentModel.setCommentString(comment);
				commentModel.setImageModel(imageModel);
				commentModel.setCommentorUsername(commentorUsername);
				commentService.addComment(commentModel);
			}
		} catch (Exception e) {
			log.debug("Comment size is invalid! " + e.getMessage());
		}
		
		
		model.setViewName(redirectToUserHome);
		return model;
	}
	
//	method to delete his/her comment on a post by the user
	@RequestMapping("/userHome/deleteComment")
	public ModelAndView deleteCommentFromHomePost(@RequestParam("commentId") int commentId, ModelAndView model) {
		
		commentService.deleteCommentByCommentId(commentId);
		model.setViewName(redirectToUserHome);	
		return model;
	}
	
//	method to display the page which allows the user to edit his/her comment of a post
	@RequestMapping("/userHome/editComment")
	public ModelAndView editCommentFromHomePost(@RequestParam("commentId") int commentId,
												@RequestParam("comment") String comment,
												ModelAndView model) {
		
		model.addObject("commentId", commentId);
		model.addObject("comment", comment);
		model.setViewName("editComment");
		return model;
	}
	

//	method to update a comment by user
	@RequestMapping("/userHome/updateNewComment")
	public ModelAndView updatedCommentFromHomePost( @RequestParam("editedComment") String editedComment, 
													@RequestParam("commentId") int commentId,
													ModelAndView model) {
		
		
		commentService.updateCommentByCommentId(editedComment, commentId);
		model.setViewName(redirectToUserHome);
		
		return model;
	}
	
}
