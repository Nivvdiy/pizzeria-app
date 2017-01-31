package fr.pizzeria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Boisson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private BigDecimal prix;
	private String urlImage;
	@Enumerated(EnumType.STRING)
	private CategorieBoisson categBoisson;

	public Boisson() {

	}

	public Boisson(String nom, BigDecimal prix, String urlImage, CategorieBoisson categBoisson) {
		super();

		this.nom = nom;
		this.prix = prix;
		this.urlImage = urlImage;
		this.categBoisson = categBoisson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrl_Image(String urlImage) {
		this.urlImage = urlImage;
	}

	public CategorieBoisson getCategBoisson() {
		return categBoisson;
	}

	public void setCategBoisson(CategorieBoisson categBoisson) {
		this.categBoisson = categBoisson;
	}

}