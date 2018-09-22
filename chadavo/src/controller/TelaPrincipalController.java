/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXListView;
import dao.ChaDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cha;
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
    private JFXListView<Label> listOpc1;

    @FXML
    private TableView<ChaTable> tabela;

    @FXML
    private TableColumn<ChaTable, String> clmCha;

    @FXML
    private TableColumn<ChaTable, String> clmBeneficio;

    @FXML
    void btnSairAction(ActionEvent event) {
        Main.changeScene("login");
    }

    void initTable() {
        clmCha.setCellValueFactory(new PropertyValueFactory<ChaTable, String>("nome"));
        clmBeneficio.setCellValueFactory(new PropertyValueFactory<ChaTable, String>("detalhes"));
        ChaDAO dao = new ChaDAO();
        ObservableList<ChaTable> chas = FXCollections.observableArrayList(dao.TodosChas());
        tabela.setItems(chas);
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
        initTable();

        // final TreeItem<Cha> root = new RecursiveTreeItem<Cha>(chas, RecursiveTreeObject::getChildren);
        //Trazendo dados do banco para primeira carregada dos chás
        //AQUI TERÁ QUE TRAZER INFORMAÇÕES DO BANCO, "CATEGORIAS" CREIO EU
        List<Label> labels = new ArrayList<>();

        try {
            Label lb1 = new Label("Chás calmantes");
            Label lb2 = new Label("Chás para insônia");
            Label lb3 = new Label("Chás para gases");
            Label lb4 = new Label("Chás para cólica");

            listOpc1.getItems().add(lb1);
            listOpc1.getItems().add(lb2);
            listOpc1.getItems().add(lb3);
            listOpc1.getItems().add(lb4);

        } catch (Exception e) {
            System.out.println("Erro " + e);
        }

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
