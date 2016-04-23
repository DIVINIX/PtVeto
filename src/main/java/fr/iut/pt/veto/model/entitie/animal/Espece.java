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
 * Bean pour VETO.Espece
 * 
 * @author sebboursier
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Espece.findAll", query = "SELECT e from Espece e"),
	@NamedQuery(name = "Espece.findById", query = "SELECT e from Espece e WHERE e.id = :id"),
	@NamedQuery(name = "Espece.findByNom", query = "SELECT e from Espece e WHERE e.nom LIKE :nom"),
	@NamedQuery(name = "Espece.removeAll", query = "DELETE FROM Espece")
})
@Table(name = "Espece", uniqueConstraints = { @UniqueConstraint(columnNames = { "nom" }) })
public class Espece implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String nom;
	
	/**
	 * Constructeur defaut
	 */
	public Espece() {
		super();
	}

	/**
	 * @param nom
	 */
	public Espece(String nom) {
		super();
		this.nom = nom;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return nom;
	}
}