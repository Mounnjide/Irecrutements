package com.irecrutements.spring.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.irecrutements.spring.models.Candidat;
import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Utilisateur;
import com.irecrutements.spring.utils.HibernateUtil;
@Component
public class CandidatDaoJdbc implements CandidatDao {

	public CandidatDaoJdbc() {
		super();
	}

	public int insert(Candidat c) {
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

	public int update(Candidat c) {
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

	public int delete(Candidat c) {
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

	public Candidat selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Candidat) session.get(Candidat.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	public Candidat findByEmailUtilisateur(String emailUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hqlSelect = "from Utilisateur where emailUtilisateur like :emailUtilisateur";

			Query query = session.createQuery(hqlSelect);
			query.setParameter("emailUtilisateur", emailUtilisateur);
			
			return (Candidat) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	public List<Candidat> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Candidat").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidat> selectByDateNaissance(Date date) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			Query<Candidat> q = session
//					.createQuery("from Candidat o where o.dateNaissance = :date ");
//			q.setParameter("date", date, TemporalType.DATE);
			Query q = session
					.createQuery("from Candidat o where o.dateNaissance = :date ");
			q.setParameter("date", date );
			return q.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidat> selectByEtablissementEtude(String etablissementEtude) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Candidat where etablissementEtude like :etablissementEtude")
					.setParameter("etablissementEtude",
							"%" + etablissementEtude + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidat> selectByNiveauEtude(String niveauEtude) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Candidat where niveauEtude like :niveauEtude")
					.setParameter("niveauEtude", "%" + niveauEtude + "%")
					.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Candidat selectByCandidature(Candidature candidature) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select c from Candidat as c"
					+ " left join c.candidatures as i"
					+ " where i = :id";

//			Query<Candidat> query = session.createQuery(hqlSelect);
			Query query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", candidature);
			
			return (Candidat) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Utilisateur emailExiste(String email) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hqlSelect = "select e from Utilisateur as e where emailUtilisateur = :email";

			Query query = session.createQuery(hqlSelect);
			query.setParameter("email", email);
			
			return (Utilisateur) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
}
