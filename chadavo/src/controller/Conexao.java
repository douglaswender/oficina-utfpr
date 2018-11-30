/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ViniciusBelloli
 */
public class Conexao {
    private static String url   = "jdbc:postgresql://localhost:5432/chadavo";
    private static String user  = "postgres";
    private static String senha = "postgres";

    public Connection getConnection() throws SQLException{
        
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, senha);
            //System.out.println("Conexão efetada com sucesso");
            return con;
        } catch (SQLException ex) {
            //System.out.println("Falha de Conexão " + ex);
            throw new RuntimeException(ex);
        } finally{
            //con.close();
        }
    }
}
