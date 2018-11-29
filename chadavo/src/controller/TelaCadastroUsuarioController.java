/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chadavo.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaCadastroUsuarioController implements Initializable {

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txUsuario;

    @FXML
    private JFXTextField txEmail;

    @FXML
    private JFXPasswordField txSenha;

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private ImageView imgBtn;

    @FXML
    private Label lbSenha;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telainicio.fxml"));
        // Definindo quem é o controller desse 'fxml':
        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);

    }

    @FXML
    void btnCadastrarAction(ActionEvent event) throws SQLException, IOException {

        boolean retorno;

        String nome = txNome.getText();
        String login = txUsuario.getText();
        String senha = txSenha.getText();
        String email = txEmail.getText();

        Usuario usuario = new Usuario(login, senha, nome, email);

        retorno = dao.UsuarioDAO.createUsuario(usuario);

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
        } else {
            usuario = dao.UsuarioDAO.loginUsuario(usuario);
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipal.fxml"));
            // Definindo quem é o controller desse 'fxml':
            fxmlloader.setController(new TelaPrincipalController(usuario));

            AnchorPane a = (AnchorPane) fxmlloader.load();

            anchorpane.getChildren().setAll(a);
            txNome.setText(null);
            txSenha.setText(null);
            txUsuario.setText(null);
            txNome.requestFocus();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
