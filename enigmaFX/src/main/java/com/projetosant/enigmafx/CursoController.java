package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CursoController implements Initializable {

    private Curso curso;

    public void setCurso(Curso curso) {
        this.curso = curso;
        atualizarInterface();

    }

    public void atualizarInterface(){
        if (curso != null) {
            curso_img.setImage(Imagem.bytesToImg(curso.getImg()));
            curso_titulo.setText(curso.getTitulo());
            curso_data.setText(curso.getTitulo());
            curso_desc.setText(curso.getTitulo());
            curso_categorias.setText(curso.listaCategorias());

            curso_titulo.setText(curso.getTitulo());        }
    }


    @FXML
    private Button btn_add_post;

    @FXML
    private Button btn_del_curso;

    @FXML
    private Button btn_mod_curso;

    @FXML
    private Label curso_categorias;

    @FXML
    private Label curso_data;

    @FXML
    private Label curso_desc;

    @FXML
    private ImageView curso_img;

    @FXML
    private Label curso_titulo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
