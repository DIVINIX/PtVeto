/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.inventaire.Vente;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author sebastien
 *
 */
public class TestInventaireDao {

	private static int NB_PRODUIT = 2;

	@BeforeClass
	public static void beforeAll() {

		JpaUtils.PERSISTENCE_UNIT = "dev_TU";

		InventaireDao.deleteAllProduit();

		Produit p1 = new Produit("p1");
		Produit p2 = new Produit("p2");
		p2.setPrix_reel(20.99);

		InventaireDao.saveOrUpdate(p1);
		InventaireDao.saveOrUpdate(p2);

		Client c1 = new Client("c1", "c1");
		Client c2 = new Client("c2", "c2");
		Salarie s1 = new Salarie("s1", "s1");
		s1.setIdentifiant("i1");
		s1.setPassword("p1");
		Salarie s2 = new Salarie("s2", "s2");
		s2.setIdentifiant("i2");
		s2.setPassword("p2");
		IndividuDao.saveOrUpdate(c1);
		IndividuDao.saveOrUpdate(c2);
		IndividuDao.saveOrUpdate(s1);
		IndividuDao.saveOrUpdate(s2);
	}

	@AfterClass
	public static void afterAll() {
		InventaireDao.deleteAllVente();
		InventaireDao.deleteAllProduit();
		IndividuDao.deleteAllIndividu();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testFindAllProduits() {
		Assert.assertEquals(NB_PRODUIT, InventaireDao.findAllProduit().size());
	}

	@Test
	public void testInsertAndDeleteProduit() {
		Produit p3 = new Produit("p3");

		int id = InventaireDao.saveOrUpdate(p3);

		Assert.assertNotEquals(0, id);
		Assert.assertEquals(NB_PRODUIT + 1, InventaireDao.findAllProduit().size());

		Produit testRes = InventaireDao.findProduitByNom("p3");

		Assert.assertEquals(id, testRes.getId());

		InventaireDao.delete(testRes);

		Assert.assertEquals(NB_PRODUIT, InventaireDao.findAllProduit().size());
	}

	@Test
	public void findProduitBy() {
		Produit p3 = new Produit("p3");
		Produit p4 = new Produit("p4");
		p4.setPrix_reel(43.21);

		int id = InventaireDao.saveOrUpdate(p3);
		InventaireDao.saveOrUpdate(p4);

		Produit p3_res = InventaireDao.findProduitById(id);
		Assert.assertNotNull(p3_res);
		Assert.assertEquals(p3.getPrix_reel(), p3_res.getPrix_reel());

		List<Produit> p4_res = InventaireDao.findProduitByPrix(p4.getPrix_reel());
		Assert.assertEquals(1, p4_res.size());
		Assert.assertEquals(p4.getPrix_reel(), p4_res.get(0).getPrix_reel());

		InventaireDao.delete(p3_res);
		InventaireDao.delete(p4_res.get(0));
	}

	@Test
	public void testInsertAndDeleteVente() {
		Vente v1 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);

		int id = InventaireDao.saveOrUpdate(v1);

		Assert.assertNotEquals(0, id);
		Assert.assertEquals(1, InventaireDao.findAllVente().size());

		Vente testRes = InventaireDao.findVenteById(id);

		Assert.assertEquals(id, testRes.getId());

		InventaireDao.delete(testRes);

		Assert.assertEquals(0, InventaireDao.findAllVente().size());
	}
	
	@Test
	public void testFindVenteByClient() {
		Vente v1 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		Vente v2 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c2").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		Vente v3 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		
		InventaireDao.saveOrUpdate(v1);
		InventaireDao.saveOrUpdate(v2);
		InventaireDao.saveOrUpdate(v3);
		
		Assert.assertEquals(2, InventaireDao.findVenteByClient((Client) IndividuDao.findIndividuByNom("c1").get(0)).size());
		Assert.assertEquals(1, InventaireDao.findVenteByClient((Client) IndividuDao.findIndividuByNom("c2").get(0)).size());
	
		InventaireDao.deleteAllVente();
	}
	
	@Test
	public void testFindVenteBySalarie() {
		Vente v1 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		Vente v2 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c2").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s2").get(0), new Date(), 2, 20.0);
		Vente v3 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		
		InventaireDao.saveOrUpdate(v1);
		InventaireDao.saveOrUpdate(v2);
		InventaireDao.saveOrUpdate(v3);
		
		Assert.assertEquals(2, InventaireDao.findVenteBySalarie((Salarie) IndividuDao.findIndividuByNom("s1").get(0)).size());
		Assert.assertEquals(1, InventaireDao.findVenteBySalarie((Salarie) IndividuDao.findIndividuByNom("s2").get(0)).size());
	
		InventaireDao.deleteAllVente();
	}
	
	@Test
	public void testFindVenteByProduit() {
		Vente v1 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		Vente v2 = new Vente(InventaireDao.findProduitByNom("p2"), (Client) IndividuDao.findIndividuByNom("c2").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s2").get(0), new Date(), 2, 20.0);
		Vente v3 = new Vente(InventaireDao.findProduitByNom("p1"), (Client) IndividuDao.findIndividuByNom("c1").get(0),
				(Salarie) IndividuDao.findIndividuByNom("s1").get(0), new Date(), 2, 20.0);
		
		InventaireDao.saveOrUpdate(v1);
		InventaireDao.saveOrUpdate(v2);
		InventaireDao.saveOrUpdate(v3);
		
		Assert.assertEquals(2, InventaireDao.findVenteByProduit(InventaireDao.findProduitByNom("p1")).size());
		Assert.assertEquals(1, InventaireDao.findVenteByProduit(InventaireDao.findProduitByNom("p2")).size());
	
		InventaireDao.deleteAllVente();
	}
}
