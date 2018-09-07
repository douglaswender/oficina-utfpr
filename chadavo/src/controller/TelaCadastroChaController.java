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
    private JFXTextField txPesquisa;

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txDescricao;

    @FXML
    private JFXTextField txDetalhes;

    @FXML
    private JFXTextField txEspecificacao;

    @FXML
    private JFXTextField txIndicacao;

    @FXML
    private JFXTextField txContraIndicacao;

    @FXML
    private JFXTextField txDicas;

    @FXML
    private JFXTextField txPrevencao;
    
    @FXML
    private ImageView imgCha;

    @FXML
    void btGravarAction(ActionEvent event) throws SQLException {
        String nome, brevedescricao, detalhes, especificacao_tecnica, indicacao, contra_indicacao, dicas, prevencao, imgcha;

        Connection con = new Conexao().getConnection();
        System.out.println(txNome.getText());

        nome                  = txNome.getText();
        brevedescricao        = txDescricao.getText();
        detalhes              = txDetalhes.getText();
        especificacao_tecnica = txEspecificacao.getText();
        indicacao             = txIndicacao.getText();
        contra_indicacao      = txContraIndicacao.getText();
        dicas                 = txDicas.getText();
        prevencao             = txPrevencao.getText();
        Image image = new Image("/img/sem_foto.png");
        imgCha.setImage(image);
        imgcha                = imgCha.toString();

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
