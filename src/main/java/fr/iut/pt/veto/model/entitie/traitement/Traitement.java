/**
 * 
 */
package fr.iut.pt.veto.model.entitie.traitement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.iut.pt.veto.model.entitie.animal.Animal;
import fr.iut.pt.veto.model.entitie.inventaire.Produit;

/**
 * Bean pour VETO.Traitement
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Traitement.findAll", query = "SELECT t from Traitement t"),
	@NamedQuery(name = "Traitement.findById", query = "SELECT t from Traitement t WHERE t.id = :id"),
	@NamedQuery(name = "Traitement.findByAnimal", query = "SELECT t from Traitement t WHERE t.animal.id = :id"),
	@NamedQuery(name = "Traitement.findByMaladie", query = "SELECT t from Traitement t WHERE t.maladie.id = :id"),
	@NamedQuery(name = "Traitement.findByNom", query = "SELECT t from Traitement t WHERE t.nom = :nom"),
	@NamedQuery(name = "Traitement.removeAll", query = "DELETE FROM Traitement")
})
@Table(name = "Traitement")
public class Traitement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Basic(optional = false)
	private Animal animal;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Basic(optional = true)
	private Maladie maladie;

	@Basic(optional = true)
	private String nom;

	@Basic(optional = true)
	private String description;

	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Basic(optional = true)
	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "soins")
	private List<Produit> soins;

	/**
	 * Constructeur defaut
	 */
	public Traitement() {
		super();
	}

	/**
	 * @return the animal
	 */
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * @param animal
	 *            the animal to set
	 */
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	/**
	 * @return the maladie
	 */
	public Maladie getMaladie() {
		return maladie;
	}

	/**
	 * @param maladie
	 *            the maladie to set
	 */
	public void setMaladie(Maladie maladie) {
		this.maladie = maladie;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the soins
	 */
	public List<Produit> getSoins() {
		return soins;
	}

	/**
	 * @param soins
	 *            the soins to set
	 */
	public void setSoins(List<Produit> soins) {
		this.soins = soins;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.getNom() + " - " + this.getMaladie();
	}
}
