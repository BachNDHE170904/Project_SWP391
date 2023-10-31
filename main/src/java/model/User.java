/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class User {

    private String username, pass, email;
    private int userId;
    private boolean isAuthorized;
    private String status;
    public User() {
    }

    public User(String username, String pass, String email, int userId, boolean isAuthorized) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.userId = userId;
        this.isAuthorized = isAuthorized;
    }

    public User(String username, String pass, String email, boolean isAuthorized) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.isAuthorized = isAuthorized;
    }

    public User(String username, String pass, boolean isAuthorized) {
        this.username = username;
        this.pass = pass;
        this.isAuthorized = isAuthorized;
    }

    public User(String username, String pass, String email, int userId, boolean isAuthorized, String status) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.userId = userId;
        this.isAuthorized = isAuthorized;
        this.status = status;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String username, String pass, int userId, boolean isAuthorized) {
        this.username = username;
        this.pass = pass;
        this.userId = userId;
        this.isAuthorized = isAuthorized;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", pass=" + pass + ", email=" + email + ", userId=" + userId + ", isAuthorized=" + isAuthorized + '}';
    }

}
