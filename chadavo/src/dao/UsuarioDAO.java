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

    
    public static boolean createUsuario(Usuario usuario) throws SQLException{
//        System.out.println(usuario.getLoginUsuario());
//        System.out.println(usuario.getSenhaUsuario());
//        System.out.println(usuario.getNomeUsuario());
        try {
            if(usuario.getLoginUsuario().equals("")||usuario.getSenhaUsuario().equals("")||usuario.getNomeUsuario().equals("")){
                return false;
            }else{
            Connection con = new Conexao().getConnection();
            PreparedStatement stm = con.prepareStatement("INSERT INTO usuario(login, senha, nome) VALUES (?, ?, ?)");
            stm.setString(1, usuario.getLoginUsuario());
            stm.setString(2, usuario.getSenhaUsuario());
            stm.setString(3, usuario.getNomeUsuario());
        
            stm.execute();
            stm.close();
            return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
            
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
