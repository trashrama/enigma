package model.entities;

import java.util.Date;
import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    private int lvl_usuario;
    private long xp;
    private Date data_nasc;
    private boolean eh_prof;
    private String login;
    private String senha;

    public Usuario(String nome, long xp, Date data_nasc, boolean eh_prof, String login, String senha, int lvl_usuario, int id) {
        this.nome = nome;
        this.xp = xp;
        this.data_nasc = data_nasc;
        this.eh_prof = eh_prof;
        this.login = login;
        this.senha = senha;
        this.lvl_usuario = lvl_usuario;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", lvl_usuario=" + lvl_usuario +
                ", xp=" + xp +
                ", data_nasc=" + data_nasc +
                ", eh_prof=" + eh_prof +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && lvl_usuario == usuario.lvl_usuario && xp == usuario.xp && eh_prof == usuario.eh_prof && Objects.equals(nome, usuario.nome) && Objects.equals(data_nasc, usuario.data_nasc) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, lvl_usuario, xp, data_nasc, eh_prof, login, senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLvl_usuario() {
        return lvl_usuario;
    }

    public void setLvl_usuario(int lvl_usuario) {
        this.lvl_usuario = lvl_usuario;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public boolean isEh_prof() {
        return eh_prof;
    }

    public void setEh_prof(boolean eh_prof) {
        this.eh_prof = eh_prof;
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
}
