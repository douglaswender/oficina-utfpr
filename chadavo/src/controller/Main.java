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
    private static Scene cadastroScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Chá da Vó 1.0 ALPHA");
        primaryStage.setResizable(false);

        
        
        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("/view/telainicio.fxml"));
        mainScene = new Scene(fxmlInicio);
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/telalogin.fxml"));
        loginScene = new Scene(fxmlLogin);

        Parent fxmlCadastroCha = FXMLLoader.load(getClass().getResource("/view/telacadastrocha.fxml"));
        cadastroScene = new Scene(fxmlCadastroCha);
                  
        primaryStage.setScene(cadastroScene);
               
        primaryStage.show();
    }
    public static void changeScene(String scene){
        switch(scene){
            case "login":
                stage.setScene(loginScene);
                break;
            case "main":
                stage.setScene(mainScene);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    //--------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    public static interface OnChangeScreen{
        void  onScreenChanged(String newScreen, Object o);
    }
    
    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }
}
