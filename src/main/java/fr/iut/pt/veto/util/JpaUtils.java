/**
 * 
 */
package fr.iut.pt.veto.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * Classe utilitaire afin de refactorer des procedure de JPA
 * 
 * @author sebastien
 */
public abstract class JpaUtils {

	private static final Logger LOG = Logger.getLogger(JpaUtils.class);

	/**
	 * L'EntityManagerFactory. Ici fonctionnant comme un Singleton, car couteux
	 * a instancier.
	 */
	private static EntityManagerFactory factory = null;

	/**
	 * Disponibles : dev_interne => pointe sur le raspberry pi, en ip interne au
	 * reseau de chez seb. dev_externe => pointe sur le raspberry pi, en dns
	 * externe. dev_TU => pointe vers une base local en create-drop, uniquement
	 * pour les TU.
	 */
	public static String PERSISTENCE_UNIT = "dev_TU";

	/**
	 * L'EntityManagerFactory est lourd, c'est pour celà qu'on le place ici,
	 * comme un singleton
	 * 
	 * @return factory
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		LOG.debug("Recuperation de l'EntityManagerFactory");
		if (factory == null) {
			LOG.debug("Creation de l'EntityManagerFactory");
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		} else {
			LOG.debug("L'EntityManagerFactory est deja existante");
		}
		return factory;
	}

	/**
	 * Clot l'EntityManagerFactory si il existe.
	 */
	public static void closeEntityManagerFactory() {
		LOG.debug("Fermeture de l'EntityManagerFactory");
		if (factory != null) {
			factory.close();
			factory = null;
		}
	}

	/**
	 * Pour savoir si la factory est cree ou pas. Sert surtout pour les TU, peut
	 * d'utilite sinon.
	 * 
	 * @return boolean
	 */
	public static boolean isSetEntityManagerFactory() {
		return (factory != null);
	}
}
