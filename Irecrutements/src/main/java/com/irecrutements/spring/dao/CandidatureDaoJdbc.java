package com.irecrutements.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.irecrutements.spring.models.Candidature;
import com.irecrutements.spring.models.Document;
import com.irecrutements.spring.models.Domaine;
import com.irecrutements.spring.models.Ville;
import com.irecrutements.spring.utils.HibernateUtil;
@Component
public class CandidatureDaoJdbc implements CandidatureDao{
	public CandidatureDaoJdbc() {
		
	}
	public int insert(Candidature c) {
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

	public int update(Candidature c) {
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

	public int delete(Candidature c) {
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

	public Candidature selectById(int id) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return (Candidature) session.get(Candidature.class, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectAll() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Candidature").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	public List<Candidature> selectAllByOrdre() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Candidature order by idcandidature desc").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	public List<Candidature> selectByIntituleCandidature(String intituleCandidature) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Candidature where intituleCandidature like :intituleCandidature")
					.setParameter("intituleCandidature",
							"%" + intituleCandidature + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectByTypeCandidature(String typeCandidature) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Candidature where typeCandidature like :typeCandidature")
					.setParameter("typeCandidature",
							"%" + typeCandidature + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectByEtatCandidature(String etatCandidature) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Candidature where etatCandidature like :etatCandidature")
					.setParameter("etatCandidature",
							"%" + etatCandidature + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectByExperienceCandidature(
			String experienceCandidature) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session
					.createQuery(
							"from Candidature where experienceCandidature like :experienceCandidature")
					.setParameter("experienceCandidature",
							"%" + experienceCandidature + "%").list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectByDomaine(Domaine domaine) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select c from Candidature as c"
					+ " left join c.domaines as d"
					+ " where d = :id";

//			Query<Candidature> query = session.createQuery(hqlSelect);
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

	public List<Candidature> selectByCv(Document cv) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select c from Candidature as c"
					+ " where cv = :id";

			Query query = session.createQuery(hqlSelect);
//			Query<Candidature> query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", cv);
			
			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectByMotivation(Document motivation) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select c from Candidature as c"
					+ " where motivation = :id";

//			Query<Candidature> query = session.createQuery(hqlSelect);
			Query query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", motivation);
			
			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	public List<Candidature> selectByVilleCandidature(Ville villeCandidature) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidat as c"
//					+ " left join c.candidatures as i"
//					+ " where i.idCandidature = :id";
			String hqlSelect = "select c from Candidature as c"
					+ " where villeCandidature = :id";

//			Query<Candidature> query = session.createQuery(hqlSelect);
			Query query = session.createQuery(hqlSelect);
//			query.setParameter("id", candidature.getIdCandidature());
			query.setParameter("id", villeCandidature);
			
			return query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	public List<Candidature> selectByFiltre(String motCle, String categorie, Ville villeCandidature, Domaine domaine) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
//			String hqlSelect = "select c from Candidature as c"
//					+ " left join c.domaines as d"
//					+ " where intituleCandidature like :motCle and "
//					+ " typeCandidature like :categorie and "
//					+ " villeCandidature = :villeCandidature and"
//					+ " d = :domaine";
			
			String hqlSelect = "select c from Candidature as c "
							+ " left join c.domaines as d where "
							+ " intituleCandidature like :motCle and "
							+ " typeCandidature like :categorie ";
			
			
			if(villeCandidature != null) hqlSelect += " and villeCandidature_idVille = :villeCandidature";
			if(domaine != null) hqlSelect += " and d = :domaine";
			
//			hqlSelect += " order by idcandidature desc";
			
			Query query = session.createQuery(hqlSelect);
			query.setParameter("motCle","%" + motCle + "%");
			query.setParameter("categorie", "%" + categorie + "%");
			
			if(villeCandidature != null) query.setParameter("villeCandidature", villeCandidature.getIdVille());
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
