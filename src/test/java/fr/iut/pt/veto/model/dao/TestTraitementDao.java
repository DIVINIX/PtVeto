/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;
import fr.iut.pt.veto.model.entitie.traitement.Maladie;
import fr.iut.pt.veto.model.entitie.traitement.Traitement;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author sebastien
 *
 */
public class TestTraitementDao {

	@BeforeClass
	public static void beforeAll() {

		JpaUtils.PERSISTENCE_UNIT = "dev_TU";

		Client c1 = new Client("c1", "c1");
		
		Animal a1 = new Animal(c1);
		a1.setNom("a1");
		AnimalDao.saveOrUpdate(a1);
		
		Animal a2 = new Animal(c1);
		a2.setNom("a2");
		AnimalDao.saveOrUpdate(a2);
		
		Animal a3 = new Animal(c1);
		a3.setNom("a3");
		AnimalDao.saveOrUpdate(a3);

		Maladie m1 = new Maladie("m1");
		TraitementDao.saveOrUpdate(m1);
		
		Produit p1 = new Produit("p1");
		InventaireDao.saveOrUpdate(p1);
	}

	@AfterClass
	public static void afterAll() {
		TraitementDao.deleteAllTraitement();
		TraitementDao.deleteAllMaladie();
		AnimalDao.deleteAllAnimal();
		IndividuDao.deleteAllIndividu();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testInsertAndDeleteMaladie() {
		Maladie maladie = new Maladie("m2");

		int id = TraitementDao.saveOrUpdate(maladie);

		Assert.assertEquals(2, TraitementDao.findAllMaladie().size());

		Maladie result = TraitementDao.findMaladieById(id);

		Assert.assertNotNull(result);
		Assert.assertEquals("m2", result.getNom());

		TraitementDao.delete(result);

		Assert.assertEquals(1, TraitementDao.findAllMaladie().size());
	}

	@Test
	public void testInsertAndDeleteTraitement() {
		Traitement traitement = new Traitement();
		traitement.setMaladie(TraitementDao.findMaladieByNom("m1"));
		traitement.setDateDebut(new Date());
		traitement.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));

		int id = TraitementDao.saveOrUpdate(traitement);

		Assert.assertEquals(1, TraitementDao.findAllTraitement().size());

		Traitement result = TraitementDao.findTraitementById(id);

		Assert.assertNotNull(result);
		Assert.assertEquals("a1", result.getAnimal().getNom());
		Assert.assertEquals("m1", result.getMaladie().getNom());

		TraitementDao.delete(result);

		Assert.assertEquals(0, TraitementDao.findAllTraitement().size());
	}

	@Test
	public void testFindBy() {
		Traitement traitement1 = new Traitement();
		traitement1.setMaladie(TraitementDao.findMaladieByNom("m1"));
		traitement1.setDateDebut(new Date());
		traitement1.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));

		Traitement traitement2 = new Traitement();
		traitement2.setDateDebut(new Date());
		traitement2.setNom("t1");
		traitement2.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));

		Traitement traitement3 = new Traitement();
		traitement3.setMaladie(TraitementDao.findMaladieByNom("m1"));
		traitement3.setDateDebut(new Date());
		traitement3.setAnimal(AnimalDao.findAnimalByNom("a2").get(0));

		TraitementDao.saveOrUpdate(traitement1);
		TraitementDao.saveOrUpdate(traitement2);
		TraitementDao.saveOrUpdate(traitement3);

		Assert.assertEquals(2, TraitementDao.findTraitementByAnimal(AnimalDao.findAnimalByNom("a1").get(0)).size());
		Assert.assertEquals(1, TraitementDao.findTraitementByAnimal(AnimalDao.findAnimalByNom("a2").get(0)).size());
		
		Assert.assertEquals(2, TraitementDao.findTraitementByMaladie(TraitementDao.findMaladieByNom("m1")).size());
		
		Assert.assertEquals(1, TraitementDao.findTraitementByNom("t1").size());

		TraitementDao.deleteAllTraitement();
	}
	
	@Test
	public void testInsertSoins() {
		Traitement traitement = new Traitement();
		traitement.setMaladie(TraitementDao.findMaladieByNom("m1"));
		traitement.setDateDebut(new Date());
		traitement.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		
		List<Produit> soins = new ArrayList<>();
		soins.add(InventaireDao.findProduitByNom("p1"));
		traitement.setSoins(soins);

		int id = TraitementDao.saveOrUpdate(traitement);

		Assert.assertEquals(1, TraitementDao.findAllTraitement().size());

		Traitement result = TraitementDao.findTraitementById(id);

		Assert.assertNotNull(result);
		Assert.assertEquals("a1", result.getAnimal().getNom());
		Assert.assertEquals("m1", result.getMaladie().getNom());
		Assert.assertEquals(1, result.getSoins().size());
		Assert.assertEquals("p1", result.getSoins().get(0).getNom());

		TraitementDao.delete(result);

		Assert.assertEquals(0, TraitementDao.findAllTraitement().size());
	}
}
