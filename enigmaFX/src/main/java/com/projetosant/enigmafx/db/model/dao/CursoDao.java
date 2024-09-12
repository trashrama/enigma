package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Post;
import com.projetosant.enigmafx.db.model.entities.Usuario;

import java.util.List;

public interface CursoDao {
    void inserir(Curso c);
    void atualizar(Curso c);
    void deletarPorID(int id);
    Curso pesquisarPorID(int ID);
    List<Curso> pesquisarPorInstrutorID(int ID);
    List<Curso> listar();
    int quantidadeAlunos(int ID);
    boolean matriculaUsuario(Curso c, Usuario u);
    boolean desmatriculaUsuario(Curso c, Usuario u);

}
