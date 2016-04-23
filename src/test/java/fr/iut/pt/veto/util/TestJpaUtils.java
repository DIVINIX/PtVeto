/**
 * 
 */
package fr.iut.pt.veto.util;

import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author sebastien
 *
 */
public class TestJpaUtils {
	
	@BeforeClass
	public static void beforeAll() {
		JpaUtils.PERSISTENCE_UNIT = "dev_TU";
	}

	@Test
	public void TestEntityManagerFactory() {
		EntityManagerFactory factory = JpaUtils.getEntityManagerFactory();
		
		Assert.assertTrue(factory.isOpen());
		Assert.assertTrue(JpaUtils.isSetEntityManagerFactory());

		JpaUtils.closeEntityManagerFactory();
		
		Assert.assertFalse(factory.isOpen());		
		Assert.assertFalse(JpaUtils.isSetEntityManagerFactory());
	}
	
}
