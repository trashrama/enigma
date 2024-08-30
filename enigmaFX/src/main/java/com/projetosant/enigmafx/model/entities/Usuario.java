package com.projetosant.enigmafx.model.entities;

import java.sql.Date;
import java.util.Objects;
import utils.Conversao;

public class Usuario {
    private int id;
    private String nome;
    private int lvl_usuario = 0;
    private long xp = 0;
    private Date data_nasc;
    private boolean eh_prof = false;
    private String login;
    private String senha;

    public Usuario(){}
    public Usuario(String nome, String data_nasc, String login, String senha) {
        this.nome = nome;
        this.data_nasc = Conversao.stringParaData(data_nasc);
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nome, long xp, String data_nasc, boolean eh_prof, String login, String senha, int lvl_usuario, int id) {
        this.nome = nome;
        this.xp = xp;
        this.data_nasc = Conversao.stringParaData(data_nasc);
        this.eh_prof = eh_prof;
        this.login = login;
        this.senha = senha;
        this.lvl_usuario = lvl_usuario;
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "ID: " + id + "\n" +
                "NOME: " + nome + "\n" +
                "LEVEL: " + lvl_usuario + "\n" +
                "XP: " + xp + "\n" +
                "DATA DE NASCIMENTO: " + Conversao.convInterDatas(data_nasc) + "\n" +
                "PROFESSOR: " + eh_prof + "\n" +
                "LOGIN: " + login + "\n" +
                "SENHA: " + senha.replaceAll(".", "*") + "\n";
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

    public java.sql.Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = Conversao.stringParaData(data_nasc) ;
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
