package com.tirtha.photoFramed.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tirtha.photoFramed.dao.UserDaoImpl;
import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.LoginModel;
import com.tirtha.photoFramed.model.UserModel;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDaoImpl userDao;
	

	@Override
	public boolean validateUserSignup(UserModel userModel) {
		return userDao.createNewUserAccount(userModel);
	}

	@Override
	public boolean validateUserLogin(LoginModel loginModel) {
		return userDao.checkUser(loginModel.getUsername(), loginModel.getPassword());
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public UserModel getUserModelByUsername(String username) {
		return userDao.getUserModelByUsername(username);
	}

	@Override
	public void saveProfilePicture(String imageName, int id) {
		userDao.saveProfileImage(imageName, id);
		
	}

	@Override
	public void updateFullname(String fullname, int userId) {
		userDao.updateFullname(fullname, userId);
	}

	@Override
	public void updateMobileNumber(String mobileNumber, int userId) {
		userDao.updateMobileNumber(mobileNumber, userId);
	}

	@Override
	public void updateActiveStatus(String active, int userId) {
		userDao.updateActive(active, userId);
	}

	@Override
	public void updatePassword(String newPassword, Integer userId) {
		userDao.updatePassword(newPassword, userId);
	}

	@Override
	public void saveUploadedPost(ImageModel imageModel) {
		userDao.savePost(imageModel);
	}

	@Override
	public void saveUpdatedPostsCount(int count, int userId) {
		userDao.updatePostsCount(count, userId);
	}

	@Override
	public void deleteUserWithUserIdByAdmin(int userId) {
		UserModel userModel = userDao.getUserModelByUserId(userId);
		userDao.deleteUserModel(userModel);
	}
			
}
