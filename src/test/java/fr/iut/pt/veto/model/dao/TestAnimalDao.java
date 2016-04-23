/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.animal.Espece;
import fr.iut.pt.veto.model.entitie.animal.Race;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author dimitri
 *
 */
public class TestAnimalDao {

	@BeforeClass
	public static void beforeAll() {
		
		JpaUtils.PERSISTENCE_UNIT = "dev_TU";

		AnimalDao.deleteAllAnimal();

		Client clientTest = new Client("nomTest", "prenomTest");
		int id = IndividuDao.saveOrUpdate(clientTest);
		Race raceTest = new Race("Husky");

		Espece especeTest = new Espece("Chien");
		int id_e = EspeceDao.saveOrUpdate(especeTest);
		raceTest.setEspece(EspeceDao.findEspeceById(id_e));
		int id_r = EspeceDao.saveOrUpdate(raceTest);
		
		Animal a1 = new Animal((Client) IndividuDao.findIndividuById(id));
		a1.setNom("taco");
		a1.setRace(EspeceDao.findRaceById(id_r));

		Animal a2 = new Animal((Client) IndividuDao.findIndividuById(id));

		AnimalDao.saveOrUpdate(a1);
		AnimalDao.saveOrUpdate(a2);
	}

	@AfterClass
	public static void afterAll() {
		AnimalDao.deleteAllAnimal();
		EspeceDao.deleteAllRace();
		EspeceDao.deleteAllEspece();
		IndividuDao.deleteAllIndividu();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(2, AnimalDao.findAllAnimal().size());
		Assert.assertEquals(1, EspeceDao.findAllEspece().size());
		Assert.assertEquals(1, EspeceDao.findAllRace().size());
	}

	@Test
	public void testFindAnimalById() {
		Animal test = AnimalDao.findAnimalById(0);
		Assert.assertEquals(2, AnimalDao.findAllAnimal().size());
	}
	
	@Test
	public void testInsertAndDeleteAnimal() {
		Client clientTest2 = new Client("nomTest2", "prenomTest2");
		Animal a3 = new Animal();
		a3.setClient(clientTest2);

		int id = AnimalDao.saveOrUpdate(a3);

		Assert.assertNotEquals(0, id);
		Assert.assertEquals(3, AnimalDao.findAllAnimal().size());

		AnimalDao.delete(AnimalDao.findAnimalById(id));
		Assert.assertEquals(2, AnimalDao.findAllAnimal().size());
	}
	
	@Test
	public void testFindAnimalByClient() {
		
		Client c = (Client) IndividuDao.findIndividuByNom("nomTest").get(0);
		
		List<Animal> list = AnimalDao.findAnimalByClient(c);
		
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testFindAnimalByRace() {
//		Race r = (Race) EspeceDao.findRaceByNom("Chien");
//		
//		Assert.assertNotNull(r);
//	
//		List<Animal> list = AnimalDao.findAnimalByRace(r);
//		
//		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testFindAnimalByEspece() {
//		Espece e = (Espece) EspeceDao.findEspeceByNom("Husky");
//		
//		Assert.assertNotNull(e);
//	
//		List<Animal> list = AnimalDao.findAnimalByEspece(e);
//		
//		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void testFindAnimalByNom() {
		Animal animal = AnimalDao.findAnimalByNom("taco").get(0);
		
		Assert.assertEquals("nomTest", animal.getClient().getNom());
	}
	
}