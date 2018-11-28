/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ViniciusBelloli
 */
public class TelasobreController implements Initializable {

    SimpleStringProperty tela;
    
    TelasobreController(String tela) {
        this.tela = new SimpleStringProperty(tela);
    }

    @FXML
    private AnchorPane anchorpane;

    @FXML
    void btnBackAction(ActionEvent event) throws SQLException, IOException {
        /*
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipaladmin.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaPrincipalAdminController(null));
        AnchorPane a = (AnchorPane) fxmlloader.load();
        anchorpane.getChildren().setAll(a);
         */
        Scene Scene = anchorpane.getScene();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
