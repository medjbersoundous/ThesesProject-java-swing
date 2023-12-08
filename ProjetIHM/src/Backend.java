import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Backend {
    public static List<Enseignant> ens = new ArrayList<>();
    public static List<Enseignant> filterens = new ArrayList<>();
    public static List<Memoir> memr = new ArrayList<>();
    public static List<Memoir> filtermemr = new ArrayList<>();
    public Backend() {
        AllMemoir();
        AllEns();
    }
     Connection conn () {
        String url ="jdbc:mysql://127.0.0.1:3306/ihm" ;
        String user ="root";
        String password ="" ;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // $$$$$$$$$$$$$$$$$ ENSEIGNANT $$$$$$$$$$$$$$$$$$$$$$$$

    public void AllEns(){
        Enseignant en;
        String sql = "select * from enseignant";
        Connection con = conn();
        if (con != null) {
            try {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    en = new Enseignant(resultSet.getInt("id") ,resultSet.getString("nom"),resultSet.getString("prenom")
                            ,resultSet.getString("specialite")) ;
                    ens.add(en);
                }
            } catch(SQLException e ) {
                System.out.println("Erreur de connection : "+e.getMessage());
            }
            InitialiserEns();
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
    public void insererEnseignant(Enseignant e) {
            Connection connection = conn();

            String query = "INSERT INTO enseignant (nom, prenom, specialite) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, e.getNom());
                preparedStatement.setString(2, e.getPrenom());
                preparedStatement.setString(3, e.getSpecialite());

                preparedStatement.executeUpdate();
                preparedStatement.close();
                AllEns();
                InitialiserEns();
                connection.close();
            }
         catch (SQLException ex) {

        }
    }

    public void InitialiserEns(){
        filterens = ens;
    }
    public void supprimerENS(int id) {
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
    public void supprimerENS(int id,String specialite) {
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

    public void insererMemoire( Memoir m) {
        try {

            Connection connection = conn();

            String query = "INSERT INTO memoir (cote, titre, auteur, annes, resumer, id_ens) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, m.getCote());
                preparedStatement.setString(2, m.getTitre());
                preparedStatement.setString(3, m.getAuteur());
                preparedStatement.setInt(4, m.getAnnes());
                preparedStatement.setString(5, m.getResumer());
                preparedStatement.setInt(6, m.getId_ens());
                preparedStatement.executeUpdate();

            }
            connection.close();

        } catch ( SQLException ex) {

        }
    }
    public void AllMemoir(){
        Memoir m;
        String sql = "select * from Memoir";
        Connection connection = conn();
        if (connection != null) {
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    m = new Memoir(resultSet.getInt("id_mem") ,
                            resultSet.getString("cote"),
                            resultSet.getString("titre")
                            ,resultSet.getString("auteur"),
                            resultSet.getInt("annes"),
                            resultSet.getString("resumer"),
                            resultSet.getInt("id_ens"),
                            resultSet.getBytes("pdff")
                            ) ;
                    memr.add(m);
                }
            } catch(SQLException e ) {
                System.out.println("Erreur de connection : "+e.getMessage());
            }

            Initialisermemr();
        }

    }
    public void Initialisermemr(){
        filtermemr = memr;
    }

    public void supprimerMemoir(int id) {
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

    public void modifierMemoir(Memoir livre) {
        try {
            Connection connection = conn();

            String query = "UPDATE Memoir SET cote = ?, titre = ?, auteur = ?, annees = ?, resumer = ?, id_ens = ?, pdff= ? WHERE id_mem = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, livre.getCote());
                preparedStatement.setString(2, livre.getTitre());
                preparedStatement.setString(3, livre.getAuteur());
                preparedStatement.setInt(4, livre.getAnnes());
                preparedStatement.setString(5, livre.getResumer());
                preparedStatement.setInt(6, livre.getId_ens());
                preparedStatement.setInt(7, livre.getId_mem());
                preparedStatement.setBytes(8, livre.getPdfBytes());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Memoir> rechercherMemoir(String cote, String titre, String encadreur) {
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
                                resultSet.getInt("id_ens"),
                                resultSet.getBytes("pdff")
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
