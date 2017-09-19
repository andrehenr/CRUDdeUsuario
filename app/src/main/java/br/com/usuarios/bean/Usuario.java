package br.com.usuarios.bean;

/**
 * Created by ANDRE on 06/09/2017.
 */

public class Usuario {
    private Long _id;
    private String login,senha,status,tipo,nome;

    public Usuario(){}

    public Usuario(String login, String senha, String status, String tipo, String nome) {
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.tipo = tipo;
        this.nome = nome;
    }

    public Usuario(Long _id, String login, String senha, String status, String tipo, String nome) {
        this._id = _id;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.tipo = tipo;
        this.nome = nome;
    }

    public Usuario(String login, String senha){
        this._id = null;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "status='" + status + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
