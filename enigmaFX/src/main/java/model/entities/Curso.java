package model.entities;

import java.util.Date;
import java.util.Objects;

public class Curso {
    private int id;
    private String titulo;
    private int id_instrutor;
    private Date data_curso;

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", id_instrutor=" + id_instrutor +
                ", data_curso=" + data_curso +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id == curso.id && id_instrutor == curso.id_instrutor && Objects.equals(titulo, curso.titulo) && Objects.equals(data_curso, curso.data_curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, id_instrutor, data_curso);
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

    public Date getData_curso() {
        return data_curso;
    }

    public void setData_curso(Date data_curso) {
        this.data_curso = data_curso;
    }

    public Curso(int id, String titulo, int id_instrutor, Date data_curso) {
        this.id = id;
        this.titulo = titulo;
        this.id_instrutor = id_instrutor;
        this.data_curso = data_curso;
    }
}
