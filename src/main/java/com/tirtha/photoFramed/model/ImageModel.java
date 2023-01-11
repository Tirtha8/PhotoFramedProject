package com.tirtha.photoFramed.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "imagemodel")
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
	@SequenceGenerator(name = "image_seq", allocationSize = 1, initialValue = 1000)
	private int imageId;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserModel userModel;

	private String imageName;
	private String imageTag;
	
	private int likes;
	private int comments;
	private int shares;



	public ImageModel() {
	}

	public ImageModel(UserModel userModel, String imageName, String imageTag, int likes, int comments, int shares) {
		this.userModel = userModel;
		this.imageName = imageName;
		this.imageTag = imageTag;
		this.likes = likes;
		this.comments = comments;
		this.shares = shares;
	}

	public int getImageId() {
		return imageId;
	}

//	public void setImageId(int imageId) {
//		this.imageId = imageId;
//	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageTag() {
		return imageTag;
	}

	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "ImageModel [imageId=" + imageId + ", userModel=" + userModel + ", imageName=" + imageName
				+ ", imageTag=" + imageTag + ", likes=" + likes + ", comments=" + comments + ", shares=" + shares + "]";
	}

	


	
	
	
}
