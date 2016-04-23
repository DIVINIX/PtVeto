/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les villes ainsi que les pays.
 * 
 * @author sebastien
 */
public abstract class GeoDao {

	private static final Logger LOG = Logger.getLogger(GeoDao.class);

	/**
	 * Recuperation de tout les pays.
	 * 
	 * @return paysList
	 */
	public static List<Pays> findAllPays() {
		LOG.debug("Requete a la base : Recuperation de tout les pays.");

		List<Pays> paysList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Pays.findAll");

			paysList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return paysList;
	}

	/**
	 * Recuperation de toutes les villes.
	 * 
	 * @return paysList
	 */
	public static List<Ville> findAllVille() {
		LOG.debug("Requete a la base : Recuperation de toutes les villes.");

		List<Ville> villeList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Ville.findAll");

			villeList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return villeList;
	}

	/**
	 * Recuperation d'un pays par son id.
	 * 
	 * @return pays
	 */
	public static Pays findPaysById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un pays par son id.");

		Pays pays = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Pays.findById");
			query.setParameter("id", id);

			pays = (Pays) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Pays sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return pays;
	}

	/**
	 * Recuperation d'un pays par son nom.
	 * 
	 * @return pays
	 */
	public static Pays findPaysByName(String nom) {
		LOG.debug("Requete a la base : Recuperation d'un pays par son nom.");

		Pays pays = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Pays.findByName");
			query.setParameter("nom", nom);

			pays = (Pays) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Pays sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return pays;
	}

	/**
	 * Recuperation d'une ville par son id.
	 * 
	 * @return paysList
	 */
	public static Ville findVilleById(int id) {
		LOG.debug("Requete a la base : Recuperation d'une ville par son id.");

		Ville ville = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Ville.findById");
			query.setParameter("id", id);

			ville = (Ville) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucune Ville sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return ville;
	}
	
	/**
	 * Recuperation d'une ville par son nom.
	 * 
	 * @return paysList
	 */
	public static Ville findVilleByName(String nom) {
		LOG.debug("Requete a la base : Recuperation d'une ville par son nom.");

		Ville ville = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Ville.findByName");
			query.setParameter("nom", nom);

			ville = (Ville) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucune Ville sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return ville;
	}

	/**
	 * Recuperation d'une ville par son code postal.
	 * 
	 * @return paysList
	 */
	public static Ville findVilleByPostal(String postal) {
		LOG.debug("Requete a la base : Recuperation d'une ville par son code postal.");

		Ville ville = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Ville.findByPostal");
			query.setParameter("codePostale", postal);

			ville = (Ville) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucune Ville sous le CP : "+postal);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return ville;
	}

	/**
	 * Recuperation de toutes les villes par leur pays.
	 * 
	 * @return paysList
	 */
	public static List<Ville> findAllVilleByPays(Pays pays) {
		LOG.debug("Requete a la base : Recuperation de toutes les villes par leur pays.");

		List<Ville> villeList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Ville.findByPays");
			query.setParameter("nom", pays.getNom());

			villeList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}

		LOG.debug("Fin de requete a la base.");
		return villeList;
	}

	/**
	 * Insert ou Update d'un pays.
	 * 
	 * @param pays
	 * @return l'id
	 */
	public static int saveOrUpdate(Pays pays) {
		LOG.debug("Requete a la base : Insert ou Update d'un pays.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.persist(pays);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return pays.getId();
	}

	/**
	 * Insert ou Update d'une ville.
	 * 
	 * @param ville
	 * @return l'id
	 */
	public static int saveOrUpdate(Ville ville) {
		LOG.debug("Requete a la base : Insert ou Update d'une ville.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			ville = em.merge(ville);
			em.persist(ville);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return ville.getId();
	}

	/**
	 * Delete d'un Pays.
	 * 
	 * @param pays
	 */
	public static void delete(Pays pays) {
		LOG.debug("Requete a la base : Delete d'un Pays.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(pays));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}

	/**
	 * Delete de tout les Pays.
	 * 
	 * @return nb, le nombre de pays supprime.
	 */
	public static int deleteAllPays() {
		LOG.debug("Requete a la base : Delete de tout les Pays.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Pays.removeAll");
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
	 * Delete d'une Ville.
	 * 
	 * @param ville
	 */
	public static void delete(Ville ville) {
		LOG.debug("Requete a la base : Delete d'une Ville.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(ville));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}

	/**
	 * Delete de toutes les Ville.
	 * 
	 * @return nb, le nombre de villes supprime.
	 */
	public static int deleteAllVille() {
		LOG.debug("Requete a la base : Delete de toutes les Ville.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Ville.removeAll");
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
	
	
}
