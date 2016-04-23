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
import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * Dao pour les Animal.
 * 
 * @author dimitri
 */
public abstract class AnimalDao {
	
	private static final Logger LOG = Logger.getLogger(AnimalDao.class);

	/**
	 * Recuperation de tout les animaux
	 * 
	 * @return animalList
	 */
	public static List<Animal> findAllAnimal() {
		LOG.debug("Requete a la base : Recuperation de tout les animaux");

		List<Animal> animalList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.findAll");

			animalList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return animalList;
	}

	/**
	 * Insert ou Update d'un animal
	 * 
	 * @param animal
	 * @return identifiant de l'animal
	 */
	public static int saveOrUpdate(Animal animal) {
		LOG.debug("Requete a la base : Insert ou Update d'un animal");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			animal = em.merge(animal);
			em.persist(animal);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");

		return animal.getId();
	}

	/**
	 * Suppression de tous les animaux de la base
	 * 
	 * @return nb le nombre d'animaux supprimés
	 */
	public static int deleteAllAnimal() {
		LOG.debug("Requete a la base : Delete de tous les animaux");

		EntityManager em = null;
		int nb = 0;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.removeAll");
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
	 * Recuperation d'un animal par son id
	 * 
	 * @return animal correspondant
	 */
	public static Animal findAnimalById(int id) {
		LOG.debug("Requete a la base : Recuperation d'un animal par son id");

		Animal animal = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.findById");
			query.setParameter("id", id);

			animal = (Animal) query.getSingleResult();

			em.getTransaction().commit();
		} catch (NoResultException ex) {
			LOG.debug("Aucun Animal sous l'id : "+id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return animal;
	}
	
	/**
	 * Recuperation d'un animal par son nom
	 * 
	 * @return animalList
	 */
	public static List<Animal> findAnimalByNom(String nom) {
		LOG.debug("Requete a la base : Recuperation d'un animal par son nom");

		List<Animal> animalList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.findByNom");
			query.setParameter("nom", nom);

			animalList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return animalList;
	}

	/**
	 * Recuperation des Animal par son client
	 * 
	 * @return animal correspondant
	 */
	public static List<Animal> findAnimalByClient(Client client) {
		LOG.debug("Requete a la base : Recuperation d'un animal par l'id de son maitre");

		List<Animal> animalList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.findByClient");
			query.setParameter("id", client.getId());

			animalList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return animalList;
	}
	
	/**
	 * Recuperation des Animal par sa race
	 * 
	 * @return animal correspondant
	 */
	public static List<Animal> findAnimalByRace(Race race) {
		LOG.debug("Requete a la base : Recuperation d'un animal par sa race");

		List<Animal> animalList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.findByRace");
			query.setParameter("id", race.getId());

			animalList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return animalList;
	}
	
	/**
	 * Recuperation des Animal par son espece
	 * 
	 * @return espece correspondant
	 */
	public static List<Animal> findAnimalByEspece(Espece espece) {
		LOG.debug("Requete a la base : Recuperation d'un animal par son espece");

		List<Animal> animalList = null;
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			Query query = em.createNamedQuery("Animal.findByEspece");
			query.setParameter("id", espece.getId());

			animalList = query.getResultList();

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
		return animalList;
	}

	/**
	 * Delete d'un animal
	 * 
	 * @param animal
	 */
	public static void delete(Animal animal) {
		LOG.debug("Requete a la base : Suppression d'un Animal");

		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(animal));

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		LOG.debug("Fin de requete a la base");
	}
}