package fr.iut.pt.veto.model.entitie.inventaire;

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

import fr.iut.pt.veto.model.entitie.individu.Client;
import fr.iut.pt.veto.model.entitie.individu.Salarie;

/**
 * Bean pour VETO.Vente
 * 
 * @author sebboursier
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Vente.findAll", query = "SELECT v from Vente v"),
	@NamedQuery(name = "Vente.findById", query = "SELECT v from Vente v WHERE v.id = :id"),
	@NamedQuery(name = "Vente.findByProduit", query = "SELECT v from Vente v WHERE v.produit.id like :id"),
	@NamedQuery(name = "Vente.findByClient", query = "SELECT v from Vente v WHERE v.client.id like :id"),
	@NamedQuery(name = "Vente.findBySalarie", query = "SELECT v from Vente v WHERE v.salarie.id like :id"),
	@NamedQuery(name = "Vente.removeAll", query = "DELETE FROM Vente")
})
@Table(name = "Vente")
public class Vente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = false)
	private Produit produit;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = false)
	private Client client;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Basic(optional = false)
	private Salarie salarie;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Basic(optional = false)
	private int quantite;
	
	@Basic(optional = false)
	private Double prix;
	
	/**
	 * Constructeur defaut
	 */
	public Vente() {
		super();
	}
	
	/**
	 * Constructeur Full
	 * @param produit
	 * @param client
	 * @param salarie
	 * @param date
	 * @param quantite
	 * @param prix
	 */
	public Vente(Produit produit, Client client, Salarie salarie, Date date, int quantite, Double prix) {
		super();
		this.produit = produit;
		this.client = client;
		this.salarie = salarie;
		this.date = date;
		this.quantite = quantite;
		this.prix = prix;
	}


	/**
	 * @return the produit
	 */
	public Produit getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(Produit produit) {
		this.produit = produit;
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
	 * @return the salarie
	 */
	public Salarie getSalarie() {
		return salarie;
	}

	/**
	 * @param salarie the salarie to set
	 */
	public void setSalarie(Salarie salarie) {
		this.salarie = salarie;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
