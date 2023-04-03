package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            // Ajouter un nouveau livreur
            Livreur liv = new Livreur(0L, "liv3", "200000000");
            livreurDAO.save(liv);

            // Afficher tous les livreurs
            List<Livreur> livlist =  livreurDAO.getAll();
            for (Livreur livreur : livlist) {
                System.out.println(livreur.toString());
            }

            // Modifier un livreur existant
            Livreur liv2 = new Livreur(1L, "liv2_modifié", "100000001");
            livreurDAO.update(liv2);

            // Supprimer un livreur
            Livreur liv3 = new Livreur(2L, "", "");
            livreurDAO.delete(liv3);

            // Afficher un livreur spécifique
            Livreur liv4 = livreurDAO.getOne(3L);
            System.out.println(liv4.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
