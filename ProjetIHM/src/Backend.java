import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Backend {
    public static List<Enseignant> ens = new ArrayList<>();
    public static List<Enseignant> filterens = new ArrayList<>();
    public Backend() { }
    private Connection conn () {
        String url ="jdbc:mysql://localhost:3306/IHM" ;
        String user ="root" ;
        String password ="" ;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {

        }
        return null;
    }

    // $$$$$$$$$$$$$$$$$ ENSEIGNANT $$$$$$$$$$$$$$$$$$$$$$$$
    public void AllEns(){
        Enseignant en;
        String sql = "select * from enseignant";
        Connection connection = conn();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                en = new Enseignant(resultSet.getInt("id") ,resultSet.getString("nom"),resultSet.getString("prenom")
                        ,resultSet.getString("specialite")) ;
                ens.add(en);
            }
        } catch(SQLException e ) {
            System.out.println("Erreur de connection : "+e.getMessage());
        }
    }

    public void FilterEns (String nom ){
        filterens.clear();
        for (Enseignant personne : ens) {
            if (personne.getNom().equals(nom)) {
                 filterens.add(personne);
            }
        }
    }

    public void InitialiserEns(){
        filterens = ens;
    }
    public void supprimerElement(int id) {
        try {
            Connection connection = conn();

            String query = "DELETE FROM enseignant WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

             preparedStatement.executeUpdate();
            AllEns();
            InitialiserEns();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {

        }
    }
    public void supprimerElement(int id,String specialite) {
        try {
            Connection connection = conn();

            String query = "UPDATE enseignant SET specialite = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, specialite);
            preparedStatement.setInt(2, id);

             preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {

        }
    }

    //$$$$$$$$$$$$$$$$$$$$$$$Memoir$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    public void supprimerLivre(int id) {
        try {
            Connection connection = conn();

            String query = "DELETE FROM Memoir WHERE id_mem = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour modifier un livre dans la base de données
    public void modifierLivre(Memoir livre) {
        try {
            Connection connection = conn();

            String query = "UPDATE Memoir SET cote = ?, titre = ?, auteur = ?, annees = ?, resumer = ?, id_ens = ? WHERE id_mem = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, livre.getCote());
                preparedStatement.setString(2, livre.getTitre());
                preparedStatement.setString(3, livre.getAuteur());
                preparedStatement.setInt(4, livre.getAnnes());
                preparedStatement.setString(5, livre.getResumer());
                preparedStatement.setInt(6, livre.getId_ens());
                preparedStatement.setInt(7, livre.getId_mem());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Memoir> rechercherLivres(String cote, String titre, String encadreur) {
        List<Memoir> resultats = new ArrayList<>();

        try {
            Connection connection = conn();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Memoir");

            if (cote != null && !cote.isEmpty()) {
                queryBuilder.append(" AND cote LIKE ?");
            }
            if (titre != null && !titre.isEmpty()) {
                queryBuilder.append(" AND titre LIKE ?");
            }
            if (encadreur != null && !encadreur.isEmpty()) {
                queryBuilder.append(" AND id_ens IN (SELECT id_ens FROM enseignant WHERE nom LIKE ?)");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
                int paramIndex = 1;

                if (cote != null && !cote.isEmpty()) {
                    preparedStatement.setString(paramIndex++, "%" + cote + "%");
                }
                if (titre != null && !titre.isEmpty()) {
                    preparedStatement.setString(paramIndex++, "%" + titre + "%");
                }
                if (encadreur != null && !encadreur.isEmpty()) {
                    preparedStatement.setString(paramIndex++, "%" + encadreur + "%");
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Memoir livre = new Memoir(
                                resultSet.getInt("id_mem"),
                                resultSet.getString("cote"),
                                resultSet.getString("titre"),
                                resultSet.getString("auteur"),
                                resultSet.getInt("annes"),
                                resultSet.getString("resumer"),
                                resultSet.getInt("id_ens")
                        );
                        resultats.add(livre);
                    }
                }
            }
        } catch (SQLException e) {

        }

        return resultats;
    }

}
