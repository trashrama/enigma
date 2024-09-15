package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Usuario;

import java.util.List;
import java.util.Map;

public interface CursoDao {
    void inserir(Curso c);
    boolean atualizar(Curso c);
    boolean deletarPorID(int id);
    Curso pesquisarPorID(int ID);
    List<Curso> pesquisarPorInstrutorID(int ID);
    List<Curso> listar();
    int quantidadeAlunos(int ID);
    boolean matriculaUsuario(Curso c, Usuario u);
    boolean desmatriculaUsuario(Curso c, Usuario u);
    Map<Integer, String> lerCategorias(int id);
}
