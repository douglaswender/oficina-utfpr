package controller;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        primaryStage.setTitle("Chá da Vó 1.0 ALPHA");
        primaryStage.setResizable(false);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);

        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("/view/telainicio.fxml"));
        mainScene = new Scene(fxmlInicio, 1200, 700);

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/telalogin.fxml"));
        loginScene = new Scene(fxmlLogin, 1200, 700);

        Parent fxmlCadastroCha = FXMLLoader.load(getClass().getResource("/view/telacadastrocha.fxml"));
        cadastroChaScene = new Scene(fxmlCadastroCha, 1200, 700);

        Parent fxmlCadastroUsuario = FXMLLoader.load(getClass().getResource("/view/telacadastrousuario.fxml"));
        cadastroUsuarioScene = new Scene(fxmlCadastroUsuario);

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
        }
    }

    public static void changeScene(String scene) {
        changeScene(scene, null);
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
