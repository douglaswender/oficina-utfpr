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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import model.Beneficio;
import model.Cha;
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

        } finally {
            ps.close();
        }
    }

    public List<Cha> pesquisaChaPorBeneficio(Beneficio b) throws SQLException {
        List<Cha> retorno = new ArrayList<>();
        //System.out.println("Benefício: "+b.getId()+" "+b.getNome());
        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT DISTINCT c.cod_cha, c.nome_cha, c.beneficios FROM chas c "
                + "JOIN benecha bc on bc.chave_benecha = c.cod_cha "
                + "JOIN beneficios b on b.cod_beneficio = bc.chave_beneficio "
                + " WHERE b.cod_beneficio = ?");
        
        ps.setInt(1, b.getId());
        
        try {
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Cha c = new Cha(rs.getInt("cod_cha"), rs.getString("nome_cha"), rs.getString("beneficios"));
                retorno.add(c);
            }
        } catch (Exception e) {
            System.out.println("ERRO: #" + e);
            return null;
        } finally{
            //rs.close();
            ps.close();
            con.close();
        }

        return retorno;
    }

    public static List<Beneficio> pesquisaTodosBeneficios() throws SQLException {
        List<Beneficio> retorno = new ArrayList<>();

        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM beneficios");

        ResultSet rs = ps.executeQuery();

        try {

            while (rs.next()) {
                Beneficio b = new Beneficio(rs.getInt("cod_beneficio"), rs.getString("nome_beneficio"));
                //System.out.println(b.getId() + "#" + b.getNome());
                retorno.add(b);
            }
            return retorno;
        } catch (SQLException e) {
            System.out.println("ERRO: #" + e);
            return null;
        } finally {
            ps.close();
        }

    }

    public static ObservableList<Beneficio> pesquisaTodosBeneficios2() throws SQLException {
        

        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM beneficios");

        ResultSet rs = ps.executeQuery();
        ObservableList<Beneficio> observableArrayList = null;
        List<Beneficio> retorno = new ArrayList<>();

        try {

            while (rs.next()) {
                Beneficio b = new Beneficio(false, rs.getString("nome_beneficio"));
                retorno.add(b);
                //observableArrayList = FXCollections.observableArrayList(new Beneficio(false, rs.getString("nome_beneficio")));
            }

            observableArrayList = FXCollections.observableArrayList(retorno);

            return observableArrayList;
        } catch (SQLException e) {
            System.out.println("ERRO: #" + e);
            return null;
        } finally {
            ps.close();
        }

    }

}
