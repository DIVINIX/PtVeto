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
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author sebastien
 *
 */
public class TestGeoDao {

	@BeforeClass
	public static void beforeAll() {
		
		JpaUtils.PERSISTENCE_UNIT = "dev_TU";

		GeoDao.deleteAllVille();
		GeoDao.deleteAllPays();

		Pays p1 = new Pays("pays1");
		Pays p2 = new Pays();
		p2.setNom("pays2");
		GeoDao.saveOrUpdate(p1);
		GeoDao.saveOrUpdate(p2);

		Ville v1 = new Ville(GeoDao.findPaysByName("pays1"), "ville1", "00001");
		Ville v2 = new Ville(GeoDao.findPaysByName("pays1"), "ville2", "00002");
		Ville v3 = new Ville();
		v3.setPays(GeoDao.findPaysByName("pays2"));
		v3.setNom("ville3");
		v3.setCodePostale("00003");
		GeoDao.saveOrUpdate(v1);
		GeoDao.saveOrUpdate(v2);
		GeoDao.saveOrUpdate(v3);
	}

	@AfterClass
	public static void afterAll() {
		GeoDao.deleteAllVille();
		GeoDao.deleteAllPays();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testFindAllPays() {
		Assert.assertEquals(2, GeoDao.findAllPays().size());
	}

	@Test
	public void testInsertAndDeletePays() {
		Pays pays = new Pays("pays3");

		int id = GeoDao.saveOrUpdate(pays);

		Assert.assertNotEquals(0, id);
		Assert.assertEquals(3, GeoDao.findAllPays().size());

		GeoDao.delete(GeoDao.findPaysById(id));

		Assert.assertEquals(2, GeoDao.findAllPays().size());
	}

	@Test
	public void testFindPaysByName() {
		Pays pays = GeoDao.findPaysByName("pays2");

		Assert.assertNotNull(pays);
		Assert.assertEquals("pays2", pays.getNom());
	}

	@Test
	public void testFindAllVille() {
		Assert.assertEquals(3, GeoDao.findAllVille().size());
	}
	
	@Test
	public void testInsertAndDeleteVille() {
		Ville ville = new Ville(GeoDao.findPaysByName("pays2"), "ville4", "00004");

		int id = GeoDao.saveOrUpdate(ville);

		Assert.assertNotEquals(0, id);
		Assert.assertEquals(4, GeoDao.findAllVille().size());

		GeoDao.delete(GeoDao.findVilleById(id));

		Assert.assertEquals(2, GeoDao.findAllPays().size());
		Assert.assertEquals(3, GeoDao.findAllVille().size());
	}
	
	@Test
	public void testFindVilleByName() {
		Ville ville = GeoDao.findVilleByName("ville2");

		Assert.assertNotNull(ville);
		Assert.assertEquals("ville2", ville.getNom());
		Assert.assertEquals("00002", ville.getCodePostale());
		Assert.assertEquals("pays1", ville.getPays().getNom());
	}
	
	@Test
	public void testFindVilleByPostal() {
		Ville ville = GeoDao.findVilleByPostal("00003");

		Assert.assertNotNull(ville);
		Assert.assertEquals("ville3", ville.getNom());
	}
	
	@Test
	public void testFindAllVilleByPays() {
		Assert.assertEquals(2, GeoDao.findAllVilleByPays(GeoDao.findPaysByName("pays1")).size());
	}

}
