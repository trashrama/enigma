package com.projetosant.enigmafx.model.dao;

import com.projetosant.enigmafx.model.entities.Curso;

import java.util.List;

public interface CursoDao {
    void inserir(Curso c);
    void atualizar(Curso c);
    void deletarPorID(int id);
    Curso pesquisarPorID(int ID);
    List<Curso> pesquisarPorInstrutorID(int ID);
    List<Curso> listar();
}
