/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import chadavo.Main;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Beneficio;
import model.Cha;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaPrincipalController implements Initializable {

    private Usuario user;

    public TelaPrincipalController(Usuario user) {
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
    private Label lbTexto;

    @FXML
    private JFXListView<Beneficio> listOpc1;

    @FXML
    private TableView<Cha> tabela;

    @FXML
    private TableColumn<Cha, String> clmCha;

    @FXML
    private JFXTextField txPesquisa;

    @FXML
    private TableColumn<Cha, String> clmBeneficio;

    @FXML
    void btnPerquisarAction(ActionEvent event) throws IOException {

        pesquisaPorNome();

    }

    @FXML
    void abreCha(MouseEvent event) throws IOException {
        Cha c = tabela.getSelectionModel().getSelectedItem();
        //System.out.println(c.getId());
        trocaTela(c);
    }

    public void trocaTela(Cha c) throws IOException {
        
        System.out.println(c.getDescricao_cha());
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telainfocha.fxml"));
        
        fxmlloader.setController(new TelaInfoCha(c));

        Parent tela = fxmlloader.load();
        
        stage.setScene(new Scene(tela));
        
        stage.show();
    }

    @FXML
    void loadData(ActionEvent event) {

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
    void listClick(MouseEvent event) throws SQLException {
        Beneficio b = listOpc1.getSelectionModel().getSelectedItem();
        //System.out.println(b.getId()+b.getNome());
        pesquisarPorBeneficio(b);

    }

    @FXML
    void onEnterPress(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            pesquisaPorNome();
        }

    }

    void pesquisaPorNome() throws IOException {

        ChaDAO dao = new ChaDAO();

        ObservableList<Cha> chas = FXCollections.observableArrayList(dao.Pesquisar(txPesquisa.getText()));

        tabela.setItems(chas);

    }

    void pesquisarPorBeneficio(Beneficio b) throws SQLException {

        BeneficioDAO dao = new BeneficioDAO();

        ObservableList<Cha> chas = FXCollections.observableArrayList(dao.pesquisaChaPorBeneficio(b));

        tabela.setItems(chas);
    }

    void initListBeneficio() throws SQLException {

        BeneficioDAO dao = new BeneficioDAO();

        ObservableList<Beneficio> list = FXCollections.observableArrayList(dao.pesquisaTodosBeneficios());

        listOpc1.getItems().addAll(list);

    }

    void initTable() {
        ChaDAO dao = new ChaDAO();
        ObservableList<Cha> chas = FXCollections.observableArrayList(dao.TodosChas());
        tabela.setItems(chas);
    }

    void clickList() {
        listOpc1.setOnMouseClicked(e -> {

        });
    }

    //data
    //ObservableList<Cha> chas = null;
    //chas = ChaDAO.TodosChas();
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initListBeneficio();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clmCha.setCellValueFactory(new PropertyValueFactory<Cha, String>("nome"));
        clmBeneficio.setCellValueFactory(new PropertyValueFactory<Cha, String>("descricao_cha"));
        initTable();

        // final TreeItem<Cha> root = new RecursiveTreeItem<Cha>(chas, RecursiveTreeObject::getChildren);
        //Trazendo dados do banco para primeira carregada dos chás
        //AQUI TERÁ QUE TRAZER INFORMAÇÕES DO BANCO, "CATEGORIAS" CREIO EU
        lbTexto.setText("Olá " + user.getNomeUsuario() + ", está sentindo alguma coisa? Está a procura de algum chá? Digite aê...");
        // TODO
    }

}
