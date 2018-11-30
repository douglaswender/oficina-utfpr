/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static dao.ChaDAO.pesquisaTodosChas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Cha;

public class ResultadobuscaController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Cha> TbvCha;

    @FXML
    private TableColumn<Cha, String> nomeCha;

    private String pesquisa;

    public ResultadobuscaController(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public void initList(String pesquisa) throws SQLException {
        nomeCha.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TbvCha.setItems(pesquisaTodosChas(pesquisa));
    }

    @FXML
    void onEnterPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            Cha c = TbvCha.getSelectionModel().getSelectedItem();
            //trocaTela(c.getId());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initList(pesquisa);
        } catch (SQLException ex) {
            Logger.getLogger(TelaInfoCha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void trocaTela(Cha pesquisa) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telacadastrocha.fxml"));

        fxmlloader.setController(new TelaCadastroChaController(pesquisa));

        Parent tela = fxmlloader.load();

        stage.setScene(new Scene(tela));

        stage.show();
    }
}
