package fr.iut.pt.veto.model.dao;

import java.util.Date;

import javax.swing.JToggleButton.ToggleButtonModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.iut.pt.veto.model.entitie.rh.Absence;
import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.model.entitie.rh.Role;
import fr.iut.pt.veto.model.entitie.rh.TypeAbsence;
import fr.iut.pt.veto.util.JpaUtils;


public class TestRhDao {
	
	public static Date dateToday;
	public static Date dateTomorow;
	public static Date dateTomorowTomorow;
	public static Contrat c1;
	public static Contrat c2;
	public static TypeAbsence typeAbsence1;
	public static TypeAbsence typeAbsence2;
	
	@BeforeClass
	public static void beforeAll() {
		
		JpaUtils.PERSISTENCE_UNIT = "dev_TU";
		
		dateToday = new Date();
		dateTomorow = new Date();
		dateTomorow.setDate(dateToday.getDate()+1);
		
		dateTomorowTomorow = new Date();
		
		dateTomorowTomorow.setDate(dateTomorow.getDate()+1);
		
		c1 = new Contrat();
		c1.setSalaire("10");
		c1.setDateDebut(dateToday);
		c1.setDateFin(dateTomorow);
		
		c2 = new Contrat();
		c2.setSalaire("100");
		c2.setDateDebut(dateTomorow);
		c2.setDateFin(dateTomorowTomorow);
		
		typeAbsence1 = new TypeAbsence();
		typeAbsence1.setIntitule("CP");
		
		typeAbsence2 = new TypeAbsence();
		typeAbsence2.setIntitule("AT");
		
		// Les rôles
	}
	
	@After
	public void after() {
		RhDao.deleteAllAbsence();
		RhDao.deleteAllContrat();
		RhDao.deleteAllRole();
		RhDao.deleteAllTypeAbsence();
	}
	
	/**
	 * TEST ROLE
	 */
	
