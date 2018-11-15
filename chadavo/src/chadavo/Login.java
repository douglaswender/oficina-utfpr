/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chadavo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;

/**
 *
 * @author dougl
 */
public class Login extends Application {

    private static Stage stage;
    private Usuario usuario;

    public Login(Usuario u) {
        this.usuario = u;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/telalogin.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Chá da Vó - Login");
        stage.setScene(scene);
        stage.show();
        setStage(stage);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
