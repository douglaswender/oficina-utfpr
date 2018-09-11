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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelacadastrousuarioController implements Initializable {

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txUsuario;


    @FXML
    private JFXPasswordField txSenha;

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private ImageView imgBtn;

    @FXML
    private Label lbSenha;

    @FXML
    void btnBackAction(ActionEvent event) {
        Main.changeScene("main");

    }

    @FXML
    void btnCadastrarAction(ActionEvent event) throws SQLException {
        Usuario usuario = null;
        boolean retorno;

        String nome = txNome.getText();
        String login = txUsuario.getText();
        String senha = txSenha.getText();
        
        
        retorno = dao.UsuarioDAO.createUsuario(login, senha, nome);

        if (!retorno) {
            lbSenha.setText("Nenhum campo pode estar vazio!");
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
        } else{
            usuario = dao.UsuarioDAO.loginUsuario(login, senha);
            Main.changeScene("cadastrocha", usuario);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