	@Test
	public void testSaveOrUpdateRole() {
		Role r1 = new Role("Stagiaire");
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r1));
	}
	
	@Test
	public void testFindAllRole() {
		Role r1 = new Role("Stagiaire");
		Role r2 = new Role("Administrateur");
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r1));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r2));
		
		Assert.assertEquals(2, RhDao.findAllRole().size());
	}
	
	@Test
	public void testDeleteRole() {
		Role r1 = new Role("Stagiaire");
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r1));
		
		Assert.assertEquals(1, RhDao.findAllRole().size());
		
		RhDao.delete(r1);
		
		Assert.assertEquals(0, RhDao.findAllRole().size());
	}
	
	@Test
	public void testDeleteAllRole(){
		Role r1 = new Role("Stagiaire");
		Role r2 = new Role("Administrateur");
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r1));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r2));
		
		Assert.assertEquals(2, RhDao.findAllRole().size());
		
		RhDao.deleteAllRole();
		
		Assert.assertEquals(0, RhDao.findAllRole().size());
	}
	
	@Test
	public void testFindRoleById(){
		Role r1 = new Role("Stagiaire");
		Role r2 = new Role("Administrateur");
		
		int idR1 = RhDao.saveOrUpdate(r1);
		int idR2 = RhDao.saveOrUpdate(r2);
		Assert.assertNotEquals(0, idR1);
		Assert.assertNotEquals(0, idR2);
		
		Assert.assertEquals(2, RhDao.findAllRole().size());
		
		Assert.assertEquals(r1.getIntitule(), RhDao.findRoleById(idR1).getIntitule());
		Assert.assertEquals(r2.getIntitule(), RhDao.findRoleById(idR2).getIntitule());
		Assert.assertNotEquals(r1.getIntitule(), RhDao.findRoleById(idR2).getIntitule());
	}
	
	@Test
	public void testFindRoleByName(){
		Role r1 = new Role("Stagiaire");
		Role r2 = new Role("Administrateur");
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r1));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(r2));
		
		Assert.assertEquals(r1.getIntitule(), RhDao.findRoleByName(r1.getIntitule()).getIntitule());
		Assert.assertEquals(r2.getIntitule(), RhDao.findRoleByName(r2.getIntitule()).getIntitule());
		
		Assert.assertNotEquals(r1.getIntitule(), RhDao.findRoleByName(r2.getIntitule()).getIntitule());
	}
	
	/*
	 ************************************
	 */
	
	/**
	 * TEST CONTRAT
	 */
	
	@Test
	public void testSaveOrUpdateContrat() {
		Contrat a = new Contrat();
		a.setSalaire("10");
		a.setDateDebut(dateToday);
		a.setDateFin(dateTomorow);
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
	}
	
	@Test
	public void testFindAllContrat() {
		Contrat a = new Contrat();
		a.setSalaire("10");
		a.setDateDebut(dateToday);
		a.setDateFin(dateTomorow);
		
		Contrat b = new Contrat();
		b.setSalaire("100");
		b.setDateDebut(dateToday);
		b.setDateFin(dateTomorowTomorow);
		
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(b));
		
		Assert.assertEquals(2, RhDao.findAllContrat().size());
	}
	
	@Test
	public void testDeleteContrat() {
		Contrat a = new Contrat();
		
		a.setSalaire("10");
		a.setDateDebut(dateToday);
		a.setDateFin(dateTomorow);
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		
		Assert.assertEquals(1, RhDao.findAllContrat().size());
		
		RhDao.delete(a);
		
		Assert.assertEquals(0, RhDao.findAllContrat().size());
	}
	
	@Test
	public void testDeleteAllContrat(){
		Contrat a = new Contrat();
		a.setSalaire("10");
		a.setDateDebut(dateToday);
		a.setDateFin(dateTomorow);
		
		Contrat b = new Contrat();
		b.setSalaire("100");
		b.setDateDebut(dateToday);
		b.setDateFin(dateTomorowTomorow);
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(b));
		
		Assert.assertEquals(2, RhDao.findAllContrat().size());
		
		RhDao.deleteAllContrat();
		
		Assert.assertEquals(0, RhDao.findAllContrat().size());
	}
	
	@Test
	public void testFindContratById(){
		Contrat a = new Contrat();
		a.setSalaire("10");
		a.setDateDebut(dateToday);
		a.setDateFin(dateTomorow);
		
		Contrat b = new Contrat();
		b.setSalaire("100");
		b.setDateDebut(dateToday);
		b.setDateFin(dateTomorowTomorow);
		
		int idA = RhDao.saveOrUpdate(a);
		int idB = RhDao.saveOrUpdate(b);
		Assert.assertNotEquals(0, idA);
		Assert.assertNotEquals(0, idB);
		
		Assert.assertEquals(2, RhDao.findAllContrat().size());
		
		Assert.assertEquals(a.getSalaire(), RhDao.findContratById(idA).getSalaire());
		
		Assert.assertEquals(b.getSalaire(), RhDao.findContratById(idB).getSalaire());
		
		Assert.assertNotEquals(a.getSalaire(), RhDao.findContratById(idB).getSalaire());
	}
	
	/*
	 ************************************
	 */
	
	/**
	 * TEST ABSENCE
	 */
	
	@Test
	public void testSaveOrUpdateAbsence() {
		Absence a = new Absence();
		a.setContrat(c1);
		a.setType(typeAbsence1);
		a.setDateDebut(dateToday);
		int idA =  RhDao.saveOrUpdate(a);
		Assert.assertNotEquals(0, idA);
	}
	
	@Test
	public void testFindAllAbsence() {
		Absence a = new Absence();
		a.setContrat(c1);
		a.setType(typeAbsence1);
		a.setDateDebut(dateToday);
		int idA =  RhDao.saveOrUpdate(a);
		Assert.assertNotEquals(0, idA);
		
		Absence b = new Absence();
		b.setContrat(c2);
		b.setType(typeAbsence2);
		b.setDateDebut(dateTomorow);
		int idB =  RhDao.saveOrUpdate(b);
		Assert.assertNotEquals(0, idB);
		
		
		Assert.assertEquals(2, RhDao.findAllAbsence().size());
	}
	
	@Test
	public void testDeleteAbsence() {
		Absence a = new Absence();
		a.setContrat(c1);
		a.setType(typeAbsence1);
		a.setDateDebut(dateToday);
		int idA =  RhDao.saveOrUpdate(a);
		Assert.assertNotEquals(0, idA);
		a = RhDao.findAbsenceById(idA);
		
		Assert.assertEquals(1, RhDao.findAllAbsence().size());
		
		RhDao.delete(a);
		
		Assert.assertEquals(0, RhDao.findAllAbsence().size());
	}
	
	@Test
	public void testDeleteAllAbsence(){
		Absence a = new Absence();
		a.setContrat(c1);
		a.setType(typeAbsence1);
		a.setDateDebut(dateToday);
		int idA =  RhDao.saveOrUpdate(a);
		Assert.assertNotEquals(0, idA);
		
		Absence b = new Absence();
		b.setContrat(c2);
		b.setType(typeAbsence2);
		b.setDateDebut(dateTomorow);
		int idB =  RhDao.saveOrUpdate(b);
		Assert.assertNotEquals(0, idB);
		
		Assert.assertEquals(2, RhDao.findAllAbsence().size());
		
		RhDao.deleteAllAbsence();
		
		Assert.assertEquals(0, RhDao.findAllAbsence().size());
	}
	
	@Test
	public void testFindAbsenseByTypeAbsence(){
		int idTypeAbsence1 = RhDao.saveOrUpdate(typeAbsence1);
		int idTypeAbsence2 = RhDao.saveOrUpdate(typeAbsence2);	
		Assert.assertNotEquals(0, idTypeAbsence1);
		Assert.assertNotEquals(0, idTypeAbsence2);
		
		TypeAbsence objTypeAbs1 = RhDao.findTypeAbsenceById(idTypeAbsence1);
		TypeAbsence objTypeAbs2 = RhDao.findTypeAbsenceById(idTypeAbsence2);
		
		Absence a = new Absence();
		a.setContrat(c1);
		a.setType(objTypeAbs1);
		a.setDateDebut(dateToday);
		int idA =  RhDao.saveOrUpdate(a);
		Assert.assertNotEquals(0, idA);
		a = RhDao.findAbsenceById(idA);
		
		Absence b = new Absence();
		b.setContrat(c2);
		b.setType(objTypeAbs2);
		b.setDateDebut(dateTomorow);
		int idB =  RhDao.saveOrUpdate(b);
		Assert.assertNotEquals(0, idB);
		b = RhDao.findAbsenceById(idB);
		
		Absence c = new Absence();
		c.setContrat(c2);
		c.setType(objTypeAbs1);
		c.setDateDebut(dateTomorow);
		int idC =  RhDao.saveOrUpdate(c);
		Assert.assertNotEquals(0, idC);
		c = RhDao.findAbsenceById(idC);
		
		
		Assert.assertEquals(3, RhDao.findAllAbsence().size());
		
		Assert.assertEquals(2, RhDao.findAbsenceByTypeAbsence(objTypeAbs1).size());
		
	}
	
	@Test
	public void testFindAbsenceByContrat(){
		
		int idC1 = RhDao.saveOrUpdate(c1);
		int idC2 = RhDao.saveOrUpdate(c2);
		
		Assert.assertNotEquals(0, idC1);
		Assert.assertNotEquals(0, idC2);
		
		Absence a = new Absence();
		a.setContrat(c1);
		a.setType(typeAbsence1);
		a.setDateDebut(dateToday);
		int idA =  RhDao.saveOrUpdate(a);
		Assert.assertNotEquals(0, idA);
		a = RhDao.findAbsenceById(idA);
		
		Absence b = new Absence();
		b.setContrat(RhDao.findContratById(idC2));
		b.setType(typeAbsence2);
		b.setDateDebut(dateTomorow);
		
		Absence c = new Absence();
		c.setContrat(RhDao.findContratById(idC2));
		c.setType(typeAbsence1);
		c.setDateDebut(dateTomorow);
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(b));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(c));
		
		Assert.assertEquals(3, RhDao.findAllAbsence().size());
		Assert.assertEquals(2, RhDao.findAbsenceByContrat(RhDao.findContratById(idC2)).size());
	}
	
	/*
	 ************************************
	 */
	
	/**
	 * TEST TYPEABSENCE
	 */
	
	@Test
	public void testSaveOrUpdateTypeAbsence() {
		TypeAbsence a = new TypeAbsence("CP");
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
	}
	
	@Test
	public void testFindAllTypeAbsence() {
		TypeAbsence a = new TypeAbsence("CP");
		TypeAbsence b = new TypeAbsence("AT");
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(b));
		
		Assert.assertEquals(2, RhDao.findAllTypeAbsence().size());
	}
	
	@Test
	public void testDeleteTypeAbsence() {
		TypeAbsence a = new TypeAbsence("CP");
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		
		Assert.assertEquals(1, RhDao.findAllTypeAbsence().size());
		
		RhDao.delete(a);
		
		Assert.assertEquals(0, RhDao.findAllTypeAbsence().size());
	}
	
	@Test
	public void testDeleteAllTypeAbsence(){
		TypeAbsence a = new TypeAbsence("CP");
		TypeAbsence b = new TypeAbsence("AT");
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(b));
		
		Assert.assertEquals(2, RhDao.findAllTypeAbsence().size());
		
		RhDao.deleteAllTypeAbsence();
		
		Assert.assertEquals(0, RhDao.findAllTypeAbsence().size());
	}
	
	@Test
	public void testFindTypeAbsenceById(){
		TypeAbsence a = new TypeAbsence("CP");
		TypeAbsence b = new TypeAbsence("AT");
		
		int idR1 = RhDao.saveOrUpdate(a);
		int idR2 = RhDao.saveOrUpdate(b);
		Assert.assertNotEquals(0, idR1);
		Assert.assertNotEquals(0, idR2);
		
		Assert.assertEquals(2, RhDao.findAllTypeAbsence().size());
		
		Assert.assertEquals(a.getIntitule(), RhDao.findTypeAbsenceById(idR1).getIntitule());
		Assert.assertEquals(b.getIntitule(), RhDao.findTypeAbsenceById(idR2).getIntitule());
		Assert.assertNotEquals(a.getIntitule(), RhDao.findTypeAbsenceById(idR2).getIntitule());
	}
	
	@Test
	public void testFindTypeAbsenceByName(){
		TypeAbsence a = new TypeAbsence("CP");
		TypeAbsence b = new TypeAbsence("AT");
		
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(a));
		Assert.assertNotEquals(0, RhDao.saveOrUpdate(b));
		
		Assert.assertEquals(a.getIntitule(), RhDao.findTypeAbsenceByName(a.getIntitule()).getIntitule());
		Assert.assertEquals(b.getIntitule(), RhDao.findTypeAbsenceByName(b.getIntitule()).getIntitule());
		
		Assert.assertNotEquals(a.getIntitule(), RhDao.findTypeAbsenceByName(b.getIntitule()).getIntitule());
	}
	
	/*
	 ************************************
	 */
}
