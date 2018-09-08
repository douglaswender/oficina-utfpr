package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
<<<<<<< HEAD
import model.Usuario;
=======
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import util.ManipularImagem;
import dao.CadastroChaDAO;
>>>>>>> 72a9af232197eae3a0a6bcb7521e458bef43f007

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
<<<<<<< HEAD
    private Label lbUser;
=======
    private ImageView btAdicionar;

    @FXML
    private ImageView btRemover;
>>>>>>> 72a9af232197eae3a0a6bcb7521e458bef43f007

    @FXML
    void btGravarAction(ActionEvent event) throws SQLException {
        String nome, brevedescricao, detalhes, especificacao_tecnica, indicacao, contra_indicacao, dicas, prevencao;
        Image imgcha;

        Connection con = new Conexao().getConnection();
        System.out.println(txNome.getText());

        nome = txNome.getText();
        brevedescricao = txDescricao.getText();
        detalhes = txDetalhes.getText();
        especificacao_tecnica = txEspecificacao.getText();
<<<<<<< HEAD
        indicacao = txIndicacao.getText();
        contra_indicacao = txContraIndicacao.getText();
        dicas = txDicas.getText();
        prevencao = txPrevencao.getText();
        Image image = new Image("/img/sem_foto.png");
        imgCha.setImage(image);
        imgcha = imgCha.toString();

        String sql = "INSERT INTO CHA(NOME                           , BREVE_DESCRICAO        , DETALHES, "
                + "ESPECIFICACAO_TECNICA          , INDICACAO              , CONTRA_INDICACAO, "
                + "DICAS                          , PREVENCAO              ) "
                + "VALUES('" + nome + "','" + brevedescricao + "','" + detalhes + "',"
                + "'" + especificacao_tecnica + "','" + indicacao + "','" + contra_indicacao + "',"
                + "'" + dicas + "','" + prevencao + "')";

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
=======
        indicacao             = txIndicacao.getText();
        contra_indicacao      = txContraIndicacao.getText();
        dicas                 = txDicas.getText();
        prevencao             = txPrevencao.getText();
        //Image image = new Image("/img/sem_foto.png");
        //imgCha.setImage(image);
        imgcha                = imgCha.getImage();
        BufferedImage image = SwingFXUtils.fromFXImage(imgcha, null);
        CadastroChaDAO.Gravar(nome, brevedescricao, detalhes, especificacao_tecnica, indicacao, contra_indicacao, dicas, prevencao, image);
        
    }

    @FXML
    void btAdicionarAction(MouseEvent event) {
        BufferedImage imagem;
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            try {
                /*imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);
                Image image = SwingFXUtils.toFXImage(imagem, null);
                imgCha.setImage(image);*/
                BufferedImage bufferedImage = ImageIO.read(arquivo);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imgCha.setImage(image);

            } catch (Exception ex) {
               // System.out.println(ex.printStackTrace().toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
        }
    }

    @FXML
    void btRemoverAction(MouseEvent event) {

>>>>>>> 72a9af232197eae3a0a6bcb7521e458bef43f007
    }

    @FXML
    void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object Data) {
                Usuario usr = (Usuario) Data;
                if (newScreen.equals("cadastro")) {
                    lbUser.setText("Olá "+ usr.getLoginUsuario());
                    System.out.println("estou na tela cadastro e os dados são: " + usr.getLoginUsuario());
                }
            }
        });
    }
}