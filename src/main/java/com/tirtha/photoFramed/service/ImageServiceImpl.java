package com.tirtha.photoFramed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tirtha.photoFramed.dao.CommentDaoImpl;
import com.tirtha.photoFramed.dao.ImageDaoImpl;
import com.tirtha.photoFramed.model.ImageModel;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDaoImpl imageDao;
	
	@Autowired
	CommentDaoImpl commentDao;
	
	@Override
	public List<ImageModel> getAllImagePosts() {
		return imageDao.getAllPosts();
	}

	@Override
	public List<ImageModel> getImageModelByUserId(int userId) {
		return imageDao.getImageModelByUserId(userId);
	}

	@Override
	public ImageModel getImageModelByImageId(int imageId) {
		return imageDao.getImageModelById(imageId);
	}

	@Override
	public void deleteImageByImageId(int imageId) {
		commentDao.deleteCommentModelByImageId(imageId);
		imageDao.deleteImageModel(imageId); 
	}

	@Override
	public void updateImageByImageTag(String imageTag, int imageId) {
		imageDao.updateImageByImageTag(imageTag, imageId);		
	}
	
}
