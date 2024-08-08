package model.dao;

import dal.DB;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {
    public static UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJDBC(DB.getConnection());
    }
}
