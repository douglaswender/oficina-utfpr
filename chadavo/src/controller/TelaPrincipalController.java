/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private JFXListView<Label> listOpc1;

    @FXML
    private JFXListView<?> listOpc2;
    
    @FXML
    void btnSairAction(ActionEvent event) {
        Main.changeScene("login");
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        //AQUI TERÁ QUE TRAZER INFORMAÇÕES DO BANCO, "CATEGORIAS" CREIO EU
        List<Label> labels = new ArrayList<>();
        
        
        try {
            Label lb1 = new Label("Chás calmantes");
            Label lb2 = new Label("Chás para insônia");
            Label lb3 = new Label("Chás para gases");
            Label lb4 = new Label("Chás para cólica");
            
            
            listOpc1.getItems().add(lb1);
            listOpc1.getItems().add(lb2);
            listOpc1.getItems().add(lb3);
            listOpc1.getItems().add(lb4);
            
            
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        // TODO
    }

}
