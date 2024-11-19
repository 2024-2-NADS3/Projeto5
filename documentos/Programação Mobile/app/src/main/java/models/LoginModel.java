package models;

public class LoginModel {
    public String email;
    public String senha;

    public LoginModel(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}