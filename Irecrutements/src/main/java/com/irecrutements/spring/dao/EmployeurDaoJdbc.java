package com.irecrutements.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Employeur;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Utilisateur;
import com.irecrutements.spring.utils.HibernateUtil;
@Component
public class EmployeurDaoJdbc implements EmployeurDao{
	public EmployeurDaoJdbc() {
		
	}

	public int insert(Employeur c) {
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
			return -1;
		} finally {
			session.close();
		}
		return id;
	}

	public int update(Employeur c) {
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

	public int delete(Employeur c) {
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

	public List<Employeur> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Employer").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Employeur selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Employeur) session.get(Employeur.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	@Override
	public Employeur findByEmailUtilisateur(String emailUtilisateur) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hqlSelect = "from Utilisateur where emailUtilisateur like :emailUtilisateur";
			Query query = session.createQuery(hqlSelect);
			query.setParameter("emailUtilisateur", emailUtilisateur);
			
			return (Employeur) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	public List<Employeur> selectByInfoEntreprise(String infoEntreprise) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Employer where infoEntreprise like :infoEntreprise")
					.setParameter("infoEntreprise",
							"%" + infoEntreprise + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Employeur> selectBySiteWeb(String siteWeb) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Employer where siteWeb like :siteWeb")
					.setParameter("siteWeb",
							"%" + siteWeb + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Employeur> selectByFacebookEntreprise(String facebookEntreprise) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Employer where facebookEntreprise like :facebookEntreprise")
					.setParameter("facebookEntreprise",
							"%" + facebookEntreprise + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Employeur> selectByLinkedinEntreprise(String linkedinEntreprise) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Employer where linkedinEntreprise like :linkedinEntreprise")
					.setParameter("linkedinEntreprise",
							"%" + linkedinEntreprise + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public Employeur selectByOffre(Offre offre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select e from Employer as e"
					+ " left join e.offres as i"
					+ " where i = :id";

//			Query<Employer> query = session.createQuery(hqlSelect);
			Query query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", offre);
			
			return (Employeur) query.uniqueResult();
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
