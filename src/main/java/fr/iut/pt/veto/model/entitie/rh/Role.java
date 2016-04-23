/**
 * 
 */
package fr.iut.pt.veto.model.entitie.rh;

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
 * Bean pour VETO.Role
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Role.findAll", query = "SELECT r from Role r"),
	@NamedQuery(name = "Role.removeAll", query = "DELETE FROM Role"),
	@NamedQuery(name = "Role.findById", query = " SELECT r from Role r WHERE r.id = :id"),
	@NamedQuery(name = "Role.findByName", query = " SELECT r from Role r WHERE r.intitule = :intitule")
})
@Table(name = "Role", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"intitule"})
})
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String intitule;
	
	/**
	 * Constructeur defaut
	 */
	public Role() {
		super();
	}
	
	/**
	 * Constructeur
	 */
	public Role(String intitule) {
		super();
		this.intitule = intitule;
	}

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * @param intitule the intitule to set
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return intitule;
	}
}
