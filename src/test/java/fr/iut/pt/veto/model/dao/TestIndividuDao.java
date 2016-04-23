/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.geo.Pays;
import fr.iut.pt.veto.model.entitie.geo.Ville;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Individu;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author sebastien
 *
 */
public class TestIndividuDao {
	
	public static final int NB_INDIVIDU = 4;
	public static final int NB_CLIENT = 1;
	public static final int NB_SALARIE = 1;

	@BeforeClass
	public static void beforeAll() {
		
		JpaUtils.PERSISTENCE_UNIT = "dev_TU";

		GeoDao.deleteAllVille();
		GeoDao.deleteAllPays();
		IndividuDao.deleteAllIndividu();

		Pays p1 = new Pays("pays1");
		GeoDao.saveOrUpdate(p1);
		Ville v1 = new Ville(GeoDao.findPaysByName("pays1"), "ville1", "00001");
		GeoDao.saveOrUpdate(v1);
		
		Individu individu1 = new Individu("n1", "p1");
		Individu individu2 = new Individu();
		individu2.setNom("n2");
		individu2.setPrenom("p2");
		
		Client cl1 = new Client("cn1","cp1");
		cl1.setVille(GeoDao.findVilleByName("ville1"));
		
		Salarie sa1 = new Salarie("sn1","sp1");
		sa1.setIdentifiant("si1");
		sa1.setPassword("pass1");
		sa1.setTelephone("0505050505");
		
		IndividuDao.saveOrUpdate(individu1);
		IndividuDao.saveOrUpdate(individu2);
		
		IndividuDao.saveOrUpdate(cl1);
		
		IndividuDao.saveOrUpdate(sa1);
	}

	@AfterClass
	public static void afterAll() {
		IndividuDao.deleteAllIndividu();
		GeoDao.deleteAllVille();
		GeoDao.deleteAllPays();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testFindAllIndividus() {
		Assert.assertEquals(NB_INDIVIDU, IndividuDao.findAllIndividu().size());
		Assert.assertEquals(NB_CLIENT, IndividuDao.findAllClient().size());
		Assert.assertEquals(NB_SALARIE, IndividuDao.findAllSalarie().size());
	}
	
	@Test
	public void testInstanceOfClient() {
		Individu i = IndividuDao.findIndividuByNom("cn1").get(0);
		
		Assert.assertTrue(i instanceof Client);
		Assert.assertNotNull(((Client) i).getDateDebut());
	}
	
	@Test
	public void testInstanceOfSalarie() {
		Individu i = IndividuDao.findIndividuByNom("sn1").get(0);
		
		Assert.assertTrue(i instanceof Salarie);
		Assert.assertEquals("si1",((Salarie) i).getIdentifiant());
	}
	
	@Test
	public void testInsertAndDeleteIndividu() {
		Individu test = new Individu("n3","p3");
		test.setVille(GeoDao.findVilleByName("ville1"));

		int id = IndividuDao.saveOrUpdate(test);
		
		Assert.assertNotEquals(0, id);
		Assert.assertEquals(NB_INDIVIDU+1, IndividuDao.findAllIndividu().size());

		Individu testRes = IndividuDao.findIndividuById(id);
		
		Assert.assertEquals(id, testRes.getId());
		Assert.assertEquals("ville1", testRes.getVille().getNom());

		IndividuDao.delete(testRes);
		
		Ville v = GeoDao.findVilleByName("ville1");
		Assert.assertNotNull(v);

		Assert.assertEquals(NB_INDIVIDU, IndividuDao.findAllIndividu().size());
	}
	
	@Test
	public void testInsertAndDeleteClient() {
		Client test = new Client("c3","p3");
		test.setVille(GeoDao.findVilleByName("ville1"));

		int id = IndividuDao.saveOrUpdate(test);
		
		Assert.assertNotEquals(0, id);
		Assert.assertEquals(NB_INDIVIDU+1, IndividuDao.findAllIndividu().size());
		Assert.assertEquals(NB_CLIENT+1, IndividuDao.findAllClient().size());

		Client testRes = (Client) IndividuDao.findIndividuById(id);
		
		Assert.assertEquals(id, testRes.getId());
		Assert.assertEquals("ville1", testRes.getVille().getNom());

		IndividuDao.delete(testRes);
		
		Ville v = GeoDao.findVilleByName("ville1");
		Assert.assertNotNull(v);

		Assert.assertEquals(NB_INDIVIDU, IndividuDao.findAllIndividu().size());
		Assert.assertEquals(NB_CLIENT, IndividuDao.findAllClient().size());
	}
	
	@Test
	public void testFindIndividuBy() {
		Assert.assertEquals("cp1", IndividuDao.findIndividuByPrenom("cp1").get(0).getPrenom());
		
		Assert.assertEquals("cp1", IndividuDao.findIndividuByVille("ville1").get(0).getPrenom());
		
		Assert.assertEquals("sp1", IndividuDao.findIndividuByTelephone("0505050505").get(0).getPrenom());
		
		Assert.assertEquals("sp1", IndividuDao.findSalarieByIdentifiant("si1").getPrenom());
	}
}
