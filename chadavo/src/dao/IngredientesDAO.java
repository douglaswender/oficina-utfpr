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
import model.Ingredientes;

/**
 *
 * @author ViniciusBelloli
 */
public class IngredientesDAO {
    public static ObservableList<Ingredientes> pesquisaTodosIngredientes() throws SQLException {
        Connection con = new Conexao().getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM ingredientes");

        ResultSet rs = ps.executeQuery();
        ObservableList<Ingredientes> observableArrayList = null;
        List<Ingredientes> retorno = new ArrayList<>();

        try {
            int i = 0;

            while (rs.next()) {
                Ingredientes ing = new Ingredientes(rs.getInt("cod_ingrediente"), rs.getString("nome_ingrediente"));
                retorno.add(ing);
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

    public static void Gravar(ObservableList<Ingredientes> ingredientes) throws SQLException{        
        Connection con2 = new Conexao().getConnection();
        PreparedStatement stm2 = con2.prepareStatement("SELECT MAX(cod_cha) AS CONTADOR FROM CHAS");
        ResultSet rs = stm2.executeQuery();
        rs.next();
        int idcha = rs.getInt(1);
        
        Connection con3 = new Conexao().getConnection();
        PreparedStatement stm3 = con3.prepareStatement("DELETE FROM INGRECHA WHERE CHAVE_BENECHA = ?");
        stm3.setInt(1, idcha);
        stm3.execute();
        stm3.close();        

        for(int i = 0; i < ingredientes.size(); i++){
            String cSqlExecute;
            
            if(ingredientes.get(i).getMarcado().isSelected()){
                Connection con = new Conexao().getConnection();
                cSqlExecute = "INSERT INTO INGRECHA(CHAVE_INGRE, CHAVE_INGRECHA) VALUES(?, ?)";

                int id      = ingredientes.get(i).getId();
                PreparedStatement stm = con.prepareStatement(cSqlExecute);
                stm.setInt(1, id);
                stm.setInt(2, idcha);
                stm.execute();
                stm.close();
            }
        }
    }
}
