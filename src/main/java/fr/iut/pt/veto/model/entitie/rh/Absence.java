/**
 * 
 */
package fr.iut.pt.veto.model.entitie.rh;

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

/**
 * Bean pour VETO.Absence
 * 
 * @author sebastien
 */
@Entity
@Table(name = "Absence")
@NamedQueries({
	@NamedQuery(name = "Absence.findAll", query = "SELECT a from Absence a"),
	@NamedQuery(name = "Absence.removeAll", query = "DELETE FROM Absence"),
	@NamedQuery(name = "Absence.findById", query = " SELECT a from Absence a WHERE a.id = :id"),
	@NamedQuery(name = "Absence.findAbsenceByTypeAbsence", query = " SELECT a from Absence a WHERE a.type.id = :id"),
	@NamedQuery(name = "Absence.findAbsenceByContrat", query = " SELECT a from Absence a WHERE a.contrat.id = :id")
})
public class Absence implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Basic(optional = false)
	private Contrat contrat;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Basic(optional = false)
	private TypeAbsence type;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Basic(optional = true)
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	/**
	 * Constructeur defaut
	 */
	public Absence() {
		super();
	}

	/**
	 * @return the contrat
	 */
	public Contrat getContrat() {
		return contrat;
	}

	/**
	 * @param contrat the contrat to set
	 */
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	/**
	 * @return the type
	 */
	public TypeAbsence getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeAbsence type) {
		this.type = type;
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
