package controller;

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
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Chá da Vó 1.0 ALPHA");
        primaryStage.setResizable(false);

        
        
        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("/view/telainicio.fxml"));
        mainScene = new Scene(fxmlInicio);
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/telalogin.fxml"));
        loginScene = new Scene(fxmlLogin);
                  
        primaryStage.setScene(mainScene);
               
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
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
