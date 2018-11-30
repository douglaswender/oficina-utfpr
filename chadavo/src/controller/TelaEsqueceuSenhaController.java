package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.UsuarioDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Usuario;

public class TelaEsqueceuSenhaController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txUsuario;

    @FXML
    private JFXTextField txEmail;

    @FXML
    private JFXButton btnRecuperar;

    @FXML
    private Label lbSenha;

    @FXML
    private ImageView imgBtn;

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telainicio.fxml"));
        // Definindo quem é o controller desse 'fxml':
        //fxmlloader.setController(new TelaLoginController(u));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
    }

    @FXML
    void btnRecuperaAction(ActionEvent event) {

        String usuario = txUsuario.getText();
        String email = txEmail.getText();
        String nome = txNome.getText();
        //System.out.println(date);

        if (usuario.equals("") || nome.equals("") || email.equals("")) {
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

        } else {
            Usuario u = UsuarioDAO.recuperaSenha(new Usuario(usuario, nome, email));

            if (u==null) {
                lbSenha.setText("Usuário não foi confirmado!!");
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção! Anote sua senha");
                alert.setHeaderText("Usuário: " + u.getNomeUsuario() + "\nSenha: "+ u.getSenhaUsuario());
                alert.show();
            }

        }

    }

}
