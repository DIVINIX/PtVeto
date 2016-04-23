/**
 * 
 */
package fr.iut.pt.veto.model.entitie.rh;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Bean pour VETO.Contrat
 * 
 * @author sebastien
 */
@Entity
@Table(name = "Contrat")
@NamedQueries({
	@NamedQuery(name = "Contrat.findAll", query = "SELECT c from Contrat c"),
	@NamedQuery(name = "Contrat.removeAll", query = "DELETE FROM Contrat"),
	@NamedQuery(name = "Contrat.findById", query = "SELECT c from Contrat c WHERE c.id = :id"),
})
public class Contrat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String salaire;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Basic(optional = true)
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	/**
	 * Constructeur defaut
	 */
	public Contrat() {
		super();
	}

	/**
	 * @return the salaire
	 */
	public String getSalaire() {
		return salaire;
	}

	/**
	 * @param salaire the salaire to set
	 */
	public void setSalaire(String salaire) {
		this.salaire = salaire;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
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
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
