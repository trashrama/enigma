package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.model.entities.Post;
import com.projetosant.enigmafx.db.model.entities.Usuario;

import java.util.List;

public interface PostDao {
    boolean inserir(int idCurso, Post p);
    void deletarPorID(int id);
    boolean atualizar(Post p);
    List<Post> listarPostsCursosdoUsuario(Usuario u);

}
