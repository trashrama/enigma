package com.projetosant.enigmafx.db.model.dao.impl;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.utils.Alerta;
import com.projetosant.enigmafx.utils.Conversao;
import com.projetosant.enigmafx.db.model.dao.UsuarioDao;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.projetosant.enigmafx.Application.usuarioLogado;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn = null;

    public UsuarioDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean inserir(Usuario u) {
        System.out.println(u.isEh_instrutor());
        String sql = "insert into usuario(nome, data_nasc, login, senha, img, eh_instrutor) values (?,?,?,?,?,?)";
        Connection conexao = DB.getConnection();
        PreparedStatement pst;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, u.getNome());
            pst.setDate(2, Date.valueOf(u.getData_nasc()));
            pst.setString(3, u.getLogin());
            pst.setString(4, u.getSenha());
            pst.setBytes(5, u.getImg());
            pst.setBoolean(6, u.isEh_instrutor());



            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "ENIGMA - Cadastro", "Cadastro efetuado com sucesso!");
            } else {
                Alerta.exibirAlerta(Alert.AlertType.ERROR, "ENIGMA - Cadastro", "Não foi possível cadastrar!");
                return false;
            }



        } catch (java.sql.SQLIntegrityConstraintViolationException e1) {
            Alerta.exibirAlerta(Alert.AlertType.ERROR, "ERRO",  "O usuário já está cadastrado no sistema");
            return false;
        } catch (Exception e) {
            Alerta.exibirAlerta(Alert.AlertType.ERROR, "ERRO",  e.toString());
            return false;
        }

        return true;
    }



    @Override
    public boolean deletarPorID(int id) {
        PreparedStatement pst = null;
        int deletado = 0;
        String in = "DELETE FROM usuario where id = ?";


        try {
            pst = conn.prepareStatement(in);
            pst.setInt(1, id);
            deletado = pst.executeUpdate();

            if (deletado > 0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst);
        }
        return false;
    }

    public List<Curso> getInscricoes(int idUsu) {
        List<Curso> lista_inscricoes = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String in = "select * from curso inner join curso_aluno on curso.id = curso_aluno.id_curso where id_aluno = ?";
        try {
            pst = conn.prepareStatement(in);
            pst.setInt(1, idUsu);
            rs = pst.executeQuery();

            while(rs.next()){
                lista_inscricoes.add(new Curso(rs.getInt("id"), rs.getString("titulo"),
                        rs.getInt("id_instrutor"), rs.getDate("data_curso").toLocalDate(), DaoFactory.createCursoDao().lerCategorias(rs.getInt("id"))));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return lista_inscricoes;

    }
    @Override
    public boolean atualizar(Usuario u, int ID) {
        PreparedStatement pst = null;
        String in = "UPDATE usuario SET nome = ?, data_nasc = ?, eh_instrutor = ?, login = ?, senha = ?, img = ?, xp = ?, lvl_usuario = ? WHERE id = ?";
        int atualizado = 0;

        try {
            pst = conn.prepareStatement(in);
            pst.setString(1, u.getNome());
            pst.setDate(2, Date.valueOf(u.getData_nasc()));
            pst.setBoolean(3, u.isEh_instrutor());
            pst.setString(4, u.getLogin());
            pst.setString(5, u.getSenha());
            pst.setBytes(6, u.getImg());
            pst.setInt(9, ID);
            pst.setLong(7, u.getXp());
            pst.setInt(8, u.getLvl_usuario());


            atualizado = pst.executeUpdate();



            if (atualizado > 0){
                return true;
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
        }
        return false;
    }


    @Override
    public Usuario pesquisarPorID(int ID) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String in = "SELECT * from usuario where id = ?";
        try{
            pst = conn.prepareStatement(in);
            pst.setInt(1, ID);
            rs = pst.executeQuery();

            if(rs.next()){
                return new Usuario(rs.getString("nome"), rs.getInt("xp"),
                        rs.getDate("data_nasc").toLocalDate(), rs.getBoolean("eh_instrutor"),
                        rs.getString("login"), rs.getString("senha"),
                        rs.getInt("lvl_usuario"), rs.getInt("id"), rs.getBytes("img"), getInscricoes(rs.getInt("id")));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return null;
    }




    @Override
    public List<Usuario> listar() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String in = "SELECT * from usuario ";
        List<Usuario> l = new ArrayList<Usuario>();
        try{
            pst = conn.prepareStatement(in);
            rs = pst.executeQuery();

            while(rs.next()){
                Usuario usu = new Usuario(rs.getString("nome"), rs.getInt("xp"),
                        rs.getDate("data_nasc").toLocalDate(), rs.getBoolean("eh_instrutor"),
                        rs.getString("login"), rs.getString("senha"),
                        rs.getInt("lvl_usuario"), rs.getInt("id"), rs.getBytes("img"), getInscricoes(rs.getInt("id")));
                l.add(usu);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return l;
    }
}
