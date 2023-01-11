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

import com.tirtha.photoFramed.model.CommentModel;

@Repository
public class CommentDaoImpl implements CommentDao{

	public static final Logger log = Logger.getLogger(CommentDaoImpl.class);
	
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
	public List<CommentModel> getCommentModels(){
		return hibernateTemplate.loadAll(CommentModel.class);
	}
	

	@Transactional
	public void addComment(CommentModel commentModel) {
		hibernateTemplate.saveOrUpdate(commentModel);
	}
	
	
	public List<CommentModel> getCommentModelByImageId(int imageId) {
		List<CommentModel> list = new ArrayList<>();
		try(Session session = sessionFactory.openSession()) {
			String sqlQuery = "select * from CommentModel cm where cm.imageModel.imageId=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, imageId);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Transactional
	public void deleteCommentByCommentId(int commentId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "delete from CommentModel cm where cm.commentId=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, commentId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transactional
	public void updateCommentByCommentId(String commentString, int commentId) {
		try (Session session = sessionFactory.openSession()){
			Transaction tx = session.beginTransaction();
			String sqlQuery = "update CommentModel cm set commentString=?1 where cm.commentId=?2";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, commentString);
			query.setParameter(2, commentId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Transactional
	public void deleteCommentModelByImageId(int imageId) {
		try(Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			String sqlQuery = "delete from CommentModel as cm where cm.imageModel.imageId=?1";
			Query query = session.createQuery(sqlQuery);
			query.setParameter(1, imageId);
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
