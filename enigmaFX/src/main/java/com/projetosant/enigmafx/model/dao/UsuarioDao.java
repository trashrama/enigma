package com.projetosant.enigmafx.model.dao;

import com.projetosant.enigmafx.model.entities.Usuario;
import java.util.List;

public interface UsuarioDao {
    void inserir(Usuario u);
    void atualizar(Usuario u, int ID);
    void deletarPorID(int id);
    Usuario pesquisarPorID(int ID);
    List<Usuario> listar();
}
