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
@Table(name = "commentmodel")
public class CommentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
	@SequenceGenerator(name = "comment_seq", allocationSize = 1, initialValue = 1000)
	private int commentId;
	
	private String commentorUsername;
	private String commentString;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ImageModel imageModel;

	public CommentModel() {
	}

	public CommentModel(String commentorUsername, String commentString, ImageModel imageModel) {
		this.commentorUsername = commentorUsername;
		this.commentString = commentString;
		this.imageModel = imageModel;
	}

	public int getCommentId() {
		return commentId;
	}

//	public void setCommentId(int commentId) {
//		this.commentId = commentId;
//	}

	public String getCommentorUsername() {
		return commentorUsername;
	}

	public void setCommentorUsername(String commentorUsername) {
		this.commentorUsername = commentorUsername;
	}

	public String getCommentString() {
		return commentString;
	}

	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}

	public ImageModel getImageModel() {
		return imageModel;
	}

	public void setImageModel(ImageModel imageModel) {
		this.imageModel = imageModel;
	}

	@Override
	public String toString() {
		return "CommentModel [commentId=" + commentId + ", commentorUsername=" + commentorUsername + ", commentString="
				+ commentString + ", imageModel=" + imageModel + "]";
	}

	
	
}
