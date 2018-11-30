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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Beneficio;
import model.Cha;
import dao.BeneficioDAO;
import dao.ContraIndicacaoDAO;
import dao.IngredientesDAO;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ContraIndicacao;
import model.Ingredientes;

public class TelaCadastroChaController implements Initializable{
    
    private Cha cha;

    public Cha getCha() {
        return cha;
    }

    public void setCha(Cha cha) {
        this.cha = cha;
    }
    
    

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
    private AnchorPane anchorpane;

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

    @FXML
    private TableView<ContraIndicacao> tbvContraIndicacao;

    @FXML
    private TableColumn<ContraIndicacao, String> selectColContra;

    @FXML
    private TableColumn<ContraIndicacao, String> nomeContraIndicacao;

    private Boolean lAlteracao = false;
    
    private Integer id = 0;
    
    private Cha pesquisa;

    TelaCadastroChaController(Cha pesquisa) {
        this.pesquisa = pesquisa;
    }


    @FXML
    void btGravarAction(ActionEvent event) throws SQLException, IOException {
        String nome, brevedescricao, modo_preparo;
        Image imgcha;

        nome = txNome.getText();
        brevedescricao = txDescricao.getText();
        modo_preparo = txModoPreparo.getText();

        imgcha = imgCha.getImage();
        BufferedImage imageBuffered = SwingFXUtils.fromFXImage(imgcha, null);
        ChaDAO.Gravar(nome, brevedescricao, modo_preparo, imageBuffered, lAlteracao, id);
        //Grava Beneficios
        ObservableList<Beneficio> items = tbvBeneficio.getItems();
        BeneficioDAO.Gravar(items);
        //Grava Ingredientes
        ObservableList<Ingredientes> ingredientes = tbvIngredientes.getItems();
        IngredientesDAO.Gravar(ingredientes);
        //Grava Contra indicação
        ObservableList<ContraIndicacao> contraIndicaco = tbvContraIndicacao.getItems();
        ContraIndicacaoDAO.Gravar(contraIndicaco);
        limpaCampos();
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
//aaa
    }

    @FXML
    void btnBackAction(ActionEvent event) throws SQLException, IOException {
        limpaCampos();
        
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/telaprincipaladmin.fxml"));
        // Definindo quem é o controller desse 'fxml':
        fxmlloader.setController(new TelaPrincipalAdminController(null));

        AnchorPane a = (AnchorPane) fxmlloader.load();

        anchorpane.getChildren().setAll(a);
        
    }

    void limpaCampos() throws SQLException {
        txNome.setText("");
        txDescricao.setText("");
        txModoPreparo.setText("");
        txPesquisa.setText("");
        Image img = new Image("/img/sem_foto.png");
        imgCha.setImage(img);
        tbvBeneficio.setItems(BeneficioDAO.pesquisaTodosBeneficios2(false, 0));
        tbvIngredientes.setItems(IngredientesDAO.pesquisaTodosIngredientes(false, 0));
        tbvContraIndicacao.setItems(ContraIndicacaoDAO.pesquisaTodasContra(false, 0));
        id = 0;
        lAlteracao = false;
    }

    @FXML
    void onEnterPress(KeyEvent event) throws SQLException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            String pesquisa = txPesquisa.getText();
            if (pesquisa.matches("[0-9]+")) {
                Cha c = new Cha(Integer.parseInt(pesquisa), "", "");
                c = ChaDAO.Pesquisar2(c);

                txNome.setText(c.getNome());
                txDescricao.setText(c.getDescricao_cha());
                txModoPreparo.setText(c.getModo_preparo());
                tbvBeneficio.setItems(BeneficioDAO.pesquisaTodosBeneficios2(true, c.getId()));
                tbvIngredientes.setItems(IngredientesDAO.pesquisaTodosIngredientes(true, c.getId()));
                tbvContraIndicacao.setItems(ContraIndicacaoDAO.pesquisaTodasContra(true, c.getId()));
                lAlteracao = true;
                id = c.getId();
                Image img = ChaDAO.capturaImagemCha(c);
                imgCha.setImage(img);
            }else{
                trocaTela(txPesquisa.getText());
            }
            
/*
            Cha c = new Cha();
            c.setId(Integer.parseInt(txPesquisa.getText()));
            Cha cha = ChaDAO.Pesquisar2(c);
            Image img = ChaDAO.capturaImagemCha(c);
            imgCha.setImage(img);
            txNome.setText(cha.getNome());
            txDescricao.setText(cha.getDescricao_cha());
            txModoPreparo.setText(cha.getModo_preparo());

            tbvBeneficio.setItems(BeneficioDAO.pesquisaTodosBeneficios2(true, c.getId()));
            tbvIngredientes.setItems(IngredientesDAO.pesquisaTodosIngredientes(true, c.getId()));
            tbvContraIndicacao.setItems(ContraIndicacaoDAO.pesquisaTodasContra(true, c.getId()));
            lAlteracao = true;
            id = cha.getId();
*/
        }
    }

    public void initList(Cha pesquisa) throws SQLException{
        //Busca todos os Beneficios
        selectCol.setCellValueFactory(new PropertyValueFactory<Beneficio, String>("marcado"));
        nomeBeneficio.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbvBeneficio.setItems(BeneficioDAO.pesquisaTodosBeneficios2(false, 0));
        //Busca todos os Ingredientess
        selectColIngre.setCellValueFactory(new PropertyValueFactory<Ingredientes, String>("marcado"));
        nomeIngrediente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbvIngredientes.setItems(IngredientesDAO.pesquisaTodosIngredientes(false, 0));
        //Busca todos as contra indicações
        selectColContra.setCellValueFactory(new PropertyValueFactory<ContraIndicacao, String>("marcado"));
        nomeContraIndicacao.setCellValueFactory(new PropertyValueFactory<ContraIndicacao, String>("nome"));
        tbvContraIndicacao.setItems(ContraIndicacaoDAO.pesquisaTodasContra(false, 0));

        if (pesquisa.equals(null)){
            Cha c = new Cha(pesquisa.getId(), "", "");
            c = ChaDAO.Pesquisar2(c);

            txNome.setText(c.getNome());
            txDescricao.setText(c.getDescricao_cha());
            txModoPreparo.setText(c.getModo_preparo());
            tbvBeneficio.setItems(BeneficioDAO.pesquisaTodosBeneficios2(true, c.getId()));
            tbvIngredientes.setItems(IngredientesDAO.pesquisaTodosIngredientes(true, c.getId()));
            tbvContraIndicacao.setItems(ContraIndicacaoDAO.pesquisaTodasContra(true, c.getId()));
            lAlteracao = true;
            id = c.getId();
            Image img = ChaDAO.capturaImagemCha(c);
            imgCha.setImage(img);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            initList(pesquisa);
        } catch (SQLException ex) {
            Logger.getLogger(TelaInfoCha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void trocaTela(String pesquisa) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/resultadobusca.fxml"));

        fxmlloader.setController(new ResultadobuscaController(pesquisa));

        Parent tela = fxmlloader.load();

        stage.setScene(new Scene(tela));

        stage.show();
    }
}