package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dao.BeneficioDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Beneficio;

public class TelacadastrabeneficioController implements Initializable {

    @FXML
    private JFXTextField txBeneficio;

    @FXML
    private JFXButton btnCadastrar;

    @FXML
    private JFXListView<Beneficio> list;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }


    @FXML
    void btnCadastrarAction(ActionEvent event) throws SQLException {
        Beneficio b = new Beneficio();
        //System.out.println(txBeneficio.getText());
        b.setNome(txBeneficio.getText());

        BeneficioDAO dao = new BeneficioDAO();

        dao.cadastraBeneficio(b);

        txBeneficio.setText(null);
        
        Main.changeScene("principaladmin");
      

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
