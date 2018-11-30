package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import dao.BeneficioDAO;
import dao.ChaDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Beneficio;
import model.Cha;
import model.Usuario;

public class TelaPrincipalAdminController implements Initializable {

    private Usuario user;

    public TelaPrincipalAdminController(Usuario user) {
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
    void btnCadBeneficioAction(ActionEvent event) throws SQLException, IOException {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telacadastrabeneficio.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelacadastrabeneficioController());

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);

    }

    @FXML
    void btnCadChaAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telacadastrocha.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaCadastroChaController(null));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
        //Main.changeScene("cadastrocha");
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
    void btnSairAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telalogin.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaLoginController());

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
    }

    @FXML
    void abreCha(MouseEvent event) throws IOException {
        Cha c = listChas.getSelectionModel().getSelectedItem();
        trocaTela(c);
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

    void initListCha() {

        listChas.getItems().clear();

        ChaDAO dao = new ChaDAO();

        ObservableList<Cha> list = FXCollections.observableArrayList(dao.TodosChas());

        listChas.getItems().addAll(list);
    }

    public void atualizaLista() {
        try {
            initListBeneficio();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipalAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("Olá: "+user.getLoginUsuario());
        try {
            initListBeneficio();
            initListCha();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipalAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void trocaTela(Cha c) throws IOException {
        //Stage stage = new Stage();
//        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telacadastrocha.fxml"));
//
//        fxmlloader.setController(new TelaCadastroChaController(pesquisa));
//
//        //Parent tela = fxmlloader.load();
//
//        stage.setScene(new Scene(tela));
//
//        stage.show();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telacadastrocha.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaCadastroChaController(c));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
    }

    public void trocaTela2(String tela) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telasobre.fxml"));

        fxmlloader.setController(new TelasobreController(tela));

        Parent tela2 = fxmlloader.load();

        stage.setScene(new Scene(tela2));

        stage.show();
    }

    @FXML
    void btnSobreAction(ActionEvent event) throws IOException {
        trocaTela2("telaprincipaladmin");
    }
}
