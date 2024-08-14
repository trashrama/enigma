package model.dao.impl;

import dal.DB;
import utils.Conversao;
import model.dao.UsuarioDao;
import model.entities.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn = null;

    public UsuarioDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Usuario u) {

        PreparedStatement pst = null;
        int adicionado = 0;
        ResultSet rs = null;

        String in = "INSERT INTO usuario(nome, data_nasc, eh_instrutor, login, senha) VALUES (?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(in, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, u.getNome());
            pst.setDate(2, u.getData_nasc());
            pst.setBoolean(3, u.isEh_prof());
            pst.setString(4, u.getLogin());
            pst.setString(5, u.getSenha());

            adicionado = pst.executeUpdate();

            if(adicionado > 0){
                System.out.println("Foi adicionado com sucesso!");
                rs = pst.getGeneratedKeys();
                if (rs.next()){
                    u.setId(rs.getInt(1));
                    System.out.println("Adicionado com sucesso!");
                    DB.closeResultSet(rs);
                }

            }else{
                System.out.println("Não foi possível inserir!");
            }


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst);
        }


    }

    @Override
    public void deletarPorID(int id) {
        PreparedStatement pst = null;
        int deletado = 0;
        String in = "DELETE FROM usuario where id = ?";



        try {
            pst = conn.prepareStatement(in);
            pst.setInt(1, id);
            deletado = pst.executeUpdate();

            if (deletado > 0){
                System.out.println("DELETADO COM SUCESSO!");
            }else{
                System.out.println("NÃO HÁ USUÁRIOS PARA SEREM DELETADOS");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst);
        }
    }

    @Override
    public void atualizar(Usuario u, int ID) {
        PreparedStatement pst = null;
        String in = "UPDATE usuario SET nome = ?, data_nasc = ?, eh_instrutor = ?, login = ?, senha = ? WHERE id = ?";
        int atualizado = 0;

        try {
            pst = conn.prepareStatement(in);
            pst.setString(1, u.getNome());
            pst.setDate(2, u.getData_nasc());
            pst.setBoolean(3, u.isEh_prof());
            pst.setString(4, u.getLogin());
            pst.setString(5, u.getSenha());
            pst.setInt(6, ID);

            atualizado = pst.executeUpdate();



            if (atualizado > 0){
                System.out.println("ATUALIZADO COM SUCESSO!");
            }else{
                System.out.println("ID NÃO FOI ENCONTRADO NO BANCO DE DADOS");
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
        }
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
                String Data =  Conversao.convInterDatas(rs.getDate("data_nasc"));

                return new Usuario(rs.getString("nome"), rs.getInt("xp"),
                        Data, rs.getBoolean("eh_instrutor"),
                        rs.getString("login"), rs.getString("senha"),
                        rs.getInt("lvl_usuario"), rs.getInt("id"));
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
                String Data =  Conversao.convInterDatas(rs.getDate("data_nasc"));
                Usuario usu = new Usuario(rs.getString("nome"), rs.getInt("xp"),
                       Data, rs.getBoolean("eh_instrutor"),
                        rs.getString("login"), rs.getString("senha"),
                        rs.getInt("lvl_usuario"), rs.getInt("id"));
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
