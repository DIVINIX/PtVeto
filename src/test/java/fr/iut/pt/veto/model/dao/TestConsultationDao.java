/**
 * 
 */
package fr.iut.pt.veto.model.dao;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.Consultation;
import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.util.JpaUtils;

/**
 * @author sebastien
 *
 */
public class TestConsultationDao {

	@BeforeClass
	public static void beforeAll() {

		JpaUtils.PERSISTENCE_UNIT = "dev_TU";
		
		Client c1 = new Client("c1", "c1");
		Client c2 = new Client("c2", "c2");
		int id1 = IndividuDao.saveOrUpdate(c1);
		int id2 = IndividuDao.saveOrUpdate(c2);
		
		Animal a1 = new Animal((Client) IndividuDao.findIndividuById(id1));
		a1.setNom("a1");
		AnimalDao.saveOrUpdate(a1);
		
		Animal a2 = new Animal((Client) IndividuDao.findIndividuById(id1));
		a2.setNom("a2");
		AnimalDao.saveOrUpdate(a2);
		
		Animal a3 = new Animal((Client) IndividuDao.findIndividuById(id2));
		a3.setNom("a3");
		AnimalDao.saveOrUpdate(a3);
	}

	@AfterClass
	public static void afterAll() {
		ConsultationDao.deleteAllConsultation();
		AnimalDao.deleteAllAnimal();
		IndividuDao.deleteAllIndividu();
		JpaUtils.closeEntityManagerFactory();
	}

	@Test
	public void testInsertAndDeleteConsultation() {
		Consultation consultation = new Consultation();
		consultation.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		consultation.setDescription("desc");
		consultation.setDateDebut(new Date());
	
		int id = ConsultationDao.saveOrUpdate(consultation);
		
		Assert.assertEquals(1, ConsultationDao.findAllConsultation().size());
		
		Consultation result = ConsultationDao.findConsultationById(id);
		
		Assert.assertEquals("desc", result.getDescription());
		Assert.assertEquals("a1", result.getAnimal().getNom());
		Assert.assertEquals("c1", result.getAnimal().getClient().getNom());
		
		ConsultationDao.delete(result);
		
		Assert.assertEquals(0, ConsultationDao.findAllConsultation().size());
	}
	
	@Test
	public void testFindByObject() {
		Consultation consultation1 = new Consultation();
		consultation1.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		consultation1.setDescription("desc");
		consultation1.setDateDebut(new Date());
		
		Consultation consultation2 = new Consultation();
		consultation2.setAnimal(AnimalDao.findAnimalByNom("a1").get(0));
		consultation2.setDescription("desc");
		consultation2.setDateDebut(new Date());
		
		Consultation consultation3 = new Consultation();
		consultation3.setAnimal(AnimalDao.findAnimalByNom("a2").get(0));
		consultation3.setDescription("desc");
		consultation3.setDateDebut(new Date());
		
		Consultation consultation4 = new Consultation();
		consultation4.setAnimal(AnimalDao.findAnimalByNom("a3").get(0));
		consultation4.setDescription("desc");
		consultation4.setDateDebut(new Date());
		
		ConsultationDao.saveOrUpdate(consultation1);
		ConsultationDao.saveOrUpdate(consultation2);
		ConsultationDao.saveOrUpdate(consultation3);
		ConsultationDao.saveOrUpdate(consultation4);
		
		Assert.assertEquals(2, ConsultationDao.findConsultationByAnimal(AnimalDao.findAnimalByNom("a1").get(0)).size());
		Assert.assertEquals(1, ConsultationDao.findConsultationByAnimal(AnimalDao.findAnimalByNom("a3").get(0)).size());
		Assert.assertEquals(3, ConsultationDao.findConsultationByClient((Client) IndividuDao.findIndividuByNom("c1").get(0)).size());
		Assert.assertEquals(1, ConsultationDao.findConsultationByClient((Client) IndividuDao.findIndividuByNom("c2").get(0)).size());
	
		ConsultationDao.deleteAllConsultation();
		Assert.assertEquals(0, ConsultationDao.findAllConsultation().size());
	}
	
}
