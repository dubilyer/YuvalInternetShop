package apihelper.pojo;

public class RolePojo {
    private int id;
    private String role;

    public RolePojo() {
    }

    public RolePojo(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
