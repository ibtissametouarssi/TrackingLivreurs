package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class TestPro {

    public static void main(String[] args) {
    try {
        ProduitDAO ProduitDAO = new ProduitDAO();

        // Ajouter un nouveau produit
        Produit newProduit = new Produit(null, "Nouveau Produit", 10.0f, "Nouvelle description");
        ProduitDAO.save(newProduit);

        // Modifier le produit ajouté
        newProduit.setNom("Produit modifié");
        newProduit.setPrix(20.0f);
        newProduit.setDescription("Description modifiée");
        ProduitDAO.update(newProduit);

        // Récupérer la liste des produits et afficher les produits
        List<Produit> Prolist =  ProduitDAO.getAll();
        for (Produit Pro :Prolist) {
            System.out.println(Pro.toString());
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

}
