package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit>{
    
    public ProduitDAO() throws SQLException {
    super();
}

@Override
public void save(Produit object) throws SQLException {
    String request = "INSERT INTO produit (nom , prix , description) VALUES (?, ?, ?)";

    // mapping objet table
    this.preparedStatement = this.connection.prepareStatement(request);
    // mapping
    this.preparedStatement.setString(1, object.getNom());
    this.preparedStatement.setFloat(2, object.getPrix());
    this.preparedStatement.setString(3, object.getDescription());
    this.preparedStatement.execute();
}

@Override
public void update(Produit object) throws SQLException {
String request = "UPDATE produit SET nom = ?, prix = ?, description = ? WHERE id = ?";

// mapping objet table
this.preparedStatement = this.connection.prepareStatement(request);
// mapping
this.preparedStatement.setString(1, object.getNom());
this.preparedStatement.setFloat(2, object.getPrix());
this.preparedStatement.setString(3, object.getDescription());
this.preparedStatement.setLong(4, object.getId_produit());
this.preparedStatement.executeUpdate();
}

@Override
public void delete(Produit object) throws SQLException {
String request = "DELETE FROM produit WHERE id = ?";

// mapping objet table
this.preparedStatement = this.connection.prepareStatement(request);
// mapping
this.preparedStatement.setLong(1, object.getId_produit());
this.preparedStatement.executeUpdate();
}

@Override
public List<Produit> getAll() throws SQLException {
    List<Produit> mylist = new ArrayList<Produit>();
    String request = "SELECT * FROM produit ";
    this.statement = this.connection.createStatement();
    this.resultSet = this.statement.executeQuery(request);
    // parcours de la table
    while (this.resultSet.next()) {
        // mapping table objet
        mylist.add(new Produit(this.resultSet.getLong(1), this.resultSet.getString(2),
                this.resultSet.getInt(3), this.resultSet.getString(4)));
    }
    return mylist;
}

@Override
public Produit getOne(Long id) throws SQLException {
    return null;
}

}
  
