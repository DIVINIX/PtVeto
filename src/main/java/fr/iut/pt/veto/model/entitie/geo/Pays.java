/**
 * 
 */
package fr.iut.pt.veto.model.entitie.geo;

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
 * Bean pour VETO.Pays
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Pays.findAll", query = "SELECT p from Pays p"),
		@NamedQuery(name = "Pays.findById", query = "SELECT p from Pays p WHERE p.id = :id"),
		@NamedQuery(name = "Pays.findByName", query = "SELECT p from Pays p WHERE p.nom LIKE :nom"),
		@NamedQuery(name = "Pays.removeAll", query = "DELETE FROM Pays") })
@Table(name = "Pays", uniqueConstraints = { @UniqueConstraint(columnNames = { "nom" }) })
public class Pays implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Basic(optional = false)
	private String nom;

	/**
	 * Constructeur par defaut.
	 */
	public Pays() {
		super();
	}

	/**
	 * @param nom
	 */
	public Pays(String nom) {
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
	 * @param nom
	 *            the nom to set
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
