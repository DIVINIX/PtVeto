/**
 * 
 */
package fr.iut.pt.veto.model.entitie.animal;

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

import fr.iut.pt.veto.model.entitie.individu.Client;

/**
 * Bean pour VETO.Animal
 * 
 * @author sebboursier
 * @author dimitri
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Animal.findAll", query = "SELECT a from Animal a"),
	@NamedQuery(name = "Animal.findById", query = "SELECT a from Animal a WHERE a.id = :id"),
	@NamedQuery(name = "Animal.findByNom", query = "SELECT a from Animal a WHERE a.nom LIKE :nom"),
	@NamedQuery(name = "Animal.findByClient", query = "SELECT a from Animal a WHERE a.client.id = :id"),
	@NamedQuery(name = "Animal.findByRace", query = "SELECT a from Animal a WHERE a.race.id = :id"),
	@NamedQuery(name = "Animal.findByEspece", query = "SELECT a from Animal a WHERE a.race.espece.id = :id"),
	@NamedQuery(name = "Animal.removeAll", query = "DELETE FROM Animal")
})
@Table(name = "Animal")
public class Animal implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = true)
	private String nom;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = true)
	private Race race;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = false)
	private Client client;
	
	/**
	 * Constructeur defaut
	 */
	public Animal() {
		super();
	}

	/**
	 * Constructeur partiel.
	 * @param client
	 */
	public Animal(Client client) {
		super();
		this.client = client;
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
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
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