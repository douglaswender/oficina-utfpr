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
import dao.UsuarioDAO;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaLoginController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

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

    public TelaLoginController() {
    }

    @FXML
    protected void btnEntrarAction(ActionEvent e) throws SQLException, IOException {
        realizarLogin();
    }

    @FXML
    protected void btnBackAction(ActionEvent e) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telainicio.fxml"));
        // Definindo quem é o controller desse 'fxml':
        //fxmlloader.setController(new TelaLoginController(u));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
    }

    @FXML
    void onEnterPress(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            realizarLogin();
        }
    }

    @FXML
    void hlCliqueAction(ActionEvent event) {
        Main.changeScene("recuperar");
    }

    @FXML
    protected void realizarLogin() throws IOException {
        Usuario retorno = null;
        String login = txLogin.getText();
        String senha = txSenha.getText();

        Usuario u = new Usuario(login, senha);

        //usar DAO para pegar os dados e realizar login
        retorno = UsuarioDAO.loginUsuario(u);
        //System.out.println(retorno);
        if (retorno == null || !retorno.getSenhaUsuario().equals(senha)) {

            lbSenha.setText("Usuário ou senha inválidos!");
            txSenha.setText(null);
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
            txLogin.setText(null);
            txSenha.setText(null);
            txLogin.requestFocus();
            if ("admin".equals(login)) {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipaladmin.fxml"));
                // Definindo quem é o controller desse 'fxml':
                fxmlloader.setController(new TelaPrincipalAdminController(retorno));

                AnchorPane a = (AnchorPane) fxmlloader.load();

                anchorpane.getChildren().setAll(a);
            } else {

                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipal.fxml"));
                // Definindo quem é o controller desse 'fxml':
                fxmlloader.setController(new TelaPrincipalController(retorno));

                AnchorPane a = (AnchorPane) fxmlloader.load();
                
                anchorpane.getChildren().setAll(a);
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
