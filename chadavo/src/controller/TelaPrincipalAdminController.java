package controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class TelaPrincipalAdminController {

    @FXML
    private JFXTextField txPesquisa;

    @FXML
    private Label lbTexto;

    @FXML
    private JFXListView<?> listOpc1;

    @FXML
    private TableView<?> tabela;

    @FXML
    private TableColumn<?, ?> clmCha;

    @FXML
    private TableColumn<?, ?> clmBeneficio;

    @FXML
    void btnPerquisarAction(ActionEvent event) {

    }

    @FXML
    void btnSairAction(ActionEvent event) {

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

}
