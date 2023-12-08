public class Enseignant implements Comparable<Enseignant> {
    private int id ;
    private String nom;
    private String prenom ;
    private String specialite ;

    public Enseignant(int id, String nom, String prenom, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    public int compareTo(Enseignant o) {
        return this.nom.compareTo(o.getNom());
    }
    public int compareTo(Enseignant o,int x) {
        return this.specialite.compareTo(o.getSpecialite());
    }
}
