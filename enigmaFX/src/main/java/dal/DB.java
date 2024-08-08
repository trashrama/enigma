package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DB {

    static Connection conn = null;

    public static Connection getConnection(){
        if (conn != null){
            String url = "jdbc:mysql://localhost:3306";
            String user = "root";
            String psswd = "99586090";
            try{
                conn = DriverManager.getConnection(url, user, psswd);
                return conn;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return conn;

    }

    private static void closeConnection(Connection c){
        if (c != null){
            try{
                c.close();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    private static void closeStatement(PreparedStatement pst){
        if (pst != null){
            try{
                pst.close();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

}
