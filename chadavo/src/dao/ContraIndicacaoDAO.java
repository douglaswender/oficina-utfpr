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
import javafx.collections.ObservableList;
import model.ContraIndicacao;

/**
 *
 * @author ViniciusBelloli
 */
public class ContraIndicacaoDAO {
    public static ObservableList<ContraIndicacao> pesquisaTodasContra() throws SQLException {
        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM contra_indicacao");

        ResultSet rs = ps.executeQuery();
        ObservableList<ContraIndicacao> observableArrayList = null;
        List<ContraIndicacao> retorno = new ArrayList<>();

        try {
            int i = 0;

            while (rs.next()) {
                ContraIndicacao contra = new ContraIndicacao(rs.getInt("cod_contra"), rs.getString("nome_contra"));
                retorno.add(contra);
                i++;
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


    public static void Gravar(ObservableList<ContraIndicacao> contraIndicacao) throws SQLException{        
        Connection con2 = new Conexao().getConnection();
        PreparedStatement stm2 = con2.prepareStatement("SELECT MAX(cod_cha) AS CONTADOR FROM CHAS");
        ResultSet rs = stm2.executeQuery();
        rs.next();
        int idcha = rs.getInt(1);
        
        Connection con3 = new Conexao().getConnection();
        PreparedStatement stm3 = con3.prepareStatement("DELETE FROM CONTRACHA WHERE CHAVE_CONTRACHA = ?");
        stm3.setInt(1, idcha);
        stm3.execute();
        stm3.close();        

        for(int i = 0; i < contraIndicacao.size(); i++){
            String cSqlExecute;
            
            if(contraIndicacao.get(i).getMarcado().isSelected()){
                Connection con = new Conexao().getConnection();
                cSqlExecute = "INSERT INTO CONTRACHA(CHAVE_CONTRA, CHAVE_CONTRACHA) VALUES(?, ?)";

                int id      = contraIndicacao.get(i).getId();
                PreparedStatement stm = con.prepareStatement(cSqlExecute);
                stm.setInt(1, id);
                stm.setInt(2, idcha);
                stm.execute();
                stm.close();
            }
        }
    }
}
