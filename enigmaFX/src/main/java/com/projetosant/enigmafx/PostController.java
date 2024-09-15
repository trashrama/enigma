package com.projetosant.enigmafx;

import com.projetosant.enigmafx.utils.Alerta;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Post;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.projetosant.enigmafx.Application.usuarioLogado;

public class PostController implements Initializable {

    @FXML
    private Button btn_criar;

    @FXML
    private Button btn_deletar;

    @FXML
    private Button btn_salvar;

    @FXML
    private GridPane grid_botoes;


    @FXML
    private TextArea conteudo_post_field;

    @FXML
    private DatePicker data_post_field;

    @FXML
    private TextField titulo_field;

    private Post p = null;

    private int idCurso;

    private CursoController cursoController;

    public void setCursoController(CursoController cursoController) {
        this.cursoController = cursoController;
    }

    void setPost(Post p, int idCurso) {
        this.p = p;
        this.idCurso = (idCurso);
        if (p == null) {
            btn_criar.setVisible(true);
            btn_criar.setDisable(false);
            grid_botoes.add(btn_criar, 1, 0);
            data_post_field.setValue(LocalDate.now());
            data_post_field.setDisable(true);

        } else {
            if (Application.usuarioLogado.isEh_instrutor() && DaoFactory.createCursoDao().pesquisarPorID(idCurso).getId_instrutor() == Application.usuarioLogado.getId()) {
                btn_salvar.setVisible(true);
                btn_deletar.setVisible(true);
                btn_salvar.setDisable(false);
                btn_deletar.setDisable(false);
                data_post_field.setDisable(false);

                data_post_field.setValue(p.getData_post());
                titulo_field.setText(p.getTitulo());
                conteudo_post_field.setText(p.getConteudo());


                grid_botoes.add(btn_salvar, 1, 0);
                grid_botoes.add(btn_deletar, 0, 0);


            } else {
                btn_salvar.setVisible(false);
                btn_deletar.setVisible(false);
                btn_criar.setVisible(false);


            }


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onCriarPost(javafx.event.ActionEvent actionEvent) throws IOException {
        if(!(conteudo_post_field.getText().isEmpty() || titulo_field.getText().isEmpty())){
            if(DaoFactory.createPostDao().inserir(new Post(titulo_field.getText(), conteudo_post_field.getText(), null, this.idCurso, false, LocalDate.now()))){
                Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Post adicionado com sucesso!");
                PrincipalController.updateFeed();

                cursoController.popularPosts();

                Stage stage = (Stage) titulo_field.getScene().getWindow();
                stage.close();
            };

        }else{
            Alerta.exibirAlerta(Alert.AlertType.ERROR, "", "Um ou mais campos precisam estar preenchidos!");
        }
    }

    @FXML
    private void onDeletarClicked() throws IOException {
        if (Alerta.exibirAlerta(Alert.AlertType.CONFIRMATION, "", "Deseja mesmo deletar o post?", "Sim", "Não")){
            if (DaoFactory.createPostDao().deletarPorID(p.getId())){
                Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Post deletado.");
                PrincipalController.updateFeed();
                cursoController.popularPosts();
            }
        }
    }

    @FXML
    private void onSalvarClicked() throws IOException {
        if(!(conteudo_post_field.getText().isEmpty() || titulo_field.getText().isEmpty())){
            if(DaoFactory.createPostDao().atualizar(new Post(p.getId(), titulo_field.getText(), conteudo_post_field.getText(), null, p.getId_curso(), false, data_post_field.getValue()))){
                Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Post atualizado com sucesso!");
                PrincipalController.updateFeed();
                cursoController.popularPosts();

                Stage stage = (Stage) titulo_field.getScene().getWindow();
                stage.close();

            };

        }else{
            Alerta.exibirAlerta(Alert.AlertType.ERROR, "", "Um ou mais campos precisam estar preenchidos!");
        }
    }
}
