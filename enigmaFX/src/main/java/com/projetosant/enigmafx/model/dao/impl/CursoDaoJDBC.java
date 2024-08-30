package com.projetosant.enigmafx.model.dao.impl;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.model.dao.CursoDao;
import com.projetosant.enigmafx.model.entities.Curso;
import utils.Conversao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CursoDaoJDBC implements CursoDao {
    private Connection conn = null;

    public CursoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Curso c) {

        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;

        ResultSet rs = null;
        int adicionado = 0;

        String inCursos = "INSERT INTO curso(titulo, id_instrutor, data_curso) VALUES (?,?,?)";

        // tem que tratar a possivel excecao da categoria inserida, por algum motivo nao existir
        try {
            pst1 = conn.prepareStatement(inCursos, Statement.RETURN_GENERATED_KEYS);

            pst1.setString(1, c.getTitulo());
            pst1.setInt(2, c.getId_instrutor());
            pst1.setDate(3, c.getData_curso());



            adicionado = pst1.executeUpdate();

            if(adicionado > 0){
                System.out.println("Foi adicionado com sucesso!");
                rs = pst1.getGeneratedKeys(); // retorna pra mim as chaves primarias geradas
                if (rs.next()){
                    c.setId(rs.getInt(1));
                    DB.closeResultSet(rs);
                }

            }else{
                System.out.println("Não foi possível inserir!");
            }

            inserirCategorias(c);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst1);
            DB.closeStatement(pst2);
        }
    }

    @Override
    public void atualizar(Curso c) {
        //deletar cursos do id e depois add eles dnv

        PreparedStatement pst = null;

        ResultSet rs = null;
        int adicionado = 0;
        int deletado = 0;

        String in = "UPDATE curso SET titulo = ?, id_instrutor = ?, data_curso = ? where id = ?";

        // tem que tratar a possivel excecao da categoria inserida, por algum motivo nao existir
        try {
            pst = conn.prepareStatement(in);

            pst.setString(1, c.getTitulo());
            pst.setInt(2, c.getId_instrutor());
            System.out.println(c.getId_instrutor());
            pst.setDate(3, c.getData_curso());
            pst.setInt(4, c.getId());


            adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                System.out.println("Foi atualizado com sucesso!");
            } else {
                System.out.println("Não foi possível inserir!");
            }

            deletarCategorias(c.getId());
            inserirCategorias(c);



        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(pst);
        }
    }
    private void inserirCategorias(Curso c){
        PreparedStatement pst = null;
        String inCat = "INSERT INTO cursos_categorias(id_curso, id_categoria) VALUES (?,?)";
        int adicionado = 0;

        try{
            pst= conn.prepareStatement(inCat);
            for (Integer id_categoria : c.getCategorias().keySet()) {
                pst.setInt(1, c.getId());
                pst.setInt(2, id_categoria);
                adicionado = pst.executeUpdate();
                //if (adicionado > 0)
                    //System.out.println("Categoria adicionada com sucesso!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
        }


    }


    // Essa função vai apagar todas as categorias d'uma vez só, pq como é uma superchave ai eu nao consigo apagar so uma..
    private void deletarCategorias(int id){ // isso aqui eu jogo no DAO? ou será que deixo o DAO só pro CRUD?
        PreparedStatement pst = null;
        String del = "DELETE from cursos_categorias where id_curso = ?";
        int deletado = 0;

        try {
            pst = conn.prepareStatement(del);
            pst.setInt(1, id);
            deletado = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst);
        }



    }

    private Map<Integer, String> lerCategorias(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Map<Integer, String> categorias = new HashMap<Integer, String>();
        String in = "select cc.id_categoria, c.nome from cursos_categorias cc inner join categoria_c c on cc.id_categoria = c.id where cc.id_curso = ?";
        try {
            pst = conn.prepareStatement(in);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while(rs.next()){
                categorias.put(rs.getInt(1), rs.getString(2));
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return categorias;

    }



    @Override
    public void deletarPorID(int id) {
        PreparedStatement pst = null;
        String del = "DELETE from curso where id = ?";
        int deletado = 0;

        try {
            pst = conn.prepareStatement(del);
            pst.setInt(1, id);
            deletado = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DB.closeStatement(pst);
        }

        deletarCategorias(id);

    }

    @Override
    public Curso pesquisarPorID(int ID) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String in = "SELECT * from curso where id = ?";
        try{
            pst = conn.prepareStatement(in);
            pst.setInt(1, ID);
            rs = pst.executeQuery();

            if(rs.next()){
                String Data = Conversao.convInterDatas(rs.getDate("data_curso"));
                return new Curso(ID, rs.getString("titulo"),
                        rs.getInt("id_instrutor"), Data, lerCategorias(ID));
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
    public List<Curso> pesquisarPorInstrutorID(int id_instrutor) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Curso> lc = new ArrayList<Curso>();
        String in = "SELECT * from curso where id_instrutor = ?";
        try{
            pst = conn.prepareStatement(in);
            pst.setInt(1, id_instrutor);
            rs = pst.executeQuery();

            while(rs.next()){
                String Data = Conversao.convInterDatas(rs.getDate("data_curso"));
                lc.add(new Curso(rs.getInt("id"), rs.getString("titulo"),
                        rs.getInt("id_instrutor"), Data, lerCategorias(rs.getInt("id"))));

            }


            return lc;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Curso> listar() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String in = "SELECT * from curso";
        List<Curso> lc = new ArrayList<>();
        try{
            pst = conn.prepareStatement(in);
            rs = pst.executeQuery();

            while(rs.next()){
                String Data = Conversao.convInterDatas(rs.getDate("data_curso"));
                lc.add(new Curso(rs.getInt("id"), rs.getString("titulo"),
                        rs.getInt("id_instrutor"), Data, lerCategorias(rs.getInt("id"))));
            }
            return lc;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }
}



