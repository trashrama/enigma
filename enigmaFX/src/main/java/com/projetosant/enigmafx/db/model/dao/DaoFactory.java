package com.projetosant.enigmafx.db.model.dao;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.impl.PostDaoJDBC;
import com.projetosant.enigmafx.db.model.dao.impl.UsuarioDaoJDBC;
import com.projetosant.enigmafx.db.model.dao.impl.CursoDaoJDBC;


public class DaoFactory {
    public static UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJDBC(DB.getConnection());
    }
    public static CursoDao createCursoDao(){
        return new CursoDaoJDBC(DB.getConnection());
    }
    public static PostDao createPostDao(){
        return new PostDaoJDBC(DB.getConnection());
    }


}
