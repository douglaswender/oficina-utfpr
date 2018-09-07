/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ViniciusBelloli
 */
public class TelaCadastroChaController implements Initializable {

    @FXML
    private JFXTextField TxtNome;

    @FXML
    private JFXTextField TxtBeneficio;

    @FXML
    private JFXTextField TxtComposicao;

    @FXML
    protected void btGravarAction(ActionEvent e) throws SQLException{
        Connection con = new Conexao().getConnection();
        System.out.println("Gravou");
        Statement stm = con.createStatement();
        String nome       = TxtNome.getText();
        String beneficio  = TxtBeneficio.getText();
        String composicao = TxtComposicao.getText();
        
        String sql = "INSERT INTO CHA(NOME, BENEFICIO, COMPOSICAO) VALUES('" + nome + "','" + beneficio + "','" + composicao + "')";
        
        stm.close();
        con.close();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
