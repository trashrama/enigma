package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.model.entities.Post;
import com.projetosant.enigmafx.db.model.entities.Usuario;

import java.util.List;

public interface PostDao {
    boolean inserir(Post p);
    boolean deletarPorID(int id);
    boolean atualizar(Post p);
    List<Post> listarPostsCursosdoUsuario(Usuario u);
    List<Post> listarPostsCursosdoInstrutor(Usuario u);
    List<Post> listarPosts(int idCurso);

}
