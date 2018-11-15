package controller;

import chadavo.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
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
        LocalDate datanasc = txDataNasc.getValue();
        String nome = txNome.getText();
        //System.out.println(date);
        
        if(usuario.equals("")||nome.equals("")||datanasc == null){
            lbSenha.setText("Campos em branco!!");
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
            
        }else{
            
        }

    }

}
