package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import java.util.List;

public interface UsuarioDao {
    boolean inserir(Usuario u);
    void atualizar(Usuario u, int ID);
    void deletarPorID(int id);
    Usuario pesquisarPorID(int ID);
    List<Usuario> listar();
    List<Curso> getInscricoes(int ID);
}
