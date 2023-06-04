package model;

public class User {
    private int id;
    private String password;
    private String username;

    public User(String username, String password) {
		super();
		this.password = password;
		this.username = username;
	}

	public User() {
		super();
	}

	public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
