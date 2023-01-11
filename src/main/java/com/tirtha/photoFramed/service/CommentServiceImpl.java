package com.tirtha.photoFramed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirtha.photoFramed.dao.CommentDao;
import com.tirtha.photoFramed.model.CommentModel;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public void addComment(CommentModel commentModel) {
		commentDao.addComment(commentModel);
	}

	@Override
	public List<CommentModel> getCommentModels() {
		return commentDao.getCommentModels();
	}

	@Override
	public List<CommentModel> getCommentModelByImageId(int imageId) {
		return commentDao.getCommentModelByImageId(imageId);
	}

	@Override
	public void updateCommentByCommentId(String commentString, int commentId) {
		commentDao.updateCommentByCommentId(commentString, commentId);
	}

	@Override
	public void deleteCommentByCommentId(int commentId) {
		commentDao.deleteCommentByCommentId(commentId);
	}
}
