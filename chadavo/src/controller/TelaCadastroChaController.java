package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaCadastroChaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField TxtNome;

    @FXML
    private JFXTextField TxtBreveDescricao;

    @FXML
    private JFXTextField TxtDetalhes;

    @FXML
    private JFXTextField TxtEspecificacaoTecnica;

    @FXML
    private JFXTextField TxtIndicacoes;

    @FXML
    private JFXTextField TxtContraIndicacoes;

    @FXML
    private JFXTextField TxtDicas;

    @FXML
    private JFXTextField TxtPrevencao;

    @FXML
    private ImageView ImgCha;

    @FXML
    void btGravarAction(ActionEvent event) throws SQLException {
        String nome, brevedescricao, detalhes, especificacao_tecnica, indicacao, contra_indicacao, dicas, prevencao, imgcha;

        Connection con = new Conexao().getConnection();
        System.out.println(TxtNome.getText());

        nome                  = TxtNome.getText();
        brevedescricao        = TxtBreveDescricao.getText();
        detalhes              = TxtDetalhes.getText();
        especificacao_tecnica = TxtEspecificacaoTecnica.getText();
        indicacao             = TxtIndicacoes.getText();
        contra_indicacao      = TxtContraIndicacoes.getText();
        dicas                 = TxtDicas.getText();
        prevencao             = TxtPrevencao.getText();
        Image image = new Image("/img/sem_foto.png");
        ImgCha.setImage(image);
        imgcha                = ImgCha.toString();

        String sql = "INSERT INTO CHA(NOME                           , BREVE_DESCRICAO        , DETALHES, " +
                                     "ESPECIFICACAO_TECNICA          , INDICACAO              , CONTRA_INDICACAO, " +
                                     "DICAS                          , PREVENCAO              ) " + 
                              "VALUES('" + nome                  + "','" + brevedescricao + "','" + detalhes + "'," +
                                     "'" + especificacao_tecnica + "','" + indicacao      + "','" + contra_indicacao + "'," +
                                     "'" + dicas                 + "','" + prevencao      + "')";

        /*String sql = "INSERT INTO CHA(NOME                           , BREVE_DESCRICAO        , DETALHES, " +
                                     "ESPECIFICACAO_TECNICA          , INDICACAO              , CONTRA_INDICACAO, " +
                                     "DICAS                          , PREVENCAO              , IMGCHA) " + 
                              "VALUES('" + nome                  + "','" + brevedescricao + "','" + detalhes + "'," +
                                     "'" + especificacao_tecnica + "','" + indicacao      + "','" + contra_indicacao + "'," +
                                     "'" + dicas                 + "','" + prevencao      + "','" + imgcha + "')";*/

        Statement stm = con.createStatement();
        stm.executeUpdate(sql);
        stm.close();
        con.close();
    }

    @FXML
    void initialize() {

    }
}
