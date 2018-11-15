/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chadavo.Main;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaInicioController implements Initializable {

    //Construtor para ser chamado passando parâmetro:
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnCadastro;

    @FXML
    protected void btLoginAction(ActionEvent e) throws IOException {
        //System.out.println("entrou!");
        //Main.changeScene("login");
        mudaTelaLogin();

    }

    @FXML
    void btnCadastrarAction(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/telacadastrousuario.fxml"));
        anchorpane.getChildren().setAll(a);
    }

    public void fecharJanela() {
        Main.getStage().close();
    }

    public void mudaTelaLogin() throws IOException {
        Usuario u = new Usuario();

        u.setLoginUsuario("nome_teste");
        u.setSenhaUsuario("bene_teste");

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telalogin.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaLoginController());
        
        AnchorPane a = (AnchorPane) fxmlloader.load();
      
        anchorpane.getChildren().setAll(a);


    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
