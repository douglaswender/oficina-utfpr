/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaLoginController implements Initializable {
    

    @FXML
    private JFXPasswordField txSenha;

    @FXML
    private JFXTextField txLogin;

    @FXML
    private JFXButton btnEntrar;
    
    @FXML
    protected void btnEntrarAction(ActionEvent e){
        String login = txLogin.getText();
        String senha = txSenha.getText();
        
        //usar DAO para pegar os dados e realizar login
        System.out.println(login+ "-"+ senha);
    }
    
    @FXML
    protected void btnBackAction(ActionEvent e){
        Main.changeScene("main");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
