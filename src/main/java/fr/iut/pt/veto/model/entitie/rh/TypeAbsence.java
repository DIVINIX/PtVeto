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

/**
 * Bean pour VETO.TypeAbsence
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "TypeAbsence.findAll", query = "SELECT t from TypeAbsence t"),
	@NamedQuery(name = "TypeAbsence.removeAll", query = "DELETE FROM TypeAbsence"),
	@NamedQuery(name = "TypeAbsence.findById", query = " SELECT t from TypeAbsence t WHERE t.id = :id"),
	@NamedQuery(name = "TypeAbsence.findByName", query = " SELECT t from TypeAbsence t WHERE t.intitule = :intitule")
})
@Table(name = "TypeAbsence")
public class TypeAbsence implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String intitule;
	
	/**
	 * Constructeur defaut
	 */
	public TypeAbsence() {
		super();
	}
	
	/**
	 * 
	 */
	public TypeAbsence(String intitule){
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