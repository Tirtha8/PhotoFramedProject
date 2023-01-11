package com.tirtha.photoFramed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usermodel")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", allocationSize = 1, initialValue = 1000)
	private int userId;

	private String fullName;

	@Column(unique = true, nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@Column(unique = true, updatable = false, nullable = false, length = 30)
	private String email;
	
	private String mobileNumber;
	
	private int enabled = 1;
	
	@Column(nullable = false)
	private String active = "Active" ;
	
	private String role = "User";
	
	private String profilePicture = "dpp.png";
	
	private int posts = 0;
	
	private int followers = 0;
	
	private int following = 0;

//	@OneToMany(mappedBy = "userModel")
//	@JoinColumn(name = "user_id")
//	private List<ImageModel> imageModel;

	public UserModel() {
	}

	public UserModel(String username, String fullName, String password, String email, String mobileNumber, int enabled,
			String active, String role, String profilePicture, int posts, int followers, int following) {
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.enabled = enabled;
		this.active = active;
		this.role = role;
		this.profilePicture = profilePicture;
		this.posts = posts;
		this.followers = followers;
		this.following = following;
	}

	public int getUserId() {
		return userId;
	}

//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", username=" + username + ", fullName=" + fullName + ", password="
				+ password + ", email=" + email + ", mobileNumber=" + mobileNumber + ", enabled=" + enabled
				+ ", active=" + active + ", role=" + role + ", profilePicture=" + profilePicture + ", posts=" + posts
				+ ", followers=" + followers + ", following=" + following + "]";
	}
	
		
}
