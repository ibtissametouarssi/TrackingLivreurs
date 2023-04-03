package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {

    public CommandeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Commande object) throws SQLException {

        String request = "insert into commande (id_commande, date_commande, date_livraison, id_livreur) values (?, ?, ?, ?)";
        // mapping object to table
        this.preparedStatement = this.connection.prepareStatement(request);

        this.preparedStatement.setLong(1, object.getId_commande());
        this.preparedStatement.setString(2, object.getDate_Commande());
        this.preparedStatement.setString(3, object.getDate_livraison());
        this.preparedStatement.setLong(4, object.getLivreur().getId_livreur());

        // execute update
        this.preparedStatement.executeUpdate();

        // insert produit-commande association
        for (Produit produit : object.getProduits()) {
            String produitRequest = "insert into produit_commande (id_commande, id_produit) values (?, ?)";
            this.preparedStatement = this.connection.prepareStatement(produitRequest);
            this.preparedStatement.setLong(1, object.getId_commande());
            this.preparedStatement.setLong(2, produit.getId_produit());
            this.preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Commande object) throws SQLException {
    String request = "update commande set date_commande=?, date_livraison=?, id_livreur=? where id_commande=?";
    this.preparedStatement = this.connection.prepareStatement(request);
    this.preparedStatement.setString(1, object.getDate_Commande());
    this.preparedStatement.setString(2, object.getDate_livraison());
    this.preparedStatement.setLong(3, object.getLivreur().getId_livreur());
    this.preparedStatement.setLong(4, object.getId_commande());
    this.preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Commande object) throws SQLException {
    String request = "delete from commande where id_commande=?";
    this.preparedStatement = this.connection.prepareStatement(request);
    this.preparedStatement.setLong(1, object.getId_commande());
    this.preparedStatement.executeUpdate();
    }

    @Override
    public List<Commande> getAll() throws SQLException {

        List<Commande> mylist = new ArrayList<Commande>();

        String request = "select c.id_commande, c.date_commande, c.date_livraison, l.id_livreur, p.id_produit, p.designation, p.prix_unitaire, p.quantite_stock from commande c " +
                "inner join livreur l on c.id_livreur = l.id_livreur " +
                "inner join produit_commande pc on c.id_commande = pc.id_commande " +
                "inner join produit p on pc.id_produit = p.id_produit " +
                "order by c.id_commande";

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(request);

        Long currentId = null;
        Commande commande = null;

        while (this.resultSet.next()) {
            Long id_commande = this.resultSet.getLong("id_commande");
            String date_commande = this.resultSet.getString("date_commande");
            String date_livraison = this.resultSet.getString("date_livraison");

            // create new commande object if the current row's id is different from the previous row's id
            if (!id_commande.equals(currentId)) {
                Livreur livreur = new Livreur(this.resultSet.getLong("id_livreur"), "", "");
                commande = new Commande(id_commande, date_commande, date_livraison, livreur, new ArrayList<>());
                mylist.add(commande);
                currentId = id_commande;
            }

            Produit produit = new Produit(
                    this.resultSet.getLong("id_produit"),
                    this.resultSet.getString("nom"),
                    this.resultSet.getFloat("prix"),
                    this.resultSet.getString("description")
            );
            commande.ajouterProduit(produit);
        }
        return mylist;
    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }
}
