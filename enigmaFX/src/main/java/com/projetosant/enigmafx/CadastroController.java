package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


public class CadastroController implements Initializable {

    @FXML
    private ImageView goBack;
    @FXML
    private TextField nome_usr, login_usr;
    @FXML
    private PasswordField pwd_usr;
    @FXML
    private DatePicker dt_usr;
    @FXML
    private ImageView img_usr;

    private File fileImg = null;

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

    private byte[] imgToBytes() throws IOException {

        if (fileImg != null){
            return Files.readAllBytes(fileImg.toPath());
        }
        return null;
    }
    @FXML
    protected void onClickedCadastrar() throws IOException{
        if (valida()) {
           if(!DaoFactory.createUsuarioDao().inserir(new Usuario(nome_usr.getText(), dt_usr.getValue(), login_usr.getText(), pwd_usr.getText(), imgToBytes()))){
               nome_usr.setText(null);
               pwd_usr.setText(null);
               dt_usr.setValue(null);
               login_usr.setText(null);
               img_usr.setImage(null);
           }else{
               Application.geraTelas("Inicial.fxml", "ENIGMA - Tela Inicial");
           }

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
