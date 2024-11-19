package models;

public class Usuario {
    public Integer id;
    public String nome_completo;
    public String email;
    public String senha;

    public Usuario(String nomeCompleto, String email, String senha) {
        this.nome_completo = nomeCompleto;
        this.email = email;
        this.senha = senha;
    }
}