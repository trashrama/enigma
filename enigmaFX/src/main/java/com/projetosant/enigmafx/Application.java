package com.projetosant.enigmafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Scene scenePrincipal;
    private static Stage stagePrincipal;
    @Override
    public void start(Stage stage) throws IOException {
        stagePrincipal = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("TelaInicial.fxml"));
        scenePrincipal = new Scene(fxmlLoader.load());
        stagePrincipal.setTitle("ENIGMA - Tela Inicial");
        stagePrincipal.setScene(scenePrincipal);
        stagePrincipal.setResizable(false);
        stagePrincipal.show();
    }


    public static Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public static void geraTelas(String url, String titulo) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ENIGMA - "+titulo);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}