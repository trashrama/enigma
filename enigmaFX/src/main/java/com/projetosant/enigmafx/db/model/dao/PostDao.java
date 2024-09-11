package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.model.entities.Post;

public interface PostDao {
    boolean inserir(int idCurso, Post p);
    void deletarPorID(int id);
    boolean atualizar(Post p);


}
