/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.traitement.Rappel;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Dossier et Rappel.
 * @author sebastien
 */
public abstract class RappelDao {
	
	private static final Logger LOG = Logger.getLogger(RappelDao.class);
	
	/**
	 * Recuperation de tout les Rappel.
	 * 
	 * @return rappelList
	 */
	public static List<Rappel> findAllRappel() {
		LOG.debug("Requete a la base : Recuperation de tout les Rappel.");

		List<Rappel> rappelList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Rappel.findAll");

			rappelList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return rappelList;
	}
	
	/**
	 * Recuperation d'un Rappel par son id.
	 * 
	 * @return Rappel
	 */
	public static Rappel findRappelById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un Rappel par son id.");

		Rappel rappel = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Rappel.findById");
			query.setParameter("id", id);
			
			rappel = (Rappel) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Rappel sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return rappel;
	}
	
	/**
	 * Recuperation d'une liste de Rappel par son animal.
	 * 
	 * @return rappelList
	 */
	public static List<Rappel> findRappelByAnimal(Animal animal) {
		LOG.debug("Requete a la base : Recuperation d'une liste de Rappel par son animal.");

		List<Rappel> rappelList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Rappel.findByAnimal");
			query.setParameter("id", animal.getId());

			rappelList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return rappelList;
	}
	
	/**
	 * Insert ou Update d'un Rappel.
	 * 
	 * @param Rappel
	 * @return l'id
	 */
	public static int saveOrUpdate(Rappel rappel) {
		LOG.debug("Requete a la base : Insert ou Update d'un Rappel.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			rappel = em.merge(rappel);
			em.persist(rappel);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return rappel.getId();
	}
	
	/**
	 * Delete de tous les Rappel.
	 * 
	 * @return nb, le nombre d'individu supprime.
	 */
	public static int deleteAllRappel() {
		LOG.debug("Requete a la base : Delete de toutes les Rappel.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Rappel.removeAll");
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
	 * Delete d'une Rappel.
	 * 
	 * @param Rappel
	 */
	public static void delete(Rappel rappel) {
		LOG.debug("Requete a la base : Delete d'un Rappel.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(rappel));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
}
