package com.projetosant.enigmafx.db.model.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.projetosant.enigmafx.utils.Conversao;

public class Usuario {
    private int id;
    private String nome;
    private int lvl_usuario = 0;
    private long xp = 0;
    private LocalDate data_nasc;
    private boolean eh_instrutor = false;
    private String login;
    private String senha;
    private byte[] img = null;
    private List<Curso> cursos_inscritos = new ArrayList<>();

    public Usuario(){}
    public Usuario(String nome, LocalDate data_nasc, String login, String senha) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.login = login;
        this.senha = senha;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setEh_instrutor(boolean eh_instrutor) {
        this.eh_instrutor = eh_instrutor;
    }

    public void setData_nasc(LocalDate data_nasc) {
        this.data_nasc = data_nasc;
    }



    public Usuario(String nome, LocalDate data_nasc, String login, String senha, byte[] img, boolean ehInstrutor, List<Curso> cursos_inscritos) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.login = login;
        this.senha = senha;
        this.img = img;
        this.eh_instrutor = ehInstrutor;
    }

    public Usuario(String nome, LocalDate data_nasc, String login, String senha, byte[] img, boolean ehInstrutor, int id) {
        this.nome = nome;
        this.data_nasc = data_nasc;
        this.login = login;
        this.senha = senha;
        this.img = img;
        this.eh_instrutor = ehInstrutor;
        this.id = id;

    }

    public Usuario(String nome, long xp, LocalDate data_nasc, boolean eh_instrutor, String login, String senha, int lvl_usuario, int id, byte[] img, List<Curso> cursos_inscritos) {
        this.nome = nome;
        this.xp = xp;
        this.data_nasc = data_nasc;
        this.eh_instrutor = eh_instrutor;
        this.login = login;
        this.senha = senha;
        this.lvl_usuario = lvl_usuario;
        this.id = id;
        this.img = img;
        this.cursos_inscritos = cursos_inscritos;

    }

    @Override
    public String toString() {
        return
                "ID: " + id + "\n" +
                "NOME: " + nome + "\n" +
                "LEVEL: " + lvl_usuario + "\n" +
                "XP: " + xp + "\n" +
                "DATA DE NASCIMENTO: " + data_nasc + "\n" +
                "PROFESSOR: " + eh_instrutor + "\n" +
                "LOGIN: " + login + "\n" +
                "SENHA: " + senha.replaceAll(".", "*") + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && lvl_usuario == usuario.lvl_usuario && xp == usuario.xp && eh_instrutor == usuario.eh_instrutor && Objects.equals(nome, usuario.nome) && Objects.equals(data_nasc, usuario.data_nasc) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, lvl_usuario, xp, data_nasc, eh_instrutor, login, senha);
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

    public LocalDate getData_nasc() {
        return data_nasc;
    }


    public boolean isEh_instrutor() {
        return eh_instrutor;
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

    public List<Curso> getCursos_inscritos() {
        return cursos_inscritos;
    }

    public void setCursos_inscritos(List<Curso> cursos_inscritos) {
        this.cursos_inscritos = cursos_inscritos;
    }


}
