package com.irecrutements.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Remarque;
import com.irecrutements.spring.utils.HibernateUtil;

@Component
public class RemarqueDaoJdbc implements RemarqueDao{
	public RemarqueDaoJdbc() {
	}

	public int insert(Remarque c) {
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

	public int update(Remarque c) {
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

	public int delete(Remarque c) {
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

	public List<Remarque> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Remarque").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Remarque selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Remarque) session.get(Remarque.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Remarque> selectByTypeRemarque(String typeRemarque) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Remarque where typeRemarque like :typeRemarque")
					.setParameter("typeRemarque",
							"%" + typeRemarque + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Remarque> selectByCoreRemarque(String coreRemarque) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Remarque where coreRemarque like :coreRemarque")
					.setParameter("coreRemarque",
							"%" + coreRemarque + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
}
