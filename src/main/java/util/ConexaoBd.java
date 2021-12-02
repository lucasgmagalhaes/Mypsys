package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.Serializable;

public class ConexaoBd implements Serializable{
    private static Connection conn = null;

    private ConexaoBd() {
    }
    
    private static boolean connect() {
        boolean status = false;

        String driverName = "org.postgresql.Driver";
        String serverName = "mpsys.postgres.database.azure.com";
        String mydatabase = "mpsys";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "adm@mpsys";
        String password = "12345678Pg";
        
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            status = (conn == null);
            System.out.println("Conexao efetuada com o postgres!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexao nao efetuada com o postgres -- Driver nao encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(e);
        }

        return status;
    }

    private static boolean close() {
        boolean status = false;

        try {
            conn.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public static Connection getConnection() throws Exception {
        if (conn == null) {
            connect();
        }
        return conn;
    }

    public static boolean closeConnection() throws Exception {
        if (conn == null) {
            return true;
        } else {
            return close();
        }
    }

}
