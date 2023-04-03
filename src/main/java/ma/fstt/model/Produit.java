/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.fstt.model;

public class Produit {
    private Long id_produit ;
    private String nom;
    private Float prix ;
    private String description ;

    public Produit() {
    }

    public Produit(Long id_produit, String nom, float prix, String description) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    public Long getId_produit() {
        return id_produit;
    }

    public String getNom() {
        return nom;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom=" + nom + ", prix=" + prix + ", description=" + description + '}';
    }

    
}
