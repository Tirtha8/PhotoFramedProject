package com.tirtha.photoFramed.service;

import java.util.List;

import com.tirtha.photoFramed.model.ImageModel;


public interface ImageService {

	public List<ImageModel> getAllImagePosts();
	public List<ImageModel> getImageModelByUserId(int userId);
	public ImageModel getImageModelByImageId(int imageId);
	public void deleteImageByImageId(int imageId);
	public void updateImageByImageTag(String imageTag, int imageId);
}
