/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class ResultadobuscaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTreeTableView<Chas> TbvCha;

    @FXML
    void initialize() {
        JFXTreeTableColumn<Chas, String> nome = new JFXTreeTableColumn<>("Nome");
        nome.setPrefWidth(150);
        nome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Chas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Chas, String> param) {
                return param.getValue().getValue().nome;
            }
        });

    }
}

class Chas extends RecursiveTreeObject<Chas> {

    StringProperty nome;
    StringProperty brevedescricao;
    StringProperty detalhes;
    StringProperty especificacao_tecnica;
    StringProperty indicacao;
    StringProperty contra_indicacao;
    StringProperty dicas;
    StringProperty prevencao;

    public Chas(String nome, String brevedescricao, String detalhes, String especificacao_tecnica, String indicacao, String contra_indicacao, String dicas, String prevencao) {
        this.nome                  = new SimpleStringProperty(nome);
        this.brevedescricao        = new SimpleStringProperty(brevedescricao);
        this.detalhes              = new SimpleStringProperty(detalhes);
        this.especificacao_tecnica = new SimpleStringProperty(especificacao_tecnica);
        this.indicacao             = new SimpleStringProperty(indicacao);
        this.contra_indicacao      = new SimpleStringProperty(contra_indicacao);
        this.dicas                 = new SimpleStringProperty(dicas);
        this.prevencao             = new SimpleStringProperty(prevencao);                        
    }

}