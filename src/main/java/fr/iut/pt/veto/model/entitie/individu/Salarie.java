/**
 * 
 */
package fr.iut.pt.veto.model.entitie.individu;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import fr.iut.pt.veto.model.entitie.rh.Contrat;
import fr.iut.pt.veto.model.entitie.rh.Role;

/**
 * Bean pour VETO.Salarie
 * 
 * @author sebboursier
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Salarie.findAll", query = "SELECT s from Salarie s"),
	@NamedQuery(name = "Salarie.findById", query = "SELECT s from Salarie s WHERE s.id = :id"),
	@NamedQuery(name = "Salarie.findByRole", query = "SELECT s from Salarie s WHERE s.role.intitule = :intitule"),
	@NamedQuery(name = "Salarie.findByIdentifiant", query = "SELECT s from Salarie s WHERE s.identifiant = :identifiant"),
	@NamedQuery(name = "Salarie.findByContrat", query = "SELECT s from Salarie s WHERE s.contrat.id = :id"),
	@NamedQuery(name = "Salarie.removeAll", query = "DELETE FROM Salarie")
})
@Table(name = "Salarie", uniqueConstraints = { @UniqueConstraint(columnNames = { "identifiant" }) })
public class Salarie  extends Individu  {
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = true)
	private Role role;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = true)
	private Contrat contrat;
	
	@Basic(optional = false)
	private String identifiant;
	
	@Basic(optional = false)
	private String password;
	
	/**
	 * Constructeur defaut
	 */
	public Salarie() {
		super();
	}
	
	/**
	 * Constructeur partiel
	 */
	public Salarie(final String nom, final String prenom) {
		super(nom,prenom);
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
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
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return identifiant;
	}
}
