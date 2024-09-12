package com.projetosant.enigmafx.db.model.entities;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Post {
    private int id;
    private String titulo;
    private String conteudo = null;
    private byte[] aula = null;
    private int id_curso;
    private boolean eh_aula;
    private LocalDate data_post = LocalDate.now();

    public Post(int id, String titulo, String conteudo, byte[] aula, int id_curso, boolean eh_aula) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.aula = aula;
        this.id_curso = id_curso;
        this.eh_aula = eh_aula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, conteudo, Arrays.hashCode(aula), id_curso, eh_aula);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public byte[] getAula() {
        return aula;
    }

    public void setAula(byte[] aula) {
        this.aula = aula;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public boolean isEh_aula() {
        return eh_aula;
    }

    public void setEh_aula(boolean eh_aula) {
        this.eh_aula = eh_aula;
    }
}
