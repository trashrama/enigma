package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Post;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class CardPostController implements Initializable  {
    private Post post;

    @FXML
    private Label titulo_post;

    @FXML
    private Text conteudo_post;

    @FXML
    private Label data_post;

    @FXML
    private Label nome_curso_post;





    public void setCurso(Post post){
        this.post = post;
        titulo_post.setText(post.getTitulo());
        conteudo_post.setText(post.getConteudo());
        data_post.setText(post.getData_post().toString());
        nome_curso_post.setText(DaoFactory.createCursoDao().pesquisarPorID(post.getId_curso()).getTitulo());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
