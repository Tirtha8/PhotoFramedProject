package com.tirtha.photoFramed.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tirtha.photoFramed.model.ImageModel;
import com.tirtha.photoFramed.model.UserModel;

@Repository
public class UserDaoImpl implements UserDao {

	public static final Logger log = Logger.getLogger(UserDaoImpl.class);
	
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	//Implementation
	
	public UserModel getUserModelByUserId(int userId) {
		return hibernateTemplate.get(UserModel.class, userId);
	}

	
	@Transactional
	public void deleteUserModel(UserModel userModel) {
		try {
			hibernateTemplate.delete(userModel);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
		
	@Transactional
	public void updateActive(String active, int userId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update UserModel um set um.active=?1 where um.userId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, active);
			query.setParameter(2, userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateMobileNumber(String mobileNumber, int userId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update UserModel um set um.mobileNumber=?1 where um.userId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, mobileNumber);
			query.setParameter(2, userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateFullname(String fullname, int userId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update UserModel um set um.fullName=?1 where um.userId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, fullname);
			query.setParameter(2, userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updatePassword(String newPassword, Integer userId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update UserModel um set um.password=?1 where um.userId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, newPassword);
			query.setParameter(2, userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Transactional
	public void updatePostsCount(int count, int userId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update UserModel um set um.posts=?1 where um.userId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, count);
			query.setParameter(2, userId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void savePost(ImageModel imageModel) {
		hibernateTemplate.saveOrUpdate(imageModel);
	}

	
	@Transactional
	public void saveProfileImage(String imageName, int id) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update UserModel um set um.profilePicture=?1 where um.userId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, imageName);
			query.setParameter(2, id);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To create new User account
	@Transactional
	public boolean createNewUserAccount(UserModel userModel) {	
		
		List<UserModel> list1 = new ArrayList<>();
		List<UserModel> list2 = new ArrayList<>();
		
		try(Session session = sessionFactory.openSession()) {
			String sqlQuery = "from UserModel um where um.username=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, userModel.getUsername());
			list1 = query.list();
			
			sqlQuery = "from UserModel um where um.email=?1";
			query = session.createQuery(sqlQuery);
			query.setParameter(1, userModel.getEmail());
			list2 = query.list();
			
			if(list1.isEmpty() && list2.isEmpty()){
				hibernateTemplate.save(userModel);	
				log.trace("User Model saved successfully!");
				return true;
			}	
			else {
				log.trace("signup un-successful! ");
				return false;			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// To fetch a User by username
	public UserModel getUserModelByUsername(String username) {
		try(Session session = sessionFactory.openSession()){
			String sqlQuery = " from UserModel as um where um.username=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, username);
			List list = query.list();
			if(list != null) {
				return (UserModel) list.get(0);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// To fetch a User by user name
	public boolean checkUser(String userName, String userPassword) {

		log.trace("In Check login");
		boolean userFound = false;

		try(Session session = sessionFactory.openSession()) {
			String sqlQuery = " from UserModel as um where um.username=?1 and um.password=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, userName);
			query.setParameter(2, userPassword);
			List list = query.list();

			if ((list != null) && (!list.isEmpty())) {
				userFound = true;
			}
		}
		return userFound;
	}

	// To fetch all the users
	public List<UserModel> getAllUsers() {
		return hibernateTemplate.loadAll(UserModel.class);
	}
	
	
//	@Transactional
//	public void savePost(ImageModel imageModel, int userId) {
//		try {
//			Session session = sessionFactory.openSession();
//			Transaction tx = session.beginTransaction();
//			String sqlQuery = "update UserModel um set um.imageModel=?1 where um.userId=?2";
//			Query query = session.createQuery(sqlQuery);
//			query.setParameter(1, imageModel);
//			query.setParameter(2, userId);
//			query.executeUpdate();
//			tx.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	//duplicate is there
//	public List<ImageModel> getAllPostsByUserId(int userId){
//		List<ImageModel> list = new ArrayList<>();
//		try(Session session = sessionFactory.openSession()) {
//			String sqlQuery = "from ImageModel as im where im.userModel.userId=?1";
//			Query query = session.createQuery(sqlQuery);
//			query.setParameter(1, userId);
//			list = query.list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
		
	
	
//	public List<CommentModel> getCommentModelsByImageId(int imageId){
//		List<CommentModel> list = new ArrayList<>();
//		try {
//			Session session = sessionFactory.openSession();
//			String sqlQuery = "select * from CommentModel as cm where cm.ImageModel_ImageId=?1";
//			Query query = session.createQuery(sqlQuery);
//			query.setParameter(1, imageId);
//			list = query.list();
//			System.out.println("inside userDao : printing list: " + list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

}
