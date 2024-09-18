package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {


    @FXML
    private ImageView img_imagem_curso;

    @FXML
    private Label lbl_autor_curso;

    @FXML
    private Label lbl_titulo_curso;

    public void setCurso(Curso c){
        lbl_titulo_curso.setText(c.getTitulo());

        if (c.getImg() == null){
            img_imagem_curso.setImage(new Image(getClass().getResource("assets/photo.png").toExternalForm()));
        }else{
            img_imagem_curso.setImage(Imagem.bytesToImg(c.getImg()));
        }
        lbl_autor_curso.setText(DaoFactory.createUsuarioDao().pesquisarPorID(c.getId_instrutor()).getNome());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
