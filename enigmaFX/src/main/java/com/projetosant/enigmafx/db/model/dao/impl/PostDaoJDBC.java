package com.projetosant.enigmafx.db.model.dao.impl;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.PostDao;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Post;

import java.sql.*;

public class PostDaoJDBC implements PostDao {
    private Connection conn = null;

    public PostDaoJDBC(Connection conn){
        this.conn = conn;
    }


    public boolean atualizar(Post p) {

        PreparedStatement pst = null;

        ResultSet rs = null;
        int adicionado = 0;
        int deletado = 0;

        String in = "UPDATE post SET titulo = ?, conteudo = ?, aula = ?, id_curso = ?, eh_aula = ? where id = ?";

        // tem que tratar a possivel excecao da categoria inserida, por algum motivo nao existir
        try {
            pst = conn.prepareStatement(in);


            pst.setString(1, p.getTitulo());
            pst.setString(2, p.getConteudo());
            pst.setBytes(3, p.getAula());
            pst.setInt(4, p.getId());
            pst.setBoolean(5, p.isEh_aula());


            adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                System.out.println("Foi atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Não foi possível inserir!");
            }



        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(pst);
        }
        return false;
    }

    public void deletarPorID(int id){ // isso aqui eu jogo no DAO? ou será que deixo o DAO só pro CRUD?
        PreparedStatement pst = null;
        String del = "DELETE from post where id_post = ?";
        int deletado = 0;

        try {
            pst = conn.prepareStatement(del);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst);
        }



    }
    public boolean inserir(int idCurso, Post p) {

        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;

        ResultSet rs = null;
        int adicionado = 0;

        String inPost = "INSERT INTO post(titulo, conteudo, aula, id_curso, eh_aula) VALUES (?,?,?,?,?)";

        // tem que tratar a possivel excecao da categoria inserida, por algum motivo nao existir
        try {
            pst1 = conn.prepareStatement(inPost, Statement.RETURN_GENERATED_KEYS);

            pst1.setString(1, p.getTitulo());
            pst1.setString(2, p.getConteudo());
            pst1.setBytes(3, p.getAula());
            pst1.setInt(4, idCurso);
            pst1.setBoolean(5, p.isEh_aula());



            adicionado = pst1.executeUpdate();

            if(adicionado > 0){
                System.out.println("Foi adicionado com sucesso!");
                rs = pst1.getGeneratedKeys(); // retorna pra mim as chaves primarias geradas
                if (rs.next()){
                    p.setId(rs.getInt(1));
                    return true;
                }

            }else{
                System.out.println("Não foi possível inserir!");
            }


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(pst1);
            DB.closeStatement(pst2);
        }
        return false;
    }
}
