package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField tele;

    @FXML
    private TableView<Livreur> mytable;

    @FXML
    private TableColumn<Livreur, Long> col_id;

    @FXML
    private TableColumn<Livreur, String> col_nom;

    @FXML
    private TableColumn<Livreur, String> col_tele;

    @FXML
    private Button saveButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    protected void onSaveButtonClick() {
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(0L, nom.getText(), tele.getText());
            livreurDAO.save(liv);
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();
        if (selectedLivreur != null) {
            try {
                selectedLivreur.setNom(nom.getText());
                selectedLivreur.setTelephone(tele.getText());
                LivreurDAO livreurDAO = new LivreurDAO();
                livreurDAO.update(selectedLivreur);
                updateTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();
        if (selectedLivreur != null) {
            try {
                LivreurDAO livreurDAO = new LivreurDAO();
                livreurDAO.delete(selectedLivreur);
                updateTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur, Long>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur, String>("telephone"));
        mytable.setItems(getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs() {
        LivreurDAO livreurDAO = null;
        ObservableList<Livreur> listfx = FXCollections.observableArrayList();
        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll()) {
                listfx.add(ettemp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        mytable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nom.setText(newValue.getNom());
                tele.setText(newValue.getTelephone());
            } else {
                nom.setText("");
                tele.setText("");
            }
        });
    }
}
