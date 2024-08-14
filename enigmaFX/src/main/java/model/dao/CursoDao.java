package model.dao;

import model.entities.Curso;

import java.util.List;

public interface CursoDao {
    void inserir(Curso c);
    void atualizar(Curso c);
    void deletarPorID(int id);
    Curso pesquisarPorID(int ID);
    Curso pesquisarPorInstrutorID(int ID);
    List<Curso> listar();
}
