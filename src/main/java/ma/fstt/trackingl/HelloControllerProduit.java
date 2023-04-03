package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloControllerProduit implements Initializable {

    @FXML
    private TextField nom ;

    @FXML
    private TextField prix ;

    @FXML
    private TextField description ;

    @FXML
    private TableView<Produit> mytable ;

    @FXML
    private TableColumn<Produit, Long> col_id ;

    @FXML
    private TableColumn<Produit, String> col_nom ;

    @FXML
    private TableColumn<Produit, Float> col_prix ;

    @FXML
    private TableColumn<Produit, String> col_description ;

    @FXML
    protected void onSaveButtonClick() {

        try {
            ProduitDAO produitDAO = new ProduitDAO();

            Produit produit = new Produit(0l, nom.getText(), Float.parseFloat(prix.getText()), description.getText());

            produitDAO.save(produit);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Produit, Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        mytable.setItems(this.getDataProduit());
    }

    public static ObservableList<Produit> getDataProduit() {
        ProduitDAO produitDAO;
        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();
            for (Produit produitTemp : produitDAO.getAll()) {
                listfx.add(produitTemp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
}