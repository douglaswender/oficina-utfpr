package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TelaEsqueceuSenhaController {

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txUsuario;

    @FXML
    private JFXDatePicker txDataNasc;

    @FXML
    private JFXButton btnRecuperar;

    @FXML
    private Label lbSenha;

    @FXML
    private ImageView imgBtn;

    @FXML
    void btnBackAction(ActionEvent event) {
        Main.changeScene("login");
    }

    @FXML
    void btnRecuperaAction(ActionEvent event) {
        
        String usuario = txUsuario.getText();
        //String datanasc = txDataNasc.getValue().toString();
        String nome = txNome.getText();
        //System.out.println(datanasc);
        
        if(usuario.equals("")||nome.equals("")){
            lbSenha.setText("Campos em branco!");
        }

    }

}
