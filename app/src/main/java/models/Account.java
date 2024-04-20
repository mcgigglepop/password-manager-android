package models;

public class Account {
    private int id;
    private String title;
    private String accountType;
    private String username;
    private String password;

    public int getId() {
        // returns the record id
        return id;
    }
    public void setId(int id) {
        // sets the record id
        this.id = id;
    }
    public String getTitle() {
        // returns the record title
        return title;
    }
    public void setTitle(String title) {
        // sets the record title
        this.title = title;
    }
    public String getAccountType() {
        // returns the record account type
        return accountType;
    }
    public void setAccountType(String accountType) {
        // sets the record account type
        this.accountType = accountType;
    }
    public String getUsername() {
        // returns the record username
        return username;
    }
    public void setUsername(String username) {
        // sets the record username
        this.username = username;
    }
    public String getPassword() {
        // returns the record password
        return password;
    }
    public void setPassword(String password) {
        // sets the record password
        this.password = password;
    }
}