public class Memoir {

    private int id_mem ;
    private String cote ;
    private String titre ;
    private String auteur ;
    private int annes ;
    private String resumer ;
    private int id_ens;

    public Memoir(int id_mem, String cote, String titre, String auteur, int annes, String resumer, int id_ens) {
        this.id_mem=id_mem;
        this.cote = cote;
        this.titre = titre;
        this.auteur = auteur;
        this.annes = annes;
        this.resumer = resumer;
        this.id_ens = id_ens;
    }
    public int getId_mem() {
        return id_mem;
    }
    public String getCote() {
        return cote;
    }
    public void setCote(String cote) {
        this.cote = cote;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public int getAnnes() {
        return annes;
    }
    public void setAnnes(int annes) {
        this.annes = annes;
    }
    public String getResumer() {
        return resumer;
    }
    public void setResumer(String resumer) {
        this.resumer = resumer;
    }
    public int getId_ens() {
        return id_ens;
    }

}
