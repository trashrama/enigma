package model.dao;

import dal.DB;
import model.dao.impl.UsuarioDaoJDBC;
import model.dao.impl.CursoDaoJDBC;


public class DaoFactory {
    public static UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJDBC(DB.getConnection());
    }
    public static CursoDao createCursoDao(){
        return new CursoDaoJDBC(DB.getConnection());
    }

}
