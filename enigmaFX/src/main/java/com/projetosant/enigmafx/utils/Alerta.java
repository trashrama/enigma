package com.projetosant.enigmafx.utils;

import javafx.scene.control.Alert;

public class Alerta {


    public static Alert exibirAlerta(Alert.AlertType at, String titulo, String conteudo) {
        // Criando um alerta do tipo INFORMATION
        Alert alert = new Alert(at);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Cabeçalho opcional
        alert.setContentText(conteudo);
        // Exibindo o alerta
        alert.showAndWait();
        return alert;
    }

    public static Alert erroFaltando() {
        return Alerta.exibirAlerta(Alert.AlertType.ERROR, "ERRO", "Um ou mais campos não estão preenchidos.");
    }

}
