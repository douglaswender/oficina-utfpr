/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.UsuarioDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Usuario;

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
    private Label lbSenha;
    
    @FXML
    protected void btnEntrarAction(ActionEvent e) throws SQLException{
        
        Usuario retorno=null;
        String login = txLogin.getText();
        String senha = txSenha.getText();
        
        //usar DAO para pegar os dados e realizar login
        retorno = UsuarioDAO.loginUsuario(login, senha);
        //System.out.println(retorno);
        if(retorno == null || !retorno.getSenhaUsuario().equals(senha)){
            lbSenha.setText("Usuário ou senha inválidos!");
            //System.out.println("Usuário ou senha incorretos!");
        }else{
            Main.changeScene("cadastro", retorno);
            //System.out.println("ChangeScene");
        }
        //System.out.println(login+ "-"+ senha);
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
