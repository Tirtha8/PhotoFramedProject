package com.tirtha.photoFramed.dao;

import java.util.List;

import com.tirtha.photoFramed.model.ImageModel;

public interface ImageDao {

	public ImageModel getImageModelById(int imageId);
	public List<ImageModel> getAllPosts();
	public List<ImageModel> getImageModelByUserId(int userId);
	public void deleteImageModel(int imageId);
	public void updateImageByImageTag(String imageTag, int imageId);
	
}
