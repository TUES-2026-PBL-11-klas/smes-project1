package backend.model;

public class User {
    private String username;
    private Role role;
    private String password;
    private String email; 
    

    public User(String username, Role role, String email, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.email = email;
    }

    public Role getRole() { return role; }
    public String getUsername() { return username; }
    public String getPassword () {return password; }
    public String getEmail() {return email;}

}