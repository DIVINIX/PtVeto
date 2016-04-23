/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.traitement.Rappel;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author sebastien
 *
 */
public class TestRappelDao {

	@BeforeClass
	public static void beforeAll() {

		JpaUtils.PERSISTENCE_UNIT = "dev_TU";

		Client c1 = new Client("c1", "c1");
		Animal a1 = new Animal(c1);
		a1.setNom("a1");
		
		Client c2 = new Client("c2", "c2");
		Animal a2 = new Animal(c2);
		a2.setNom("a2");
		
		AnimalDao.saveOrUpdate(a1);
		AnimalDao.saveOrUpdate(a2);
		
	}

	@AfterClass
	public static void afterAll() {
		
		RappelDao.deleteAllRappel();
		AnimalDao.deleteAllAnimal();
		IndividuDao.deleteAllIndividu();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testInsertAndDeleteRappel() {
		Rappel rappel = new Rappel();
		rappel.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		rappel.setDate(new Date());
		
		int id = RappelDao.saveOrUpdate(rappel);
		
		Assert.assertEquals(1, RappelDao.findAllRappel().size());
		
		Rappel result = RappelDao.findRappelById(id);
		
		Assert.assertNotNull(result);
		Assert.assertEquals("c1", result.getAnimal().getClient().getNom());
		
		RappelDao.delete(result);
		
		Assert.assertEquals(0, RappelDao.findAllRappel().size());
	}
	
	@Test
	public void testFindRappelByDossier() {
		Rappel rappel1 = new Rappel();
		rappel1.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		rappel1.setDate(new Date());
		
		Rappel rappel2 = new Rappel();
		rappel2.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		rappel2.setDate(new Date());
		
		RappelDao.saveOrUpdate(rappel1);
		RappelDao.saveOrUpdate(rappel2);
		
		Assert.assertEquals(2, RappelDao.findRappelByAnimal(AnimalDao.findAnimalByNom("a1").get(0)).size());
	
		RappelDao.deleteAllRappel();
	}
}
