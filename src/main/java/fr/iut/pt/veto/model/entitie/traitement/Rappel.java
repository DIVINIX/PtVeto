/**
 * 
 */
package fr.iut.pt.veto.model.entitie.traitement;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.iut.pt.veto.model.entitie.animal.Animal;

/**
 * Bean pour VETO.Rappel
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Rappel.findAll", query = "SELECT r from Rappel r"),
	@NamedQuery(name = "Rappel.findById", query = "SELECT r from Rappel r WHERE r.id = :id"),
	@NamedQuery(name = "Rappel.findByAnimal", query = "SELECT r from Rappel r WHERE r.animal.id = :id"),
	@NamedQuery(name = "Rappel.removeAll", query = "DELETE FROM Rappel")
})
@Table(name = "Rappel")
public class Rappel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Basic(optional = false)
	private Animal animal;

	@Basic(optional = true)
	private String raison;

	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	/**
	 * Constructeur defaut
	 */
	public Rappel() {
		super();
	}

	/**
	 * @return the raison
	 */
	public String getRaison() {
		return raison;
	}

	/**
	 * @param raison
	 *            the raison to set
	 */
	public void setRaison(String raison) {
		this.raison = raison;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the animal
	 */
	public Animal getAnimal() {
		return animal;
	}

	/**
	 * @param animal the animal to set
	 */
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
