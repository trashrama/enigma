package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


import java.net.URL;
import java.util.ResourceBundle;

public class CursoController implements Initializable {

    private Curso curso;

    @FXML
    private Label curso_categorias;

    @FXML
    private Label curso_data;

    @FXML
    private Text curso_desc;

    @FXML
    private ImageView curso_img;

    @FXML
    private Label curso_titulo;
    @FXML
    private Button btn_cancelar_matricula;

    @FXML
    private Button btn_matricular;

    @FXML
    private Button btn_add_post;

    @FXML
    private Button btn_del_curso;

    @FXML
    private Button btn_mod_curso;

    public void setCurso(Curso curso) {
        this.curso = curso;
        atualizarInterface();

    }

    public void atualizarInterface(){
        if (curso != null) {
            curso_img.setImage(Imagem.bytesToImg(curso.getImg()));
            curso_titulo.setText(curso.getTitulo());
            curso_data.setText(curso.getData_curso().toString());
            curso_desc.setText(curso.getDescricao());
            curso_categorias.setText(curso.listaCategorias());

            if(Application.usuarioLogado.isEh_instrutor() && curso.getId_instrutor() == Application.usuarioLogado.getId()){
                btn_add_post.setVisible(true);
                btn_mod_curso.setVisible(true);
                btn_del_curso.setVisible(true);
                btn_cancelar_matricula.setVisible(false);
                btn_matricular.setVisible(false);


            }else{
                btn_add_post.setVisible(false);
                btn_mod_curso.setVisible(false);
                btn_del_curso.setVisible(false);
                btn_cancelar_matricula.setVisible(true);
                btn_matricular.setVisible(true);

                System.out.println(DaoFactory.createUsuarioDao().getInscricoes(Application.usuarioLogado.getId()));
                if (DaoFactory.createUsuarioDao().getInscricoes(Application.usuarioLogado.getId()).contains(curso.getId())){
                    btn_cancelar_matricula.setDisable(false);
                    btn_matricular.setDisable(true);
                }else{
                    btn_cancelar_matricula.setDisable(true);
                    btn_matricular.setDisable(false);
                }

            }


        }
    }

    @FXML
    void cancelarMatricula(ActionEvent event) {
        DaoFactory.createCursoDao().desmatriculaUsuario(curso, Application.usuarioLogado);
        btn_matricular.setDisable(false);
        btn_cancelar_matricula.setDisable(true);

    }

    @FXML
    void fazerMatricula(ActionEvent event) {
        DaoFactory.createCursoDao().matriculaUsuario(curso, Application.usuarioLogado);
        btn_matricular.setDisable(true);
        btn_cancelar_matricula.setDisable(false);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
