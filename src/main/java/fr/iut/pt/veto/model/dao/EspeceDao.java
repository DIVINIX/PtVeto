/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Espece et Race.
 * 
 * @author dimitri
 */
public abstract class EspeceDao {
	
	private static final Logger LOG = Logger.getLogger(EspeceDao.class);

	/**
	 * Recuperation de toutes les especes
	 * 
	 * @return especeList
	 */
	public static List<Espece> findAllEspece() {
		LOG.debug("Requete a la base : Recuperation de toutes les especes");

		List<Espece> especeList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Espece.findAll");

			especeList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return especeList;
	}
	
	/**
	 * Recuperation de toutes les races
	 * 
	 * @return raceList
	 */
	public static List<Race> findAllRace() {
		LOG.debug("Requete a la base : Recuperation de toutes les races");

		List<Race> raceList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Race.findAll");

			raceList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return raceList;
	}

	/**
	 * Insert ou Update d'une espece
	 * 
	 * @param espece
	 * @return identifiant de l'espece
	 */
	public static int saveOrUpdate(Espece espece) {
		LOG.debug("Requete a la base : Insert ou Update d'une espece");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			espece = em.merge(espece);
			em.persist(espece);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");

		return espece.getId();
	}
	
	/**
	 * Insert ou Update d'une race
	 * 
	 * @param race
	 * @return identifiant de la race
	 */
	public static int saveOrUpdate(Race race) {
		LOG.debug("Requete a la base : Insert ou Update d'une race");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			race = em.merge(race);
			em.persist(race);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");

		return race.getId();
	}

	/**
	 * Suppression de toutes les especes de la base
	 * 
	 * @return nb le nombre d'especes supprimées
	 */
	public static int deleteAllEspece() {
		LOG.debug("Requete a la base : Delete de toutes les especes");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Espece.removeAll");
			nb = query.executeUpdate();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		LOG.debug("Fin de requete a la base");
		return nb;
	}

	/**
	 * Suppression de toutes les races de la base
	 * 
	 * @return nb le nombre de races supprimées
	 */
	public static int deleteAllRace() {
		LOG.debug("Requete a la base : Delete de toutes les races");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Race.removeAll");
			nb = query.executeUpdate();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return nb;
	}
	
	/**
	 * Recuperation d'une espece par son id
	 * 
	 * @return espece correspondant
	 */
	public static Espece findEspeceById(int id) {
		LOG.debug("Requete a la base : Recuperation d'une espece par son id");

		Espece espece = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Espece.findById");
			query.setParameter("id", id);

			espece = (Espece) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Espece sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return espece;
	}
	
	/**
	 * Recuperation d'une race par son id
	 * 
	 * @return espece correspondant
	 */
	public static Race findRaceById(int id) {
		LOG.debug("Requete a la base : Recuperation d'une espece par son id");

		Race race = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Race.findById");
			query.setParameter("id", id);

			race = (Race) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucune Race sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return race;
	}
	
	/**
	 * Recuperation d'une espece par son nom
	 * 
	 * @return espece
	 */
	public static Espece findEspeceByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation d'une espece par son nom");

		Espece espece = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Espece.findByNom");
			query.setParameter("nom", nom);

			espece = (Espece) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucune Espece sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return espece;
	}
	
	/**
	 * Recuperation d'une race par son nom
	 * 
	 * @return race
	 */
	public static Race findRaceByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation d'une race par son nom");

		Race race = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Race.findByNom");
			query.setParameter("nom", nom);

			race = (Race) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucune Race sous le nom : "+nom);
		}  finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return race;
	}


	/**
	 * Recuperation des race par leur espece
	 * 
	 * @return raceList 
	 */
	public static List<Race> findRaceByEspece(Espece espece) {
		LOG.debug("Requete a la base : Recuperation des race par leur espece.");

		List<Race> raceList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Race.findByEspece");
			query.setParameter("id", espece.getId());

			raceList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return raceList;
	}

	/**
	 * Delete d'une espece
	 * 
	 * @param espece
	 */
	public static void deleteEspece(Espece espece) {
		LOG.debug("Requete a la base : Suppression d'une Espece");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(espece));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
	}
	
	/**
	 * Delete d'une race
	 * 
	 * @param race
	 */
	public static void deleteRace(Race race) {
		LOG.debug("Requete a la base : Suppression d'une Race");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(race));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
	}
}
