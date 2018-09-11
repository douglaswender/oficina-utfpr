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

    
    public static boolean createUsuario(String login, String senha, String nome) throws SQLException{
        try {
            if(login.equals("")||senha.equals("")||nome.equals("")){
                return false;
            }else{
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO usuario(login, senha, nome) VALUES (?, ?, ?)");
            stm.setString(1, login);
            stm.setString(2, senha);
            stm.setString(3, nome);
        
            stm.execute();
            stm.close();
            return true;
            }
        } catch (Exception e) {
            return false;
            //System.out.println(e);
        }
    }
    public static Usuario loginUsuario(String login, String senha){
        Usuario usuario = null;
        try {            
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT * FROM usuario WHERE login=?");
            stm.setString(1, login);
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){
                
                usuario = new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("senha"), rs.getString("nome"));
                return usuario;
            }
            
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return usuario;
    }
    
}
