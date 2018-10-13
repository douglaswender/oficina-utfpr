package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.input.MouseEvent;
import dao.ChaDAO;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Beneficio;
import model.Cha;
import dao.BeneficioDAO;
import dao.IngredientesDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Ingredientes;

public class TelaCadastroChaController {

    @FXML
    private JFXTextField txPesquisa;

    @FXML
    private JFXTextField txNome;

    @FXML
    private JFXTextField txDescricao;

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
    
    @FXML
    private TableView<Beneficio> tbvBeneficio;

    @FXML
    private TableColumn<Beneficio, String> selectCol;

    @FXML
    private TableColumn<Beneficio, String> nomeBeneficio;

    @FXML
    private TableView<Ingredientes> tbvIngredientes;
    
    @FXML
    private TableColumn<Ingredientes, String> selectColIngre;
    
    @FXML
    private TableColumn<Ingredientes, String> nomeIngrediente;

    private Boolean lAlteracao = false;
    private Integer id = 0;

    @FXML
    void btGravarAction(ActionEvent event) throws SQLException, IOException {
        String nome, brevedescricao, modo_preparo;
        Image imgcha;

        nome = txNome.getText();
        brevedescricao = txDescricao.getText();
        modo_preparo = txModoPreparo.getText();

        imgcha = imgCha.getImage();
        BufferedImage imageBuffered = SwingFXUtils.fromFXImage(imgcha, null);
        ChaDAO.Gravar(nome, brevedescricao, imageBuffered, lAlteracao, id);
        //Grava Beneficios
        ObservableList<Beneficio> items = tbvBeneficio.getItems();
        BeneficioDAO.Gravar(items);
        //Grava Ingredientes
        ObservableList<Ingredientes> ingredientes = tbvIngredientes.getItems();
        IngredientesDAO.Gravar(ingredientes);
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
        txModoPreparo.setText("");
        txPesquisa.setText("");
        Image img = new Image("/img/sem_foto.png");
        imgCha.setImage(img);
        Main.changeScene("principaladmin");
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
//            txIngredientes.setText(cha.getIngredientes());
//            txContraIndicacao.setText(cha.getContra_indicacao());
//            txModoPreparo.setText(cha.getModo_preparo());
            lAlteracao = true;
            id = cha.getId();
        }
    }

    @FXML
    void initialize() throws SQLException {
        //Busca todos os Beneficios
        selectCol.setCellValueFactory(new PropertyValueFactory<Beneficio, String>("marcado"));
        nomeBeneficio.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbvBeneficio.setItems(BeneficioDAO.pesquisaTodosBeneficios2());
        //Busca todos os Ingredientess
        selectColIngre.setCellValueFactory(new PropertyValueFactory<Ingredientes, String>("marcado"));
        nomeIngrediente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbvIngredientes.setItems(IngredientesDAO.pesquisaTodosIngredientes());
    }
}
