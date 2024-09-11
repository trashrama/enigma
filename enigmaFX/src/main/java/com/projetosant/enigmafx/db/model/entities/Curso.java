package com.projetosant.enigmafx.db.model.entities;

import com.projetosant.enigmafx.utils.Conversao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.projetosant.enigmafx.Application.usuarioLogado;


public class Curso {
    private int id;
    private String titulo;
    private int id_instrutor;
    private LocalDate data_curso;
    private Map<Integer, String> categorias = new HashMap<>(); // hashset pq nao pode se repetir, so pode ter uma chave unica e nos valores igualmente.
    private byte[] img;
    private String descricao;



    @Override
    public String toString() {
        StringBuilder cat = new StringBuilder();

        for(String c: this.categorias.values()){
            cat.append(c).append("\n");
        }

        return "ID: " + this.id + "\n" +
                "TITULO: " + this.titulo + "\n" +
                "DATA DO CURSO: " + this.data_curso + "\n" + "CATEGORIAS: " + "\n" +
                cat;

    }

    public Curso (){};
    public Curso(int id, String titulo, int id_instrutor, LocalDate data_curso, Map<Integer, String> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.id_instrutor = id_instrutor;
        this.data_curso = (data_curso);
        this.categorias = categorias;
    }


    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public Curso(String titulo, String descricao, byte[] img, Map<Integer, String> categorias) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.img = img;
        this.id_instrutor = usuarioLogado.getId();
        this.data_curso = LocalDate.now();
        this.categorias = categorias;
    }

    public Curso(int ID, String titulo, String descricao, byte[] img, int idInstrutor, LocalDate dt, Map<Integer, String> categorias) {
        this.id = ID;
        this.titulo = titulo;
        this.descricao = descricao;
        this.img = img;
        this.id_instrutor = idInstrutor;
        this.data_curso = LocalDate.now();
        this.categorias = categorias;
    }



    public Curso(String titulo, int id_instrutor, LocalDate data_curso, Map<Integer, String> categorias) {
        this.titulo = titulo;
        this.id_instrutor = id_instrutor;
        this.data_curso = (data_curso);
        this.categorias = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id == curso.id && id_instrutor == curso.id_instrutor && Objects.equals(titulo, curso.titulo) && Objects.equals(data_curso, curso.data_curso) && Objects.equals(categorias, curso.categorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, id_instrutor, data_curso, categorias);
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

    public int getId_instrutor() {
        return id_instrutor;
    }

    public void setId_instrutor(int id_instrutor) {
        this.id_instrutor = id_instrutor;
    }

    public LocalDate getData_curso() {
        return data_curso;
    }

    public void setData_curso(LocalDate data_curso) {
        this.data_curso = data_curso;
    }

    public Map<Integer, String> getCategorias() {
        return categorias;
    }

    public String listaCategorias() {
        Collection<String> f = categorias.values();
        return String.join(", ", f);
    }

    public void addCategoria(int ID, String categoria) {
        this.categorias.put(ID, categoria);
    }


}
