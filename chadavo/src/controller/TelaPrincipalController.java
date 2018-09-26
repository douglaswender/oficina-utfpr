/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Beneficio;
import model.ChaTable;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author dougl
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private Label lbTexto;

    @FXML
    private JFXListView<Beneficio> listOpc1;

    @FXML
    private TableView<ChaTable> tabela;

    @FXML
    private TableColumn<ChaTable, String> clmCha;

    @FXML
    private JFXTextField txPesquisa;

    @FXML
    private TableColumn<ChaTable, String> clmBeneficio;

    @FXML
    void btnPerquisarAction(ActionEvent event) throws IOException {

        pesquisaPorNome();

    }

    @FXML
    void loadData(ActionEvent event) {

    }

    @FXML
    void btnSairAction(ActionEvent event) {
        Main.changeScene("login");
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

        ObservableList<ChaTable> chas = FXCollections.observableArrayList(dao.Pesquisar(txPesquisa.getText()));

        tabela.setItems(chas);

    }
    
    void pesquisarPorBeneficio(Beneficio b) throws SQLException{
        
        BeneficioDAO dao = new BeneficioDAO();
        
        ObservableList<ChaTable> chas = FXCollections.observableArrayList(dao.pesquisaChaPorBeneficio(b));
        
        tabela.setItems(chas);
    }

    void initListBeneficio() throws SQLException {

        BeneficioDAO dao = new BeneficioDAO();

        ObservableList<Beneficio> list = FXCollections.observableArrayList(dao.pesquisaTodosBeneficios());

        listOpc1.getItems().addAll(list);

    }

    void initTable() {
        ChaDAO dao = new ChaDAO();
        ObservableList<ChaTable> chas = FXCollections.observableArrayList(dao.TodosChas());
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
        clmCha.setCellValueFactory(new PropertyValueFactory<ChaTable, String>("nome"));
        clmBeneficio.setCellValueFactory(new PropertyValueFactory<ChaTable, String>("detalhes"));
        initTable();

        // final TreeItem<Cha> root = new RecursiveTreeItem<Cha>(chas, RecursiveTreeObject::getChildren);
        //Trazendo dados do banco para primeira carregada dos chás
        //AQUI TERÁ QUE TRAZER INFORMAÇÕES DO BANCO, "CATEGORIAS" CREIO EU
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object Data) {
                Usuario usr = (Usuario) Data;
                if (newScreen.equals("principal")) {
                    lbTexto.setText("Olá " + usr.getNomeUsuario() + ", está sentindo alguma coisa? Está a procura de algum chá? Digite aê...");
                    //System.out.println("estou na tela cadastro e os dados são: " + usr.getLoginUsuario());
                }
            }
        });
        // TODO
    }

}
