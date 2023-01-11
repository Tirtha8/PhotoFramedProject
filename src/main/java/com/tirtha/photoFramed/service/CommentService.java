package com.tirtha.photoFramed.service;

import java.util.List;

import com.tirtha.photoFramed.model.CommentModel;


public interface CommentService {

	public void addComment(CommentModel commentModel);
	public List<CommentModel> getCommentModels();
	public List<CommentModel> getCommentModelByImageId(int imageId);
	public void updateCommentByCommentId(String commentString, int commentId);
	public void deleteCommentByCommentId(int commentId);
}
