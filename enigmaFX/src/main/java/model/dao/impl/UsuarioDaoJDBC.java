package model.dao.impl;

import model.dao.UsuarioDao;
import model.entities.Usuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn = null;

    public UsuarioDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Usuario u) {

    }

    @Override
    public void deletar(Usuario c) {

    }

    @Override
    public void atualizar(Usuario c) {

    }

    @Override
    public Usuario pesquisar(Usuario c) {
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return List.of();
    }
}
