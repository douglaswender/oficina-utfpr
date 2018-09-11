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
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javax.swing.SwingUtilities;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaLoginController implements Initializable {

    @FXML
    private JFXTextField txLogin;

    @FXML
    private JFXPasswordField txSenha;

    @FXML
    private JFXButton btnEntrar;

    @FXML
    private Label lbSenha;

    @FXML
    private ImageView imgBtn;

    @FXML
    protected void btnEntrarAction(ActionEvent e) throws SQLException {
        realizarLogin();
    }

    @FXML
    protected void btnBackAction(ActionEvent e) {
        Main.changeScene("main");
    }

    @FXML
    void onEnterPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            realizarLogin();
        }
    }

    @FXML
    protected void realizarLogin() {
        Usuario retorno = null;
        String login = txLogin.getText();
        String senha = txSenha.getText();

        //usar DAO para pegar os dados e realizar login
        retorno = UsuarioDAO.loginUsuario(login, senha);
        //System.out.println(retorno);
        if (retorno == null || !retorno.getSenhaUsuario().equals(senha)) {
            
            lbSenha.setText("Usuário ou senha inválidos!");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lbSenha.setText(null);
                        }
                    });
                }
            }, 5000, 5000);
            //System.out.println("Usuário ou senha incorretos!");
        }else {
            Main.changeScene("cadastrocha", retorno);
            //System.out.println("ChangeScene");
        }
            //System.out.println(login+ "-"+ senha);
        }

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize
        (URL url, ResourceBundle rb
        
        
    

) {
        // TODO
    }

}
