package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Usuario;

public class TelaPerfilUsuarioController implements Initializable{

    private Usuario user;

    public TelaPerfilUsuarioController(Usuario user) {
        this.user = user;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXTextField txLogin;

    @FXML
    private JFXPasswordField  txSenha;

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txEmail;

    @FXML
    private ImageView imgBtn;

    @FXML
    private JFXButton btnEntrar;
    
    @FXML
    private JFXButton btnSalvar;
    
    private Usuario u;

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipal.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaPrincipalController(user));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
    }

    @FXML
    void btnSalvarAction(ActionEvent event) throws SQLException {
        boolean retorno;
        u = new Usuario(user.getIdUsuario(), txLogin.getText(), txSenha.getText(), txNome.getText(), txEmail.getText());
        retorno = UsuarioDAO.salvarUsuario(u);
        
        if(retorno){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção!");
            alert.setHeaderText("Usuário "+u.getNomeUsuario()+ " atualizado com sucesso...");
            alert.show();
            user = u;
        }

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {    
        if(user.getEmailUsuario()==null){
            
        }else{
            txEmail.setText(user.getEmailUsuario());
        }
        txLogin.setText(user.getLoginUsuario());
        txNome.setText(user.getNomeUsuario());
        txSenha.setText(user.getSenhaUsuario());
        //System.out.println(user.getIdUsuario());
    }

}
