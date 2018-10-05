package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Usuario;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.input.MouseEvent;
import dao.ChaDAO;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import com.jfoenix.controls.JFXCheckBox;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Cha;

public class TelaCadastroChaController {

    @FXML
    private JFXTextField txPesquisa;

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txDescricao;

    @FXML
    private JFXTextField txBeneficio;

    @FXML
    private JFXTextField txIngredientes;

    @FXML
    private JFXTextField txContraIndicacao;
    
    @FXML
    private JFXCheckBox ckBeneficio;

    @FXML
    private JFXTextField txModoPreparo;

    @FXML
    private ImageView imgCha;

    @FXML
    private Label lbUser;

    @FXML
    private ImageView btAdicionar;

    @FXML
    private ImageView btRemover;

    @FXML
    private JFXButton btImagem;

    private Boolean lAlteracao = false;
    private Integer id = 0;

    @FXML
    void btGravarAction(ActionEvent event) throws SQLException, IOException {
        String nome, brevedescricao, beneficio, ingredientes, contra_indicacao, modo_preparo;
        Image imgcha;

        nome = txNome.getText();
        brevedescricao = txDescricao.getText();
        beneficio = txBeneficio.getText();
        ingredientes = txIngredientes.getText();
        contra_indicacao = txContraIndicacao.getText();
        modo_preparo = txModoPreparo.getText();

        imgcha = imgCha.getImage();
        BufferedImage imageBuffered = SwingFXUtils.fromFXImage(imgcha, null);
        ChaDAO.Gravar(nome, brevedescricao, beneficio, ingredientes, contra_indicacao, modo_preparo, imageBuffered, lAlteracao, id);

    }

    @FXML
    void btAdicionarAction(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Arquivos de imagem", "*.jpg"));
        File f = fc.showOpenDialog(null);

        if (f != null) {
            String arquivo = f.getAbsolutePath();
            File file = new File(arquivo);
            Image image = new Image(file.toURI().toString());
            imgCha.setImage(image);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Atenção!");
            alert.setHeaderText("Nenhum arquivo foi selecionado");
            javafx.scene.image.ImageView imageview = new javafx.scene.image.ImageView();
            alert.show();
        }
    }

    @FXML
    void btRemoverAction(MouseEvent event) {
        Image img = new Image("/img/sem_foto.png");
        imgCha.setImage(img);
    }

    @FXML
    void Pesquisar(ActionEvent event) throws SQLException, IOException {
//        Cha c = new Cha();
//        c.setId(Integer.parseInt(txPesquisa.getText()));
//        Cha cha = ChaDAO.Pesquisar2(c);
//        Image img = ChaDAO.capturaImagemCha(c);
//        imgCha.setImage(img);
//        txNome.setText(c.getNome());
//        txDescricao.setText(c.getDescricao_cha());
//        txBeneficio.setText(c.getBeneficios());
//        txIngredientes.setText(c.getIngredientes());
//        txContraIndicacao.setText(c.getContra_indicacao());
//        txModoPreparo.setText(c.getModo_preparo());
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        txNome.setText("");
        txDescricao.setText("");
        txBeneficio.setText("");
        txIngredientes.setText("");
        txContraIndicacao.setText("");
        txModoPreparo.setText("");
        txPesquisa.setText("");
        Image img = new Image("/img/sem_foto.png");
        imgCha.setImage(img);
        Main.changeScene("principaladmin");
    }

    @FXML
    void btTeste(ActionEvent event) throws SQLException {
        //List<JFXCheckBox> TodosBeneficios = ChaDAO.TodosBeneficios();
//        JFrame frame = new JFrame("Options");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        frame.setSize(300, 300);
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        List<JCheckBox> checkboxes = new ArrayList<>();
//
//        Connection con = new Conexao().getConnection();
//        PreparedStatement stm = con.prepareStatement("SELECT * FROM beneficios");
//        ResultSet rs = stm.executeQuery();
//
//        while (rs.next()) {
//            JCheckBox box = new JCheckBox(rs.getString(2));
//            checkboxes.add(box);
//        }
//
//        frame.add(panel);
    }

    @FXML
    void onEnterPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Cha c = new Cha();
            c.setId(Integer.parseInt(txPesquisa.getText()));
            Cha cha = ChaDAO.Pesquisar2(c);
            Image img = ChaDAO.capturaImagemCha(c);
            imgCha.setImage(img);
            txNome.setText(cha.getNome());
            txDescricao.setText(cha.getDescricao_cha());
            txBeneficio.setText(cha.getBeneficios());
//            txIngredientes.setText(cha.getIngredientes());
//            txContraIndicacao.setText(cha.getContra_indicacao());
//            txModoPreparo.setText(cha.getModo_preparo());
            lAlteracao = true;
            id = cha.getId();
        }
    }

    @FXML
    void initialize() {
        
    }
}
