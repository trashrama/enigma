package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.projetosant.enigmafx.utils.Alerta;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import static com.projetosant.enigmafx.utils.Imagem.imgToBytes;


public class CadastroUsuarioController implements Initializable {

    @FXML
    private TextField nome_usr, login_usr;
    @FXML
    private Button btn_sou_instrutor;
    @FXML
    private PasswordField pwd_usr;
    @FXML
    private DatePicker dt_usr;
    @FXML
    private ImageView img_usr;
    private File fileImg = null;
    private boolean ehInstrutor = false;

    @FXML
    protected void onClickedGoBack(MouseEvent mouseEvent) throws IOException {
        Application.geraTelas("Inicial.fxml", "Tela Inicial");

    }

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
    protected void onClickedCadastrar() throws IOException{
        if (valida()) {
           if(!DaoFactory.createUsuarioDao().inserir(new Usuario(nome_usr.getText(), dt_usr.getValue(), login_usr.getText(), pwd_usr.getText(), Imagem.imgToBytes(fileImg), ehInstrutor))){
               nome_usr.setText(null);
               pwd_usr.setText(null);
               dt_usr.setValue(null);
               login_usr.setText(null);
               img_usr.setImage(null);
               btn_sou_instrutor.setDisable(false);
           }else{
               Application.geraTelas("Inicial.fxml", "ENIGMA - Tela Inicial");
           }

        }
    }

    @FXML
    protected void onClickedSouInstrutor(){
        if (Alerta.exibirAlerta(Alert.AlertType.CONFIRMATION, "Você é instrutor mesmo?", "Confirme se é instrutor", "Sou, eu juro!", "Não sou não, eu sou mentiroso")){
            ehInstrutor = true;
            btn_sou_instrutor.setDisable(true);
        }
    }


    private boolean valida(){
        if (nome_usr.getText().isEmpty() || login_usr.getText().isEmpty() || pwd_usr.getText().isEmpty() ||
                dt_usr.getValue() == null){
            Alerta.erroFaltando();
            return false;
        }
        return true;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
