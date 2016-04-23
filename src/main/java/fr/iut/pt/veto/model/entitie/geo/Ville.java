/**
 * 
 */
package fr.iut.pt.veto.model.entitie.geo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.loader.custom.Return;

/**
 * Bean pour VETO.Ville
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Ville.findAll", query = "SELECT v from Ville v"),
	@NamedQuery(name = "Ville.findById", query = "SELECT v from Ville v WHERE v.id = :id"),
	@NamedQuery(name = "Ville.findByName", query = "SELECT v from Ville v WHERE v.nom LIKE :nom"),
	@NamedQuery(name = "Ville.findByPostal", query = "SELECT v from Ville v WHERE v.codePostale LIKE :codePostale"),
	@NamedQuery(name = "Ville.findByPays", query = "SELECT v from Ville v WHERE v.pays.nom LIKE :nom"),
	@NamedQuery(name = "Ville.removeAll", query = "DELETE FROM Ville")
})
@Table(
	name = "Ville",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"nom","codePostale"})
	}
)
public class Ville implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = false)
	private Pays pays;
	
	@Basic(optional = false)
	private String nom;
	
	@Basic(optional = false)
	private String codePostale;

	/**
	 * Constructeur par defaut
	 */
	public Ville() {
		super();
	}

	/**
	 * @param pays
	 * @param nom
	 * @param codePostale
	 */
	public Ville(Pays pays, String nom, String codePostale) {
		super();
		this.pays = pays;
		this.nom = nom;
		this.codePostale = codePostale;
	}

	/**
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(Pays pays) {
		this.pays = pays;
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
	 * @return the codePostale
	 */
	public String getCodePostale() {
		return codePostale;
	}

	/**
	 * @param codePostale the codePostale to set
	 */
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
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
