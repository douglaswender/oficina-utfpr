package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dao.BeneficioDAO;
import dao.ChaDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Beneficio;
import model.Cha;

public class TelaPrincipalAdminController implements Initializable {

    @FXML
    private JFXTextField txPesquisa;

    @FXML
    private Label lbTexto;

    @FXML
    private JFXButton btnCadBeneficio;

    @FXML
    private JFXButton btnCadCha;

    @FXML
    private JFXButton btnListarBeneficios;

    @FXML
    private JFXButton btnListarChas;

    @FXML
    private JFXListView<Cha> listChas;

    @FXML
    private JFXListView<Beneficio> listBeneficios;

    //private Stage stage;
    
    
    @FXML
    void btnCadBeneficioAction(ActionEvent event) throws SQLException {

        Main.changeScene("cadastrobeneficio");
//        Stage stage = new Stage();
//        stage.setScene(Main.retornaSceneCadBeneficio());
//        
//        if (stage.isShowing()) {
//            System.out.println("a janela já esta aberta");
//        } else {
//            stage.show();
//            stage.setTitle("Cadastrar benefício");
//            
//        }
        
        
    }

    @FXML
    void btnCadChaAction(ActionEvent event) {

    }

    @FXML
    void btnListBeneficioAction(ActionEvent event) throws SQLException {
        initListBeneficio();
    }

    @FXML
    void btnListChasAction(ActionEvent event) {
        initListCha();
    }

    @FXML
    void btnPerquisarAction(ActionEvent event) {

    }

    @FXML
    void btnSairAction(ActionEvent event) {
        Main.changeScene("login");
    }

    @FXML
    void listClick(MouseEvent event) {

    }

    @FXML
    void loadData(ActionEvent event) {

    }

    @FXML
    void onEnterPress(KeyEvent event) {

    }

    public Stage getStage() {
        return null;

    }

        void initListBeneficio() throws SQLException {

            listBeneficios.getItems().clear();

            BeneficioDAO dao = new BeneficioDAO();

            ObservableList<Beneficio> list = FXCollections.observableArrayList(dao.pesquisaTodosBeneficios());

            listBeneficios.getItems().addAll(list);

        }
        void initListCha(){
            
            listChas.getItems().clear();
            
            ChaDAO dao = new ChaDAO();
            
            ObservableList<Cha> list = FXCollections.observableArrayList(dao.TodosChas());
            
            listChas.getItems().addAll(list);
        }
    
    public void atualizaLista(){
        try {
            initListBeneficio();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipalAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initListBeneficio();
            initListCha();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipalAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
