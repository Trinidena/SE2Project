package model.user;

public class User {

	private String creatorName;
	private String password;
	private String role;

	public User(String creatorName, String password, String role) {
		this.setCreatorName(creatorName);
		this.setPassword(password);
		this.setRole(role);
	}

    public String getCreatorName() {
        return this.creatorName;
    }

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

    public String getPassword() {
        return this.password;
    }

	public void setPassword(String password) {
		this.password = password;
	}

    public String getRole() {
        return this.role;
    }

	public void setRole(String role) {
		this.role = role;
	}
}