package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import com.projetosant.enigmafx.utils.Alerta;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.projetosant.enigmafx.Application.usuarioLogado;


public class UsuarioController implements Initializable {
    @FXML
    private TextField id_field;

    @FXML
    private DatePicker data_nasc_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField nome_field;

    @FXML
    private CheckBox instrutor_field;

    @FXML
    private PasswordField senha_field;

    @FXML
    private ImageView img_usr;

    private File fileImg = null;

    @FXML
    protected void onImgClicked(){
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fileImg = fc.showOpenDialog(stage);

        if (fileImg != null) {
            img_usr.setImage(new Image(fileImg.toURI().toString()));
        }

    }

    @FXML
    private void setarCampos(){
        if (usuarioLogado.getImg() == null){
            img_usr.setImage(new Image(getClass().getResource("assets/user.png").toExternalForm()));
        }else{
            img_usr.setImage(Imagem.bytesToImg(usuarioLogado.getImg()));
        }
        id_field.setText(String.valueOf(usuarioLogado.getId()));
        nome_field.setText(usuarioLogado.getNome());
        login_field.setText(usuarioLogado.getLogin());
        data_nasc_field.setValue(usuarioLogado.getData_nasc());
        instrutor_field.setSelected(usuarioLogado.isEh_instrutor());
        senha_field.setText(usuarioLogado.getSenha());

    }


    @FXML
    private void onAtualizarClicked(ActionEvent event) throws IOException {
        byte[] img = Imagem.imgToBytes(fileImg);
        if (img == null){
            img = usuarioLogado.getImg();
        }

        if(DaoFactory.createUsuarioDao().atualizar(new Usuario(nome_field.getText(), data_nasc_field.getValue(), login_field.getText(), senha_field.getText(), img, instrutor_field.isSelected(), usuarioLogado.getId()), usuarioLogado.getId())){
            usuarioLogado = new Usuario(nome_field.getText(), data_nasc_field.getValue(), login_field.getText(), senha_field.getText(), img, instrutor_field.isSelected(), usuarioLogado.getId());
            System.out.println(usuarioLogado.isEh_instrutor());
            Application.geraTelas("Principal.fxml", "Principal");
            Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Atualizado com sucesso!");

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
            return;
        }
        Alerta.exibirAlerta(Alert.AlertType.ERROR, "", "Não foi possível atualizar.");
    }

    @FXML
    void onDeletarContaClicked(ActionEvent event) throws IOException {

        if (Alerta.exibirAlerta(Alert.AlertType.CONFIRMATION, "", "Deseja mesmo deletar a conta?", "Sim", "Não")){
            if (DaoFactory.createUsuarioDao().deletarPorID(usuarioLogado.getId())){
                Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Conta deletada.");
                Application.geraTelas("Inicial.fxml", "Tela Inicial");
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setarCampos();
    }
}
