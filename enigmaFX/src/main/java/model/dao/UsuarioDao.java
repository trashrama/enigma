package model.dao;

import model.entities.Usuario;
import java.util.List;

public interface UsuarioDao {
    void inserir(Usuario u);
    void deletar(Usuario c);
    void atualizar(Usuario c);
    Usuario pesquisar(Usuario c);
    List<Usuario> listar();
}
