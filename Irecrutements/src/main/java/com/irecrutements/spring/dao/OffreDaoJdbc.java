package com.irecrutements.spring.dao;

import java.util.Date;

import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Offre;
import com.irecrutements.spring.models.Ville;
import com.irecrutements.spring.utils.HibernateUtil;
@Component
public class OffreDaoJdbc implements OffreDao{
	public OffreDaoJdbc() {
	}

	public int insert(Offre c) {
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

	public int update(Offre c) {
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

	public int delete(Offre c) {
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

	public List<Offre> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Offre").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectAllByOrder() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Offre order by idOffre desc").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	public Offre selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Offre) session.get(Offre.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByIntituleOffre(String intituleOffre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Offre where intituleOffre like :intituleOffre")
					.setParameter("intituleOffre",
							"%" + intituleOffre + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByTypeOffre(String typeOffre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Offre where typeOffre like :typeOffre")
					.setParameter("typeOffre",
							"%" + typeOffre + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByDescriptionOffre(String descriptionOffre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Offre where descriptionOffre like :descriptionOffre")
					.setParameter("descriptionOffre",
							"%" + descriptionOffre + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByDateDebutOffre(Date dateDebutOffre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			Query<Offre> q = session
//					.createQuery("from Offre o where o.dateDebutOffre = :date ");
			Query q = session
					.createQuery("from Offre o where o.dateDebutOffre = :date ");
//			q.setParameter("date", dateDebutOffre, TemporalType.DATE);
			q.setParameter("date", dateDebutOffre);
			
			return q.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByDateFinOffre(Date dateFinOffre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			Query<Offre> q = session
//					.createQuery("from Offre o where o.dateFinOffre = :date ");
//			q.setParameter("date", dateFinOffre, TemporalType.DATE);
			Query q = session
					.createQuery("from Offre o where o.dateFinOffre = :date ");
			q.setParameter("date", dateFinOffre );
			return q.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByDureeOffre(String dureeOffre) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Offre where dureeOffre like :dureeOffre")
					.setParameter("dureeOffre",
							"%" + dureeOffre + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByExperienceRequise(String experienceRequise) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Offre where experienceRequise like :experienceRequise")
					.setParameter("experienceRequise",
							"%" + experienceRequise + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Offre> selectByDomaine(Domaine domaine) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select o from Offre as o"
					+ " left join o.domaines as d"
					+ " where d = :id";

//			Query<Offre> query = session.createQuery(hqlSelect);
			Query query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", domaine);

			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	@Override
	public List<Offre> selectByFiltre(String motCle, String categorie, Ville villeOffre, Domaine domaine) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			String hqlSelect = "select o from Offre as o "
							+ " left join o.domaines as d where "
							+ " intituleOffre like :motCle and "
							+ " typeOffre like :categorie ";
			
			
			if(villeOffre != null) hqlSelect += " and villeOffre_idVille = :villeOffre";
			if(domaine != null) hqlSelect += " and d = :domaine";
			
//			hqlSelect += " order by idcandidature desc";
			
			Query query = session.createQuery(hqlSelect);
			query.setParameter("motCle","%" + motCle + "%");
			query.setParameter("categorie", "%" + categorie + "%");
			
			if(villeOffre != null) query.setParameter("villeOffre", villeOffre.getIdVille());
			if(domaine != null) query.setParameter("domaine", domaine);
//			System.out.println(query.getQueryString());
			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
}
