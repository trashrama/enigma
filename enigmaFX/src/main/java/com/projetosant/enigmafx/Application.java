package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Application extends javafx.application.Application {
    private static Scene scenePrincipal;
    private static Stage stagePrincipal;
    public static Usuario usuarioLogado;



    @Override
    public void start(Stage stage) throws IOException {
        stagePrincipal = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Inicial.fxml"));
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
        System.out.println("trocou");
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());
        stagePrincipal.setTitle("ENIGMA - "+titulo);
        stagePrincipal.setScene(scene);
        stagePrincipal.setResizable(false);

        stagePrincipal.show();
    }

    public static void invocaTela(String url, String titulo) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("ENIGMA - "+titulo);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void invocaCurso(String url, String titulo, Curso c) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(url));
        Scene scene = new Scene(fxmlLoader.load());

        CursoController controller = fxmlLoader.getController();
        controller.setCurso(c);

        stage.setTitle("ENIGMA - "+titulo);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}