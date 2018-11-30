package controller;

import chadavo.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dao.BeneficioDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Beneficio;

public class TelacadastrabeneficioController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXTextField txBeneficio;

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private JFXListView<Beneficio> list;

    public void backAction() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipaladmin.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaPrincipalAdminController(null));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        try {
            backAction();
        } catch (IOException ex) {
            Logger.getLogger(TelacadastrabeneficioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @FXML
    void btnCadastrarAction(ActionEvent event) throws SQLException {
        if (txBeneficio.getText().isEmpty()) {
            System.out.println("Está nulo meu parça");
        } else {
            Beneficio b = new Beneficio();
            //System.out.println(txBeneficio.getText());
            b.setNome(txBeneficio.getText());

            BeneficioDAO dao = new BeneficioDAO();

            dao.cadastraBeneficio(b);

            txBeneficio.setText(null);

            try {
                backAction();
            } catch (IOException ex) {
                Logger.getLogger(TelacadastrabeneficioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
