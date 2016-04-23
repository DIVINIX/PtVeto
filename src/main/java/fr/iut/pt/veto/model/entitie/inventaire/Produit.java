/**
 * 
 */
package fr.iut.pt.veto.model.entitie.inventaire;

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
 * Bean pour VETO.Produit
 * 
 * @author sebastien
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Produit.findAll", query = "SELECT p from Produit p"),
	@NamedQuery(name = "Produit.findById", query = "SELECT p from Produit p WHERE p.id = :id"),
	@NamedQuery(name = "Produit.findByNom", query = "SELECT p from Produit p WHERE p.nom like :nom"),
	@NamedQuery(name = "Produit.findByPrix", query = "SELECT p from Produit p WHERE p.prix_reel like :prix_reel"),
	@NamedQuery(name = "Produit.removeAll", query = "DELETE FROM Produit")
})
@Table(name = "Produit", uniqueConstraints = { @UniqueConstraint(columnNames = { "nom" }) })
public class Produit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Basic(optional = false)
	private String nom;

	@Basic(optional = true)
	private Double prix_reel;
	
	@Basic(optional = true)
	private int stock;

	/**
	 * Constructeur defaut
	 */
	public Produit() {
		super();
	}
	
	/**
	 * Constructeur 
	 */
	public Produit(String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prix_reel
	 */
	public Double getPrix_reel() {
		return prix_reel;
	}

	/**
	 * @param prix_reel
	 *            the prix_reel to set
	 */
	public void setPrix_reel(Double prix_reel) {
		this.prix_reel = prix_reel;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
}
