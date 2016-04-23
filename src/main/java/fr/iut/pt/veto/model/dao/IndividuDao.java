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
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Individus, Clients et Salarie.
 * 
 * @author sebastien
 */
public abstract class IndividuDao {

	private static final Logger LOG = Logger.getLogger(IndividuDao.class);

	/**
	 * Recuperation de tout les individus.
	 * 
	 * @return individuList
	 */
	public static List<Individu> findAllIndividu() {
		LOG.debug("Requete a la base : Recuperation de tout les individus.");

		List<Individu> individuList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.findAll");

			individuList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return individuList;
	}

	/**
	 * Recuperation de tout les clients.
	 * 
	 * @return clientList
	 */
	public static List<Client> findAllClient() {
		LOG.debug("Requete a la base : Recuperation de tout les clients.");

		List<Client> clientList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Client.findAll");

			clientList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return clientList;
	}

	/**
	 * Recuperation de tout les salarie.
	 * 
	 * @return clientList
	 */
	public static List<Salarie> findAllSalarie() {
		LOG.debug("Requete a la base : Recuperation de tout les salaries.");

		List<Salarie> clientList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Salarie.findAll");

			clientList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return clientList;
	}

	/**
	 * Insert ou Update d'un individu.
	 * 
	 * Ne devrait être utilise que pour les tests!!!
	 * 
	 * @param individu
	 * @return l'id
	 */
	public static int saveOrUpdate(Individu individu) {
		LOG.debug("Requete a la base : Insert ou Update d'un individu.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			individu = em.merge(individu);
			em.persist(individu);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return individu.getId();
	}

	/**
	 * Delete de tous les Individu.
	 * 
	 * @return nb, le nombre d'individu supprime.
	 */
	public static int deleteAllIndividu() {
		LOG.debug("Requete a la base : Delete de toutes les Individu.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.removeAll");
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
	 * Recuperation d'un individu par son id.
	 * 
	 * @return individu
	 */
	public static Individu findIndividuById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un individu par son id.");

		Individu individu = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.findById");
			query.setParameter("id", id);

			individu = (Individu) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Individu sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return individu;
	}

	/**
	 * Recuperation d'un individu par son nom.
	 * 
	 * @return listIndividu
	 */
	public static List<Individu> findIndividuByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation des individus par leur nom.");

		List<Individu> listIndividu = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.findByNom");
			query.setParameter("nom", nom);

			listIndividu = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return listIndividu;
	}

	/**
	 * Recuperation d'un individu par son prenom.
	 * 
	 * @return listIndividu
	 */
	public static List<Individu> findIndividuByPrenom(String prenom) {
		LOG.debug("Requete a la base : Recuperation des individus par leur prenom.");

		List<Individu> listIndividu = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.findByPrenom");
			query.setParameter("prenom", prenom);

			listIndividu = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return listIndividu;
	}

	/**
	 * Recuperation d'un individu par son telephone.
	 * 
	 * @return listIndividu
	 */
	public static List<Individu> findIndividuByTelephone(String telephone) {
		LOG.debug("Requete a la base : Recuperation des individus par leur telephone.");

		List<Individu> listIndividu = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.findByTelephone");
			query.setParameter("telephone", telephone);

			listIndividu = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return listIndividu;
	}

	/**
	 * Recuperation d'un individu par sa ville.
	 * 
	 * @return listIndividu
	 */
	public static List<Individu> findIndividuByVille(String nom) {
		LOG.debug("Requete a la base : Recuperation d'un individu par sa ville.");

		List<Individu> listIndividu = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Individu.findByVille");
			query.setParameter("nom", nom);

			listIndividu = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return listIndividu;
	}
	
	/**
	 * Recuperation d'un individu par son role.
	 * 
	 * @return listIndividu
	 */
	public static List<Salarie> findIndividuByRole(String intitule) {
		LOG.debug("Requete a la base : Recuperation d'un individu par son role.");

		List<Salarie> listIndividu = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Salarie.findByRole");
			query.setParameter("intitule", intitule);

			listIndividu = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return listIndividu;
	}
	
	/**
	 * Recuperation d'un individu par son Contrat.
	 * 
	 * @return salarie
	 */
	public static Salarie findSalarieByContrat(Contrat contrat) {
		LOG.debug("Requete a la base : Recuperation d'un individu par son Contrat.");

		Salarie salarie = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Salarie.findByContrat");
			query.setParameter("id", contrat.getId());

			salarie = (Salarie) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Individu sous le contrat : "+contrat.getId());
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return salarie;
	}

	/**
	 * Recuperation d'un individu par son identifiant.
	 * 
	 * @return salarie
	 */
	public static Salarie findSalarieByIdentifiant(String identifiant) {
		LOG.debug("Requete a la base : Recuperation d'un individu par son identifiant.");

		Salarie salarie = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Salarie.findByIdentifiant");
			query.setParameter("identifiant", identifiant);

			salarie = (Salarie) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Individu sous l'identifiant : "+identifiant);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return salarie;
	}

	/**
	 * Delete d'un Individu.
	 * 
	 * @param ville
	 */
	public static void delete(Individu individu) {
		LOG.debug("Requete a la base : Delete d'un Individu.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(individu));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}

}
