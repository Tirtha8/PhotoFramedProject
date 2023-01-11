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

@Repository
public class ImageDaoImpl implements ImageDao{
	
	public static final Logger log = Logger.getLogger(ImageDaoImpl.class);
	
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

//	Implementation
	public ImageModel getImageModelById(int imageId) {
		return hibernateTemplate.get(ImageModel.class, imageId);
	}

	public List<ImageModel> getAllPosts() {
		return hibernateTemplate.loadAll(ImageModel.class);
	}
	
	
	public List<ImageModel> getImageModelByUserId(int userId) {
		List<ImageModel> list = new ArrayList<>();
		try(Session session = sessionFactory.openSession()) {
			String sqlQuery = " from ImageModel im where im.userModel.userId=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, userId);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	@Transactional
	public void deleteImageModel(int imageId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "delete from ImageModel where imageId=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, imageId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void updateImageByImageTag(String imageTag, int imageId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update ImageModel im set im.imageTag=?1 where im.imageId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, imageTag);
			query.setParameter(2, imageId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
