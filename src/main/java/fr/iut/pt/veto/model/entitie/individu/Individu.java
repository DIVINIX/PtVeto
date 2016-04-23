/**
 * 
 */
package fr.iut.pt.veto.model.entitie.individu;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.iut.pt.veto.model.entitie.geo.Ville;

/**
 * Bean pour VETO.Individu
 * 
 * @author sebboursier
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Individu.findAll", query = "SELECT i from Individu i"),
	@NamedQuery(name = "Individu.findById", query = "SELECT i from Individu i WHERE i.id = :id"),
	@NamedQuery(name = "Individu.findByNom", query = "SELECT i from Individu i WHERE i.nom LIKE :nom"),
	@NamedQuery(name = "Individu.findByPrenom", query = "SELECT i from Individu i WHERE i.prenom LIKE :prenom"),
	@NamedQuery(name = "Individu.findByTelephone", query = "SELECT i from Individu i WHERE i.telephone LIKE :telephone"),
	@NamedQuery(name = "Individu.findByVille", query = "SELECT i from Individu i WHERE i.ville.nom LIKE :nom"),
	@NamedQuery(name = "Individu.removeAll", query = "DELETE FROM Individu")
})
@Table(name = "Individu")
@Inheritance(strategy=InheritanceType.JOINED)
public class Individu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Basic(optional = false)
	private String nom;
	
	@Basic(optional = false)
	private String prenom;
	
	@Basic(optional = true)
	private String telephone;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = true)
	private Ville ville;
	
	@Basic(optional = true)
	private String adresse;
	
	@Basic(optional = true)
	private String mail;

	/**
	 * Constructeur defaut
	 */
	public Individu() {
		super();
	}

	/**
	 * @param nom
	 * @param prenom
	 */
	public Individu(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the ville
	 */
	public Ville getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(Ville ville) {
		this.ville = ville;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
