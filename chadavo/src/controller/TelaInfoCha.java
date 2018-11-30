/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import dao.BeneficioDAO;
import dao.ChaDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Beneficio;
import model.Cha;
import model.Ingrediente;

/**
 *
 * @author dougl
 */
public class TelaInfoCha implements Initializable {

    private Cha cha;

    public TelaInfoCha(Cha cha) {
        this.cha = cha;
    }

    public Cha getCha() {
        return cha;
    }

    public void setCha(Cha cha) {
        this.cha = cha;
    }

    @FXML
    private JFXTextArea txModoPreparo;

    @FXML
    private JFXListView<Beneficio> listbeneficios;

    @FXML
    private JFXListView<Ingrediente> listIngredientes;

    @FXML
    private Pane pane;

    @FXML
    private Label lblNomeCha;

    @FXML
    private JFXTextArea lblDescCha;

    @FXML
    private Label lblBeneCha;

    @FXML
    private ImageView imgCha;

    @FXML
    private Label lblIngredientes;

    public void initList(Cha c) throws SQLException {

        BeneficioDAO dao = new BeneficioDAO();

        ObservableList<Beneficio> list = FXCollections.observableArrayList(dao.pesquisaBeneficioPorCha(c));

        System.out.println(list);

        listbeneficios.getItems().addAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Este chá é o: " + cha.getNome());
        Cha c = ChaDAO.Pesquisar2(cha);
        System.out.println("#ID: " + c.getId());

        Image img = ChaDAO.capturaImagemCha(c);
        //System.out.println(beneficios.size());
        lblNomeCha.setText(c.getNome() + " #" + c.getId());
        lblDescCha.setText("Descrição: " + c.getDescricao_cha());
        lblIngredientes.setText("Ingredientes:\n");
        txModoPreparo.setText(c.getModo_preparo());

        try {
            initList(c);
        } catch (SQLException ex) {
            System.out.println("Erro: #" + ex);
        }

        if (img != null) {
            imgCha.setImage(img);
        }

    }

}
