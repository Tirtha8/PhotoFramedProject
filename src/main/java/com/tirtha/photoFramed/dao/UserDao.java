package com.tirtha.photoFramed.dao;

import java.util.List;

import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.UserModel;

public interface UserDao {

	public UserModel getUserModelByUserId(int userId);
	public void deleteUserModel(UserModel userModel);
	
	public void updateActive(String active, int userId);
	public void updateMobileNumber(String mobileNumber, int userId);
	public void updateFullname(String fullname, int userId);
	public void updatePassword(String newPassword, Integer userId);
	public void updatePostsCount(int count, int userId);
	public void savePost(ImageModel imageModel);
	public void saveProfileImage(String imageName, int id);
	
	
	public boolean createNewUserAccount(UserModel userModel);
	public UserModel getUserModelByUsername(String username);
	public boolean checkUser(String userName, String userPassword);
	public List<UserModel> getAllUsers();
	
	
}
