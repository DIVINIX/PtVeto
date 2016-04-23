/**
 * 
 */
package fr.iut.pt.veto.model.entitie.traitement;

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
 * Bean pour VETO.Maladie
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Maladie.findAll", query = "SELECT m from Maladie m"),
	@NamedQuery(name = "Maladie.findById", query = "SELECT m from Maladie m WHERE m.id = :id"),
	@NamedQuery(name = "Maladie.findByNom", query = "SELECT m from Maladie m WHERE m.nom = :nom"),
	@NamedQuery(name = "Maladie.removeAll", query = "DELETE FROM Maladie")
})
@Table(name = "Maladie", uniqueConstraints = { @UniqueConstraint(columnNames = { "nom" }) })
public class Maladie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Basic(optional = false)
	private String nom;

	/**
	 * Constructeur defaut
	 */
	public Maladie() {
		super();
	}

	/**
	 * Constructeur Full.
	 * @param nom
	 */
	public Maladie(String nom) {
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
		return this.getNom();
	}
	
}
