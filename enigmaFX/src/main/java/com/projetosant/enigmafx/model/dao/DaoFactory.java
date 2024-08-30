package com.projetosant.enigmafx.model.dao;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.model.dao.impl.UsuarioDaoJDBC;
import com.projetosant.enigmafx.model.dao.impl.CursoDaoJDBC;


public class DaoFactory {
    public static UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJDBC(DB.getConnection());
    }
    public static CursoDao createCursoDao(){
        return new CursoDaoJDBC(DB.getConnection());
    }

}
