package fr.iut.pt.veto.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import fr.iut.pt.veto.model.entitie.rh.Absence;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.model.entitie.rh.TypeAbsence;
import fr.iut.pt.veto.util.JpaUtils;

public abstract class RhDao {
	private static final Logger LOG = Logger.getLogger(RhDao.class);
	
	/*
	 * *************
	 * ROLE ********
	 * *************
	 */
	
	/**
	 * Insert ou Update d'un role.
	 * 
	 * @param role
	 * @return l'id
	 * Si le l'insert ou l'update échoue, la méthode renvoie 0.
	 */
	public static int saveOrUpdate(Role role) {
		LOG.debug("Requete a la base : Insert ou Update d'un role.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.persist(role);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return role.getId();
	}
	
	/**
	 * Delete d'un Role.
	 * 
	 * @param role
	 */
	public static void delete(Role role) {
		LOG.debug("Requete a la base : Delete d'un role.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(role));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
	/**
	 * Recuperation de tout les role.
	 * 
	 * @return roleList
	 */
	public static List<Role> findAllRole() {
		LOG.debug("Requete a la base : Recuperation de tout les rôles.");

		List<Role> roleList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Role.findAll");

			roleList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return roleList;
	}
	
	/**
	 * Delete de toutes les Role.
	 * 
	 * @return nb, le nombre de role supprime.
	 */
	public static int deleteAllRole() {
		LOG.debug("Requete a la base : Delete de toutes les roles.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Role.removeAll");
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
	 * Recuperation d'un role par son id.
	 * 
	 * @return role
	 */
	public static Role findRoleById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un role par son id.");

		Role role = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Role.findById");
			query.setParameter("id", id);

			role = (Role) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun role sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return role;
	}
	
	/**
	 * Recuperation d'un role par son nom.
	 * 
	 * @return role
	 */
	public static Role findRoleByName(String nom) {
		LOG.debug("Requete a la base : Recuperation d'un role par son nom.");

		Role role = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Role.findByName");
			query.setParameter("intitule", nom);

			role = (Role) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun role sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return role;
	}
	
	/*
	 * *************
	 * CONTRAT *****
	 * *************
	 */
	
	/**
	 * Insert ou Update d'un contrat.
	 * 
	 * @param contrat
	 * @return l'id
	 * Si le l'insert ou l'update échoue, la méthode renvoie 0.
	 */
	public static int saveOrUpdate(Contrat contrat) {
		LOG.debug("Requete a la base : Insert ou Update d'un contrat.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.persist(contrat);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return contrat.getId();
	}
	
	/**
	 * Delete d'un Contrat.
	 * 
	 * @param contrat
	 */
	public static void delete(Contrat contrat) {
		LOG.debug("Requete a la base : Delete d'un contrat.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(contrat));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
	/**
	 * Recuperation de tout les contrat.
	 * 
	 * @return contratList
	 */
	public static List<Contrat> findAllContrat() {
		LOG.debug("Requete a la base : Recuperation de tout les rôles.");

		List<Contrat> contratList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Contrat.findAll");

			contratList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return contratList;
	}
	
	/**
	 * Delete de toutes les Contrat.
	 * 
	 * @return nb, le nombre de contrat supprime.
	 */
	public static int deleteAllContrat() {
		LOG.debug("Requete a la base : Delete de toutes les contrats.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Contrat.removeAll");
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
	 * Recuperation d'un contrat par son id.
	 * 
	 * @return contrat
	 */
	public static Contrat findContratById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un contrat par son id.");

		Contrat contrat = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Contrat.findById");
			query.setParameter("id", id);

			contrat = (Contrat) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun contrat sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return contrat;
	}
	
	/*
	 * *************
	 * ABSENCE *****
	 * *************
	 */
	
	/**
	 * Insert ou Update d'un absence.
	 * 
	 * @param absence
	 * @return l'id
	 * Si le l'insert ou l'update échoue, la méthode renvoie 0.
	 */
	public static int saveOrUpdate(Absence absence) {
		LOG.debug("Requete a la base : Insert ou Update d'un absence.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			
			absence = em.merge(absence);
			em.persist(absence);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return absence.getId();
	}
	
	/**
	 * Delete d'un Absence.
	 * 
	 * @param absence
	 */
	public static void delete(Absence absence) {
		LOG.debug("Requete a la base : Delete d'un absence.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(absence));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
	/**
	 * Recuperation de tout les absence.
	 * 
	 * @return absenceList
	 */
	public static List<Absence> findAllAbsence() {
		LOG.debug("Requete a la base : Recuperation de tout les rôles.");

		List<Absence> absenceList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Absence.findAll");

			absenceList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return absenceList;
	}
	
	/**
	 * Delete de toutes les Absence.
	 * 
	 * @return nb, le nombre de absence supprime.
	 */
	public static int deleteAllAbsence() {
		LOG.debug("Requete a la base : Delete de toutes les absences.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Absence.removeAll");
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
	 * Recuperation d'un absence par son id.
	 * 
	 * @return absence
	 */
	public static Absence findAbsenceById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un absence par son id.");

		Absence absence = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Absence.findById");
			query.setParameter("id", id);

			absence = (Absence) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun absence sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return absence;
	}
	
	/**
	 * Recuperation des absences qui ont le type passer en paramètre.
	 * @param TypeAbsence
	 * @return absence
	 */
	public static List<Absence> findAbsenceByTypeAbsence(TypeAbsence typeAbsence) {
		LOG.debug("Requete a la base : Recuperation des absences qui ont pour type typeAbsence.");

		List<Absence> absence = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Absence.findAbsenceByTypeAbsence");
			query.setParameter("id", typeAbsence.getId());

			absence = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return absence;
	}
	
	/**
	 * Recuperation des absences liées à un contrat.
	 * @param Contrat
	 * @return absence
	 */
	public static List<Absence> findAbsenceByContrat(Contrat contrat) {
		LOG.debug("Requete a la base : Recuperation des absences pour un même contrat");

		List<Absence> absence = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Absence.findAbsenceByContrat");
			query.setParameter("id", contrat.getId());

			absence = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return absence;
	}
	
	/*
	 * *************
	 * TYPEABSENCE *
	 * *************
	 */
	
	/**
	 * Insert ou Update d'un typeAbsence.
	 * 
	 * @param typeAbsence
	 * @return l'id
	 * Si le l'insert ou l'update échoue, la méthode renvoie 0.
	 */
	public static int saveOrUpdate(TypeAbsence typeAbsence) {
		LOG.debug("Requete a la base : Insert ou Update d'un typeAbsence.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.persist(typeAbsence);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");

		return typeAbsence.getId();
	}
	
	/**
	 * Delete d'un TypeAbsence.
	 * 
	 * @param typeAbsence
	 */
	public static void delete(TypeAbsence typeAbsence) {
		LOG.debug("Requete a la base : Delete d'un typeAbsence.");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(typeAbsence));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
	}
	
	/**
	 * Recuperation de tout les typeAbsence.
	 * 
	 * @return typeAbsenceList
	 */
	public static List<TypeAbsence> findAllTypeAbsence() {
		LOG.debug("Requete a la base : Recuperation de tout les rôles.");

		List<TypeAbsence> typeAbsenceList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("TypeAbsence.findAll");

			typeAbsenceList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return typeAbsenceList;
	}
	
	/**
	 * Delete de toutes les TypeAbsence.
	 * 
	 * @return nb, le nombre de typeAbsence supprime.
	 */
	public static int deleteAllTypeAbsence() {
		LOG.debug("Requete a la base : Delete de toutes les typeAbsences.");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("TypeAbsence.removeAll");
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
	 * Recuperation d'un typeAbsence par son id.
	 * 
	 * @return typeAbsence
	 */
	public static TypeAbsence findTypeAbsenceById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un typeAbsence par son id.");

		TypeAbsence typeAbsence = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("TypeAbsence.findById");
			query.setParameter("id", id);

			typeAbsence = (TypeAbsence) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun typeAbsence sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return typeAbsence;
	}
	
	/**
	 * Recuperation d'un typeAbsence par son nom.
	 * 
	 * @return typeAbsence
	 */
	public static TypeAbsence findTypeAbsenceByName(String nom) {
		LOG.debug("Requete a la base : Recuperation d'un typeAbsence par son nom.");

		TypeAbsence typeAbsence = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("TypeAbsence.findByName");
			query.setParameter("intitule", nom);

			typeAbsence = (TypeAbsence) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun typeAbsence sous le nom : "+nom);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base.");
		return typeAbsence;
	}
}
