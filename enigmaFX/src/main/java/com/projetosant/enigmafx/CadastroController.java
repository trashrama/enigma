package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.projetosant.enigmafx.utils.Alerta;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CadastroController extends Application {

    @FXML
    private ImageView goBack;
    @FXML
    private TextField nome_usr, login_usr;
    @FXML
    private PasswordField pwd_usr;
    @FXML
    private DatePicker dt_usr;


    @FXML
    public void onClickedGoBack(MouseEvent mouseEvent) throws IOException {
        Application.geraTelas("Inicial.fxml", "Tela Inicial");

    }


    private boolean valida(){
       if (nome_usr.getText().isEmpty() || login_usr.getText().isEmpty() || pwd_usr.getText().isEmpty() ||
            dt_usr.getValue() == null){
            Alerta.erroFaltando();
            return false;
        }
        return true;
    }
    @FXML
    public void onClickedCadastrar() throws IOException{
        if (valida()){
            String sql = "insert into usuario(nome, data_nasc, login, senha) values (?,?,?,?)";
            Connection conexao = DB.getConnection();
            PreparedStatement pst;

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, nome_usr.getText());
                pst.setDate(2, Date.valueOf(dt_usr.getValue()));
                pst.setString(3, login_usr.getText());
                pst.setString(4, pwd_usr.getText());


                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "ENIGMA - Cadastro", "Cadastro efetuado com sucesso!");
                    DaoFactory.createUsuarioDao().inserir(new Usuario(nome_usr.getText(), dt_usr.getValue().toString(), login_usr.getText(), pwd_usr.getText()));

                } else {
                    Alerta.exibirAlerta(Alert.AlertType.ERROR, "ENIGMA - Cadastro", "Não foi possível cadastrar!");

                }
                nome_usr.setText(null);
                pwd_usr.setText(null);
                dt_usr.setValue(null);
                login_usr.setText(null);


            } catch (java.sql.SQLIntegrityConstraintViolationException e1) {
                Alerta.exibirAlerta(Alert.AlertType.ERROR, "ERRO",  "O usuário já está cadastrado no sistema");
            } catch (Exception e) {
                Alerta.exibirAlerta(Alert.AlertType.ERROR, "ERRO",  e.toString());
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
