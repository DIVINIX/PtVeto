/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Consultation.
 * 
 * @author sebastien
 */
public abstract class ConsultationDao {

	private static final Logger LOG = Logger.getLogger(ConsultationDao.class);

	/**
	 * Recuperation de tout les Consultation.
	 * 
	 * @return paysList
	 */
	public static List<Consultation> findAllConsultation() {
		LOG.debug("Requete a la base : Recuperation de tout les Consultation.");

		List<Consultation> consultList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Consultation.findAll");

			consultList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return consultList;
	}

	/**
	 * Recuperation d'un Consultation par son id.
	 * 
	 * @return consult
	 */
	public static Consultation findConsultationById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un Consultation par son id.");

		Consultation consult = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Consultation.findById");
			query.setParameter("id", id);

			consult = (Consultation) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Consultation sous l'id : " + id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return consult;
	}

	/**
	 * Recuperation d'une liste de Consultation par son client.
	 * 
	 * @return venteList
	 */
	public static List<Consultation> findConsultationByClient(Client client) {
		LOG.debug("Requete a la base : Recuperation d'une liste de vente par son Produit.");

		List<Consultation> consultList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Consultation.findByClient");
			query.setParameter("id", client.getId());

			consultList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return consultList;
	}

	/**
	 * Recuperation d'une liste de Consultation par son Animal.
	 * 
	 * @return venteList
	 */
	public static List<Consultation> findConsultationByAnimal(Animal animal) {
		LOG.debug("Requete a la base : Recuperation d'une liste de Consultation par son Animal.");

		List<Consultation> consultList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Consultation.findByAnimal");
			query.setParameter("id", animal.getId());

			consultList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return consultList;
	}

	/**
	 * Insert ou Update d'un Consultation.
	 * 
	 * @param consult
	 * @return l'id
	 */
	public static int saveOrUpdate(Consultation consult) {
		LOG.debug("Requete a la base : Insert ou Update d'un Consultation.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			consult = em.merge(consult);
			em.persist(consult);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return consult.getId();
	}

	/**
	 * Delete de tous les Consultation.
	 * 
	 * @return nb, le nombre Consultation supprime.
	 */
	public static int deleteAllConsultation() {
		LOG.debug("Requete a la base : Delete de toutes les Consultation.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Consultation.removeAll");
			nb = query.executeUpdate();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		LOG.debug("Fin de requete a la base.");
		return nb;
	}

	/**
	 * Delete d'un Consultation.
	 * 
	 * @param consult
	 */
	public static void delete(Consultation consult) {
		LOG.debug("Requete a la base : Delete d'un Consultation.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(consult));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
}
