/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static dao.ChaDAO.pesquisaTodosChas;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public void initList(String pesquisa) throws SQLException{
        nomeCha.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TbvCha.setItems(pesquisaTodosChas(pesquisa));
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            initList(pesquisa);
        } catch (SQLException ex) {
            Logger.getLogger(TelaInfoCha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}