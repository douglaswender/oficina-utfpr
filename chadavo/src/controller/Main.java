package controller;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author dougl
 */
public class Main extends Application {

    private static Stage stage;

    private static Scene mainScene;
    private static Scene loginScene;
    private static Scene cadastroChaScene;
    private static Scene cadastroUsuarioScene;
    private static Scene recuperarSenhaScene;
    private static Scene principalScene;
    private static Scene cadastroBeneficio;
    private static Scene principalAdminScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        primaryStage.getIcons().add(new Image("/img/POJET.png"));
        primaryStage.setTitle("Chá da Vó 1.0 ALPHA");
        primaryStage.setResizable(false);
        //primaryStage.setWidth(1200);
        //,primaryStage.setHeight(700);

        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("/view/telainicio.fxml"));
        mainScene = new Scene(fxmlInicio, 1200, 700);

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/telalogin.fxml"));
        loginScene = new Scene(fxmlLogin, 1200, 700);

        Parent fxmlCadastroCha = FXMLLoader.load(getClass().getResource("/view/telacadastrocha.fxml"));
        cadastroChaScene = new Scene(fxmlCadastroCha, 1200, 700);

        Parent fxmlCadastroUsuario = FXMLLoader.load(getClass().getResource("/view/telacadastrousuario.fxml"));
        cadastroUsuarioScene = new Scene(fxmlCadastroUsuario);

        Parent fxmlRecuperarSenha = FXMLLoader.load(getClass().getResource("/view/telaesqueceusenha.fxml"));
        recuperarSenhaScene = new Scene(fxmlRecuperarSenha);

        Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("/view/telaprincipal.fxml"));
        principalScene = new Scene(fxmlPrincipal);

        Parent fxmlCadastroBeneficio = FXMLLoader.load(getClass().getResource("/view/telacadastrabeneficio.fxml"));
        cadastroBeneficio = new Scene(fxmlCadastroBeneficio);
        
        Parent fxmlPrincipalAdmin = FXMLLoader.load(getClass().getResource("/view/telaprincipaladmin.fxml"));
        principalAdminScene = new Scene(fxmlPrincipalAdmin);
        
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void changeScene(String scene, Object data) {
        switch (scene) {
            case "login":
                stage.setScene(loginScene);
                notifyAllListeners("login", data);
                break;
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", data);
                break;
            case "cadastrocha":
                stage.setScene(cadastroChaScene);
                notifyAllListeners("cadastrocha", data);
                break;
            case "cadastrousuario":
                stage.setScene(cadastroUsuarioScene);
                notifyAllListeners("cadastrousuario", data);
                break;
            case "recuperar":
                stage.setScene(recuperarSenhaScene);
                notifyAllListeners("recuperar", data);
                break;
            case "principal":
                stage.setScene(principalScene);
                notifyAllListeners("principal", data);
                break;
            case "principaladmin":
                stage.setScene(principalAdminScene);
                notifyAllListeners("principaladmin", data);
                break;  
            case "cadastrobeneficio":
                stage.setScene(cadastroBeneficio);
                notifyAllListeners("cadastrobeneficio", data);
                break;
        }
    }
    
    

    public static void changeScene(String scene) {
        changeScene(scene, null);
    }
    
    public static Scene retornaSceneCadBeneficio(){
        return cadastroBeneficio;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //--------PASSAGEM DE PARAMETROS-----------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {

        void onScreenChanged(String newScreen, Object Data);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object Data) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, Data);
        }
    }
}
