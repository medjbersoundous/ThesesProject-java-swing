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
    private List<Admin> admins = new ArrayList<>();
    public static List<Memoir> memr = new ArrayList<>();
    public static List<Memoir> filtermemr = new ArrayList<>();

    public Backend() {
        AllMemoir();
        AllEns();
        getAllAdmins();

    }

    Connection conn() {
        String url = "jdbc:mysql://127.0.0.1:3306/ihm";
        String user = "root";
        String password = "";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // $$$$$$$$$$$$$$$$$ ENSEIGNANT $$$$$$$$$$$$$$$$$$$$$$$$

    public void AllEns() {
        Enseignant en;
        String sql = "select * from enseignant";
        Connection con = conn();
        if (con != null) {
            try {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    en = new Enseignant(resultSet.getInt("id"), resultSet.getString("nom"),
                            resultSet.getString("prenom"), resultSet.getString("specialite"));
                    ens.add(en);
                }
            } catch (SQLException e) {
                System.out.println("Erreur de connection : " + e.getMessage());
            }
            InitialiserEns();
        }
    }

    public void FilterEns(String nom) {
        filterens.clear();
        for (Enseignant personne : ens) {
            if (personne.getNom().equals(nom)) {
                filterens.add(personne);
            }
        }
    }

    public void insererEnseignant(String nom, String prenom, String specialite) {
        Connection connection = conn();

        String query = "INSERT INTO enseignant (nom, prenom, specialite) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, specialite);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            AllEns();
            InitialiserEns();
            connection.close();
        } catch (SQLException ex) {

        }
    }

    public void InitialiserEns() {
        filterens = ens;
    }

    public void supprimerENS(int id) {
        try {
            Connection connection = conn();
            supprimerMemoir(id, "");
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

    public void updateENS(int id, String specialite, String nom, String prenom) {
        try {
            Connection connection = conn();

            String query = "UPDATE enseignant SET specialite = ?, nom = ?, prenom = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, specialite);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, prenom);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // $$$$$$$$$$$$$$$$$$$$$$$Memoir$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    public void insererMemoire(String cote, String titre, String auteur, int annes, String resumer, int id_ens,
            byte[] pdfBytes, String niveau) {
        try {
            Connection connection = conn();
            String query = "INSERT INTO memoir (cote, titre, auteur, annes, resumer, id_ens,pdff, niveau) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, cote);
                preparedStatement.setString(2, titre);
                preparedStatement.setString(3, auteur);
                preparedStatement.setInt(4, annes);
                preparedStatement.setString(5, resumer);
                preparedStatement.setInt(6, id_ens);
                preparedStatement.setBytes(7, pdfBytes);
                preparedStatement.setString(8, niveau);
                preparedStatement.executeUpdate();
            }
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AllMemoir() {
        Memoir m;
        String sql = "select * from Memoir";
        Connection connection = conn();
        if (connection != null) {
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    m = new Memoir(resultSet.getInt("id_mem"),
                            resultSet.getString("cote"),
                            resultSet.getString("titre"),
                            resultSet.getString("auteur"),
                            resultSet.getInt("annes"),
                            resultSet.getString("resumer"),
                            resultSet.getInt("id_ens"),
                            resultSet.getBytes("pdff"),
                            resultSet.getString("niveau"));
                    memr.add(m);
                }
            } catch (SQLException e) {
                System.out.println("Erreur de connection : " + e.getMessage());
            }
            Initialisermemr();
        }
    }

    public void Initialisermemr() {
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

    public void supprimerMemoir(int id, String ens) {
        try {
            Connection connection = conn();

            String query = "DELETE FROM Memoir WHERE id_ens = ?";
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

            String query = "UPDATE Memoir SET cote = ?, titre = ?, auteur = ?, annes = ?, resumer = ?, id_ens = ?, pdff = ?, niveau = ? WHERE id_mem = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, livre.getCote());
                preparedStatement.setString(2, livre.getTitre());
                preparedStatement.setString(3, livre.getAuteur());
                preparedStatement.setInt(4, livre.getAnnes());
                preparedStatement.setString(5, livre.getResumer());
                preparedStatement.setInt(6, livre.getId_ens());
                preparedStatement.setBytes(7, livre.getPdfBytes());
                preparedStatement.setString(8, livre.getNiveau());
                preparedStatement.setInt(9, livre.getId_mem()); // Corrected parameter index
                preparedStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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
                                resultSet.getBytes("pdff"),
                                resultSet.getString("niveau"));
                        resultats.add(livre);
                    }
                }
            }
        } catch (SQLException e) {

        }

        return resultats;
    }

    // $$$$$$$$$$$$$$$$$$$$$$$Admin$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public List<Admin> getAllAdmins() {

        String sql = "SELECT * FROM admin";
        try (Connection connection = conn();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getInt("id_adm"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving admins: " + e.getMessage());
        }

        return admins;
    }

    public boolean checkCredentials(String enteredUsername, String enteredPassword) {
        String sql = "SELECT * FROM Admin WHERE username = ? AND password = ?";
        try (Connection connection = conn();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, enteredUsername);
            preparedStatement.setString(2, enteredPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Error while checking credentials: " + e.getMessage());
            return false;
        }
    }

}
