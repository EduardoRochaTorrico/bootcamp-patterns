package mysqlSingleton;

import java.sql.*;
public class MySQLClient {

    public static Connection connection;

    public static Connection getConnection(){
        try{
            if (connection == null) {
                Runtime.getRuntime().addShutdownHook(new getClose());
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo", "root", "");
                System.out.println("entroa al if");
            }
            return connection;
        }catch (Exception e) {
            throw new RuntimeException("Conexion fallida", e);
        }
    }
    static class getClose extends Thread{
        @Override
        public void run(){
            try{
                Connection conn = MySQLClient.getConnection();
                conn.close();
            }catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

}