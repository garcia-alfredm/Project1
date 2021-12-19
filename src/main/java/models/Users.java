package models;

public class Users {
    Integer id;
    String username;
    String password;
    String firstName;
    String LastName;
    String email;
    /* Foreign Key referenced in dao */
    /* is used when fetching querys from db*/
    String role;
    /* is used when creating users*/
    Integer roleId;

    public Users() {
    }

    /* is used when getting user from db*/
    public Users(Integer id, String username, String password, String firstName, String lastName, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.role = role;
    }

    /* is used when creating user in db*/
    public Users(Integer id, String username, String password, String firstName, String lastName, String email, Integer roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    //no to string impl

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
