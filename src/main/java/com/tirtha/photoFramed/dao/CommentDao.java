package com.tirtha.photoFramed.dao;

import java.util.List;

import com.tirtha.photoFramed.model.CommentModel;

public interface CommentDao {
	
	public List<CommentModel> getCommentModels();
	public void addComment(CommentModel commentModel);
	public List<CommentModel> getCommentModelByImageId(int imageId);
	public void deleteCommentByCommentId(int commentId);
	public void updateCommentByCommentId(String commentString, int commentId);
	public void deleteCommentModelByImageId(int imageId);
	

}
