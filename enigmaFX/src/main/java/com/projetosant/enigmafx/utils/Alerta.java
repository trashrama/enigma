package com.projetosant.enigmafx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerta {


    public static void exibirAlerta(Alert.AlertType at, String titulo, String conteudo) {
        // Criando um alerta do tipo INFORMATION
        Alert alert = new Alert(at);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Cabeçalho opcional
        alert.setContentText(conteudo);
        // Exibindo o alerta
        alert.showAndWait();
    }

    public static boolean exibirAlerta(Alert.AlertType at, String titulo, String conteudo, String btn_sim, String btn_nao) {
        Alert alert = new Alert(at);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Cabeçalho opcional
        alert.setContentText(conteudo);

        ButtonType simButton = new ButtonType(btn_sim, ButtonBar.ButtonData.OK_DONE);
        ButtonType naoButton = new ButtonType(btn_nao, ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(simButton, naoButton);

        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == simButton;


    }

    public static void erroFaltando() {
        Alerta.exibirAlerta(Alert.AlertType.ERROR, "ERRO", "Um ou mais campos não estão preenchidos.");
    }

}
