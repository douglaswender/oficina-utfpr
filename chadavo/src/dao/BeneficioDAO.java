/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.Conexao;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Beneficio;

/**
 *
 * @author dougl
 */
public class BeneficioDAO {

    public boolean cadastraBeneficio(Beneficio b) throws SQLException {

        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("INSERT INTO beneficios (nome_beneficio) VALUES (?)");

        try {
            ps.setString(1, b.getNome());

            ps.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {

            ps.close();

        }
        return false;
    }

    public boolean atualizaBeneficio(Beneficio b) throws SQLException {

        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("UPDATE beneficios SET nome_beneficio = ? WHERE cod_beneficio = ?");

        try {

            ps.setString(1, b.getNome());
            ps.setInt(2, b.getId());

            ps.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro: " + e);

            return false;
        } finally {
            ps.close();
        }
    }

    public boolean deletaBeneficio(Beneficio b) throws SQLException {

        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM beneficios WHERE cod_beneficio = ?");

        try {
            ps.setInt(1, b.getId());

            ps.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro: " + e);

            return false;

        } finally{
            ps.close();
        }
    }
    
    public List<Beneficio> pesquisaBeneficio(Beneficio b){
        List<Beneficio> retorno = null;
        
        return retorno;
    }

}
