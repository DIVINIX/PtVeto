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
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Maladie et Traitement.
 * @author sebastien
 */
public abstract class TraitementDao {

	private static final Logger LOG = Logger.getLogger(TraitementDao.class);

	/**
	 * Recuperation de tout les Maladie.
	 * 
	 * @return maladieList
	 */
	public static List<Maladie> findAllMaladie() {
		LOG.debug("Requete a la base : Recuperation de tout les Maladie.");

		List<Maladie> maladieList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Maladie.findAll");

			maladieList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return maladieList;
	}
	
	/**
	 * Recuperation de tout les Traitement.
	 * 
	 * @return maladieList
	 */
	public static List<Traitement> findAllTraitement() {
		LOG.debug("Requete a la base : Recuperation de tout les Traitement.");

		List<Traitement> traitementList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Traitement.findAll");

			traitementList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return traitementList;
	}
	
	/**
	 * Recuperation d'un Maladie par son id.
	 * 
	 * @return maladie
	 */
	public static Maladie findMaladieById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un Maladie par son id.");

		Maladie maladie = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Maladie.findById");
			query.setParameter("id", id);

			maladie = (Maladie) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Maladie sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return maladie;
	}
	
	/**
	 * Recuperation d'un Traitement par son id.
	 * 
	 * @return traitement
	 */
	public static Traitement findTraitementById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un Traitement par son id.");

		Traitement traitement = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Traitement.findById");
			query.setParameter("id", id);

			traitement = (Traitement) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Traitement sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return traitement;
	}
	
	/**
	 * Recuperation d'un Maladie par son nom.
	 * 
	 * @return Maladie
	 */
	public static Maladie findMaladieByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation d'un Maladie par son nom.");

		Maladie maladie = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Maladie.findByNom");
			query.setParameter("nom", nom);

			maladie = (Maladie) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Maladie sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return maladie;
	}
	
	/**
	 * Recuperation Traitement par son animal.
	 * 
	 * @return traitement
	 */
	public static List<Traitement> findTraitementByAnimal(Animal animal) {
		LOG.debug("Requete a la base : Recuperation Traitement par son animal.");

		List<Traitement> traitementList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Traitement.findByAnimal");
			query.setParameter("id", animal.getId());

			traitementList =  query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return traitementList;
	}
	
	/**
	 * Recuperation Traitement par son maladie.
	 * 
	 * @return traitementList
	 */
	public static List<Traitement> findTraitementByMaladie(Maladie maladie) {
		LOG.debug("Requete a la base : Recuperation Traitement par son maladie.");

		List<Traitement> traitementList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Traitement.findByMaladie");
			query.setParameter("id", maladie.getId());

			traitementList =  query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return traitementList;
	}
	
	/**
	 * Recuperation Traitement par son nom.
	 * 
	 * @return traitementList
	 */
	public static List<Traitement> findTraitementByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation Traitement par son nom.");

		List<Traitement> traitementList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Traitement.findByNom");
			query.setParameter("nom", nom);

			traitementList =  query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return traitementList;
	}
	
	/**
	 * Insert ou Update d'un Maladie.
	 * 
	 * @param Maladie
	 * @return l'id
	 */
	public static int saveOrUpdate(Maladie maladie) {
		LOG.debug("Requete a la base : Insert ou Update d'un Maladie.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			maladie = em.merge(maladie);
			em.persist(maladie);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return maladie.getId();
	}
	
	/**
	 * Insert ou Update d'un Traitement.
	 * 
	 * @param Traitement
	 * @return l'id
	 */
	public static int saveOrUpdate(Traitement traitement) {
		LOG.debug("Requete a la base : Insert ou Update d'un Traitement.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			traitement = em.merge(traitement);
			em.persist(traitement);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return traitement.getId();
	}
	
	/**
	 * Delete de tous les Maladie.
	 * 
	 * @return nb, le nombre Maladie supprime.
	 */
	public static int deleteAllMaladie() {
		LOG.debug("Requete a la base : Delete de toutes les Maladie.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Maladie.removeAll");
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
	 * Delete de tous les Traitement.
	 * 
	 * @return nb, le nombre Traitement supprime.
	 */
	public static int deleteAllTraitement() {
		LOG.debug("Requete a la base : Delete de toutes les Traitement.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Traitement.removeAll");
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
	 * Delete d'une Maladie.
	 * 
	 * @param Maladie
	 */
	public static void delete(Maladie maladie) {
		LOG.debug("Requete a la base : Delete d'un Maladie.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(maladie));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
	/**
	 * Delete d'une Traitement.
	 * 
	 * @param Traitement
	 */
	public static void delete(Traitement traitement) {
		LOG.debug("Requete a la base : Delete d'un Traitement.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(traitement));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
}
