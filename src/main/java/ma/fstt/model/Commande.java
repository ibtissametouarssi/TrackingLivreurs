package ma.fstt.model;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private Long id_commande;
    private String date_Commande;
    private String date_livraison;
    private Livreur livreur;
    private List<Produit> produits;

    public Commande() {
        produits = new ArrayList<>();
    }

    public Commande(Long id_commande, String date_Commande, String date_livraison, Livreur livreur, List<Produit> produits) {
        this.id_commande = id_commande;
        this.date_Commande = date_Commande;
        this.date_livraison = date_livraison;
        this.livreur = livreur;
        this.produits = produits;
    }

    public Long getId_commande() {
        return id_commande;
    }

    public String getDate_Commande() {
        return date_Commande;
    }

    public String getDate_livraison() {
        return date_livraison;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public void setDate_Commande(String date_Commande) {
        this.date_Commande = date_Commande;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    public void supprimerProduit(Produit produit) {
        produits.remove(produit);
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" +id_commande + ", date_Commande=" + date_Commande + ", date_livraison=" 
                + date_livraison + ", livreur=" + livreur + ", produits=" + produits + '}';
    }

    
}
