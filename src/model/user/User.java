package model.user;

public class User {

    private String creatorName;
    private String password;
    private String role;

    public User(String creatorName, String password, String role) {
        this.creatorName = creatorName;
        this.password = password;
        this.role = role;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}