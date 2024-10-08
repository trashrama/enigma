package com.projetosant.enigmafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController {
    @FXML
    private Button btn_cadastro;
    @FXML
    private Button btn_login;
    @FXML
    private Button goBack;

    @FXML
    protected void onBtnCadastroClicked() throws IOException {
        Application.getStagePrincipal().close();
        Application.geraTelas("CadastroUsuario.fxml", "Cadastro");
    }


    @FXML
    protected void onBtnLoginClicked() throws IOException {
        Application.getStagePrincipal().close();
        Application.geraTelas("Login.fxml", "Login");
    }


}