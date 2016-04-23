/**
 * 
 */
package fr.iut.pt.veto.model.entitie.animal;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Bean pour VETO.Race
 * 
 * @author sebboursier
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Race.findAll", query = "SELECT r from Race r"),
	@NamedQuery(name = "Race.findById", query = "SELECT r from Race r WHERE r.id = :id"),
	@NamedQuery(name = "Race.findByNom", query = "SELECT r from Race r WHERE r.nom LIKE :nom"),
	@NamedQuery(name = "Race.findByEspece", query = "SELECT r from Race r WHERE r.espece.id = :id"),
	@NamedQuery(name = "Race.removeAll", query = "DELETE FROM Race")
})
@Table(name = "Race", uniqueConstraints = { @UniqueConstraint(columnNames = { "nom" }) })
public class Race implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String nom;

	@Basic(optional = false)
	private Espece espece;
	
	/**
	 * Constructeur defaut
	 */
	public Race() {
		super();
	}

	/**
	 * @param nom et espece
	 */
	public Race(String nom, Espece espece) {
		super();
		this.nom = nom;
		this.espece = espece;
	}

	public Race(String race) {
		this.nom = race;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return espece
	 */
	public Espece getEspece() {
		return espece;
	}

	/**
	 * @param espece to set espece
	 */
	public void setEspece(Espece espece) {
		this.espece = espece;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.getEspece().getNom() + ": " + this.getNom();
	}
}