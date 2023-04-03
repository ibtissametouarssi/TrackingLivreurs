package ma.fstt.trackingl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;
import ma.fstt.model.Livreur;
import ma.fstt.model.Produit;

public class HelloControllerCommande implements Initializable {

    @FXML
    private TextField date_Commande;
    
    @FXML
    private TextField date_livraison;
    
    @FXML
    private TextField livreur_nom;
    
    @FXML
    private TextField livreur_telephone;
     
    @FXML
    private TextField produits;
     
    @FXML
    private TableView<Commande> mytable;
    
    @FXML
    private TableColumn<Commande, Long> col_id;
    
    @FXML
    private TableColumn<Commande, String> col_date_Commande;
    
    @FXML
    private TableColumn<Commande, String> col_date_livraison;
    
    @FXML
    private TableColumn<Commande, Livreur> col_livreur;
    
    @FXML
    private TableColumn<Commande, List<Produit>> col_produits;
    
    @FXML
    protected void onSaveButtonClick() {

        // Accès à la BDD
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            Livreur livreur = new Livreur(0L, livreur_nom.getText(), livreur_telephone.getText());
            List<Produit> produits = new ArrayList<>();
            // Ajouter les produits à la liste des produits
            Commande commande = new Commande(0L, date_Commande.getText(), date_livraison.getText(), livreur, produits);
            commandeDAO.save(commande);

            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Commande, Long>("id_Commande"));
        col_date_Commande.setCellValueFactory(new PropertyValueFactory<Commande, String>("date_Commande"));
        col_date_livraison.setCellValueFactory(new PropertyValueFactory<Commande, String>("date_livraison"));
        col_livreur.setCellValueFactory(new PropertyValueFactory<Commande, Livreur>("livreur"));
        col_produits.setCellValueFactory(new PropertyValueFactory<Commande, List<Produit>>("produits"));

        mytable.setItems(getDataCommande());
    }

    public static ObservableList<Commande> getDataCommande() {
        CommandeDAO commandeDAO = null;
        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            for (Commande commandeTemp : commandeDAO.getAll()) {
                listfx.add(commandeTemp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }
}
