/**
 * 
 */
package fr.iut.pt.veto.model.entitie;

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
 * Bean pour VETO.Consultation
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Consultation.findAll", query = "SELECT c from Consultation c"),
	@NamedQuery(name = "Consultation.findById", query = "SELECT c from Consultation c WHERE c.id = :id"),
	@NamedQuery(name = "Consultation.findByClient", query = "SELECT c from Consultation c WHERE c.animal.client.id = :id"),
	@NamedQuery(name = "Consultation.findByAnimal", query = "SELECT c from Consultation c WHERE c.animal.id = :id"),
	@NamedQuery(name = "Consultation.removeAll", query = "DELETE FROM Consultation")
})
@Table(name = "Consultation")
public class Consultation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Basic(optional = true)
	private Animal animal;

	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Basic(optional = false)
	private String description;

	/**
	 * Constructeur defaut
	 */
	public Consultation() {
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
