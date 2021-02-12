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
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Utilisateur;
import com.irecrutements.spring.utils.HibernateUtil;
@Component
public class UtilisateurDaoJdbc implements UtilisateurDao{
	public UtilisateurDaoJdbc() {

	}

	public int insert(Utilisateur c) {
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

	public int update(Utilisateur c) {
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

	public int delete(Utilisateur c) {
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

	public List<Utilisateur> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Utilisateur").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Utilisateur selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Utilisateur) session.get(Utilisateur.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByNomPrenomUtilisateur(
			String nomPrenomUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Utilisateur where nomPrenomUtilisateur like :nomPrenomUtilisateur")
					.setParameter("nomPrenomUtilisateur",
							"%" + nomPrenomUtilisateur + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByEmailUtilisateur(String emailUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Utilisateur where emailUtilisateur like :emailUtilisateur")
					.setParameter("emailUtilisateur",
							"%" + emailUtilisateur + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	public Utilisateur findByEmailUtilisateur(String emailUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("from Utilisateur where emailUtilisateur like :emailUtilisateur");
			query.setParameter("emailUtilisateur", emailUtilisateur );
			return (Utilisateur)query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByPwdUtilisateur(String pwdUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Utilisateur where pwdUtilisateur like :pwdUtilisateur")
					.setParameter("pwdUtilisateur",
							"%" + pwdUtilisateur + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByCiviliteUtilisateur(
			String civiliteUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Utilisateur where civiliteUtilisateur like :civiliteUtilisateur")
					.setParameter("civiliteUtilisateur",
							"%" + civiliteUtilisateur + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByAdresse(String adresse) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Utilisateur where adresse like :adresse")
					.setParameter("adresse",
							"%" + adresse + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByTelephone(String telephone) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Utilisateur where telephone like :telephone")
					.setParameter("telephone",
							"%" + telephone + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByDateInscription(Date dateInscription) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			Query<Utilisateur> q = session
//					.createQuery("from Utilisateur o where o.dateInscription = :date ");
//			q.setParameter("date", dateInscription, TemporalType.DATE);
//			return q.list();
			return session
					.createQuery("from Utilisateur o where o.dateInscription = :date ")
					.setParameter("date", dateInscription).list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Utilisateur> selectByPhoto(Document photo) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select c from Utilisateur as c"
					+ " where photo = :id";
			Query query = session.createQuery(hqlSelect);
//			Query<Utilisateur> query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", photo);
			
			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
}
