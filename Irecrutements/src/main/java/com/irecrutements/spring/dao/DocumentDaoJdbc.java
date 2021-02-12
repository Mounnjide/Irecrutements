package com.irecrutements.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.utils.HibernateUtil;
@Component
public class DocumentDaoJdbc implements DocumentDao{
	public DocumentDaoJdbc() {
		
	}

	public int insert(Document c) {
		Session session = null;
		Transaction tx = null;
		int id = -1;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			id = (Integer) session.save(c);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (tx != null)
				tx.rollback();
			return id;
		} finally {
			session.close();
		}
		return id;
	}

	public int update(Document c) {
		Session session = null;
		Transaction tx = null;
		int id = -1;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.update(c);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (tx != null)
				tx.rollback();
			return id;
		} finally {
			session.close();
		}
		return 0;
	}

	public int delete(Document c) {
		Session session = null;
		Transaction tx = null;
		int id = -1;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(c);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (tx != null)
				tx.rollback();
			return id;
		} finally {
			session.close();
		}
		return 0;
	}

	public List<Document> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Document").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Document selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Document) session.get(Document.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Document> selectByTitreDocument(String titreDocument) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Document where titreDocument like :titreDocument")
					.setParameter("titreDocument",
							"%" + titreDocument + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Document> selectByTypeDocument(String typeDocument) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Document where typeDocument like :typeDocument")
					.setParameter("typeDocument",
							"%" + typeDocument + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
}
