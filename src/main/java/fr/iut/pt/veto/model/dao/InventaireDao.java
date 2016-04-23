/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.inventaire.Vente;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Produit ainsi que les Vente.
 * @author sebastien
 */
public abstract class InventaireDao {

	private static final Logger LOG = Logger.getLogger(InventaireDao.class);

	/**
	 * Recuperation de tout les produits.
	 * 
	 * @return produitList
	 */
	public static List<Produit> findAllProduit() {
		LOG.debug("Requete a la base : Recuperation de tout les produits.");

		List<Produit> produitList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Produit.findAll");

			produitList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return produitList;
	}
	
	/**
	 * Recuperation de tout les Vente.
	 * 
	 * @return venteList
	 */
	public static List<Vente> findAllVente() {
		LOG.debug("Requete a la base : Recuperation de tout les Vente.");

		List<Vente> venteList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Vente.findAll");

			venteList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return venteList;
	}
	
	/**
	 * Insert ou Update d'un produit.
	 * 
	 * @param produit
	 * @return l'id
	 */
	public static int saveOrUpdate(Produit produit) {
		LOG.debug("Requete a la base : Insert ou Update d'un produit.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			produit = em.merge(produit);
			em.persist(produit);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return produit.getId();
	}
	
	/**
	 * Insert ou Update d'une Vente.
	 * 
	 * @param vente
	 * @return l'id
	 */
	public static int saveOrUpdate(Vente vente) {
		LOG.debug("Requete a la base : Insert ou Update d'une Vente.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			vente = em.merge(vente);
			em.persist(vente);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return vente.getId();
	}
	
	/**
	 * Delete de tous les produits.
	 * 
	 * @return nb, le nombre d'individu supprime.
	 */
	public static int deleteAllProduit() {
		LOG.debug("Requete a la base : Delete de toutes les produits.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Produit.removeAll");
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
	 * Delete de tous les Vente.
	 * 
	 * @return nb, le nombre Vente supprime.
	 */
	public static int deleteAllVente() {
		LOG.debug("Requete a la base : Delete de toutes les Vente.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Vente.removeAll");
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
	 * Recuperation d'un v par son id.
	 * 
	 * @return produit
	 */
	public static Produit findProduitById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un Produit par son id.");

		Produit produit = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Produit.findById");
			query.setParameter("id", id);
			
			produit = (Produit) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Produit sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return produit;
	}
	
	/**
	 * Recuperation d'un Vente par son id.
	 * 
	 * @return Vente
	 */
	public static Vente findVenteById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un Vente par son id.");

		Vente vente = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Vente.findById");
			query.setParameter("id", id);
			
			vente = (Vente) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Vente sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return vente;
	}
	
	/**
	 * Recuperation d'un produit par son nom.
	 * 
	 * @return produitList
	 */
	public static Produit findProduitByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation des produit par leur nom.");

		Produit produit = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Produit.findByNom");
			query.setParameter("nom", nom);

			produit = (Produit) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Produit sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return produit;
	}
	
	/**
	 * Recuperation d'un produit par son prix.
	 * 
	 * @return produitList
	 */
	public static List<Produit> findProduitByPrix(Double prix_reel) {
		LOG.debug("Requete a la base : Recuperation des produit par leur prix.");

		List<Produit> produitList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Produit.findByPrix");
			query.setParameter("prix_reel", prix_reel);

			produitList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return produitList;
	}
	
	/**
	 * Recuperation d'une liste de vente par son Produit.
	 * 
	 * @return venteList
	 */
	public static List<Vente> findVenteByProduit(Produit produit) {
		LOG.debug("Requete a la base : Recuperation d'une liste de vente par son Produit.");

		List<Vente> venteList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Vente.findByProduit");
			query.setParameter("id", produit.getId());

			venteList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return venteList;
	}
	
	/**
	 * Recuperation d'une liste de vente par son Client.
	 * 
	 * @return venteList
	 */
	public static List<Vente> findVenteByClient(Client client) {
		LOG.debug("Requete a la base : Recuperation d'une liste de vente par son Produit.");

		List<Vente> venteList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Vente.findByClient");
			query.setParameter("id", client.getId());

			venteList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return venteList;
	}
	
	/**
	 * Recuperation d'une liste de vente par son Salarie.
	 * 
	 * @return venteList
	 */
	public static List<Vente> findVenteBySalarie(Salarie salarie) {
		LOG.debug("Requete a la base : Recuperation d'une liste de vente par son Salarie.");

		List<Vente> venteList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Vente.findBySalarie");
			query.setParameter("id", salarie.getId());

			venteList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return venteList;
	}
	
	/**
	 * Delete d'un produit.
	 * 
	 * @param produit
	 */
	public static void delete(Produit produit) {
		LOG.debug("Requete a la base : Delete d'un produit.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(produit));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
	/**
	 * Delete d'une Vente.
	 * 
	 * @param Vente
	 */
	public static void delete(Vente vente) {
		LOG.debug("Requete a la base : Delete d'une Vente.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(vente));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
}
