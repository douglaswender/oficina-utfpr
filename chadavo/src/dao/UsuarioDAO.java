/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author dougl
 */
public class UsuarioDAO {

    
    public static void createUsuario(String login, String senha) throws SQLException{
        try {
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO usuario(login, senha) VALUES (?, ?)");
            stm.setString(1, login);
            stm.setString(2, senha);
        
            stm.execute();
            stm.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static int loginUsuario(String login, String senha){
        try {            
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM usuario WHERE login=?");
            stm.setString(1, login);
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("senha"));

                if(usuario.getSenhaUsuario().equals(senha)){
                    return 1;
                }
            }
            
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
}
