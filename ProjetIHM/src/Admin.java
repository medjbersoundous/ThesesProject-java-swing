public class Admin {

    private int id_adm;
    private String username;
    private String password;

    public Admin(int id_adm, String username, String password) {
        this.id_adm = id_adm;
        this.username = username;
        this.password = password;
    }

    public Admin() {

    }

    public int getId_adm() {
        return id_adm;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
