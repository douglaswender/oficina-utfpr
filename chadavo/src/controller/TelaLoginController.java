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
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Hyperlink hlClique;

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
    void hlCliqueAction(ActionEvent event) {
        Main.changeScene("recuperar");
    }

    @FXML
    protected void realizarLogin() {
        Usuario retorno = null;
        String login = txLogin.getText();
        String senha = txSenha.getText();
        
        Usuario u = new Usuario(login, senha);

        //usar DAO para pegar os dados e realizar login
        retorno = UsuarioDAO.loginUsuario(u);
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
        } else {
            if ("admin".equals(login)) {
                txLogin.setText(null);
                txSenha.setText(null);
                txLogin.requestFocus();
                Main.changeScene("cadastrocha", retorno);
            } else {
                txLogin.setText(null);
                txSenha.setText(null);
                txLogin.requestFocus();
                Main.changeScene("principal", retorno);
            }

            //System.out.println("ChangeScene");
        }
        //System.out.println(login+ "-"+ senha);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txLogin.requestFocus();
    }

}
