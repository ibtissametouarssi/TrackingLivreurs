
package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;
import ma.fstt.model.Produit;

public class TestCom {
    public static void main(String[] args) {
        try {
            // test save method
            CommandeDAO commandeDAO = new CommandeDAO();
            Livreur livreur = new Livreur(1L, "John Doe", "0604747874");
            Commande commande = new Commande(1L, "2023-04-02", "2023-04-05", livreur, new ArrayList<>());
            Produit produit1 = new Produit(1L, "Produit 1", 10.5f, "Description produit 1");
            Produit produit2 = new Produit(2L, "Produit 2", 15.75f, "Description produit 2");
            commande.ajouterProduit(produit1);
            commande.ajouterProduit(produit2);
            commandeDAO.save(commande);
            System.out.println("Commande saved successfully.");
            // test getAll method
            List<Commande> commandes = commandeDAO.getAll();
            for (Commande c : commandes) {
                System.out.println("Commande " + c.getId_commande() + ":");
                System.out.println("- Date commande: " + c.getDate_Commande());
                System.out.println("- Date livraison: " + c.getDate_livraison());
                System.out.println("- Livreur: " + c.getLivreur().getNom() + " (" + c.getLivreur().getTelephone() + ")");
                System.out.println("- Produits:");
                for (Produit p : c.getProduits()) {
                    System.out.println("  * " + p.getDescription() + " (prix: " + p.getPrix() + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

