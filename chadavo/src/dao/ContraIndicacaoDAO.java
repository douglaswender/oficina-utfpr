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
import javafx.scene.control.CheckBox;
import model.ContraIndicacao;

/**
 *
 * @author ViniciusBelloli
 */
public class ContraIndicacaoDAO {
    public static ObservableList<ContraIndicacao> pesquisaTodasContra(Boolean lAlteracao, int CodigoCha) throws SQLException {
        Connection con = new Conexao().getConnection();

        String cSQL = "";
        
        if (lAlteracao){
            cSQL = "select con.*, coalesce((select true from contracha where chave_contra = con.cod_contra and chave_contracha = ?), false) as marcado from contra_indicacao as con left join contracha as cont on(cont.chave_contra = con.cod_contra) group by cod_contra order by cod_contra";
        }else{
            cSQL = "SELECT * FROM contra_indicacao order by cod_contra";
        }
        
        PreparedStatement ps = con.prepareStatement(cSQL);

        if (lAlteracao){
            ps.setInt(1, CodigoCha);
        }

        ResultSet rs = ps.executeQuery();
        ObservableList<ContraIndicacao> observableArrayList = null;
        List<ContraIndicacao> retorno = new ArrayList<>();

        try {
            int i = 0;

            while (rs.next()) {
                ContraIndicacao contra = new ContraIndicacao(rs.getInt("cod_contra"), rs.getString("nome_contra"));
                retorno.add(contra);

                if (lAlteracao){
                    CheckBox c = new CheckBox();
                    c.selectedProperty().set(rs.getBoolean("marcado"));
                    contra.setMarcado(c);
                }
                
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


    public static void Gravar(ObservableList<ContraIndicacao> contraIndicacao, int CodigoCha) throws SQLException{        
        int idcha = 0;

        if (CodigoCha == 0){
            Connection con2 = new Conexao().getConnection();
            PreparedStatement stm2 = con2.prepareStatement("SELECT MAX(cod_cha) AS CONTADOR FROM CHAS");
            ResultSet rs = stm2.executeQuery();
            rs.next();
            idcha = rs.getInt(1);
        }else{
            idcha = CodigoCha;
        }
        
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
