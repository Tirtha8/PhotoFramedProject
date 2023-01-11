package com.tirtha.photoFramed.service;

import java.util.List;

import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.LoginModel;
import com.tirtha.photoFramed.model.UserModel;



public interface UserService {

	public boolean validateUserSignup(UserModel userModel);
	public boolean validateUserLogin(LoginModel loginModel);
	public List<UserModel> getAllUsers();
	public UserModel getUserModelByUsername(String username);
	public void saveProfilePicture(String imageName, int id);
	public void updateFullname(String fullname, int userId);
	public void updateMobileNumber(String mobileNumber, int userId);
	public void updateActiveStatus(String active, int userId);
	public void updatePassword(String newPassword, Integer userId);
	public void saveUploadedPost(ImageModel imageModel);
	public void saveUpdatedPostsCount(int count, int userId); 
	
	public void deleteUserWithUserIdByAdmin(int userId);
	
	
}
