/**
 * 
 */
package fr.iut.pt.veto.model.entitie.individu;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Bean pour VETO.Client
 * 
 * @author sebboursier
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Client.findAll", query = "SELECT c from Client c"),
	@NamedQuery(name = "Client.findById", query = "SELECT c from Client c WHERE c.id = :id"),
	@NamedQuery(name = "Client.removeAll", query = "DELETE FROM Client")
})
@Table(name = "Client")
public class Client extends Individu {

	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	/**
	 * Constructeur defaut
	 */
	public Client() {
		super();
	}
	
	/**
	 * 
	 */
	public Client(final String nom, final String prenom) {
		super(nom,prenom);
		this.dateDebut = new Date();
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	@Override
	public String toString() {
		return this.getNom() + " " + this.getPrenom() + " " + this.getTelephone();
	}

}
