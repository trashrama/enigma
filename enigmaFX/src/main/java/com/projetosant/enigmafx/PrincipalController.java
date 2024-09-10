package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private Label btn_cad_curso;

    @FXML
    private Label btn_meus_cursos;

    @FXML
    private ImageView img_cur;

    @FXML
    private AnchorPane curso_pnl;

    @FXML
    private AnchorPane principal_pnl;

    @FXML
    private TableView<Curso> pane_cursos;

    @FXML
    private TableColumn<Curso, LocalDate> data_tbl_curso;

    @FXML
    private TableColumn<Curso, byte[]> img_tbl_curso;

    @FXML
    private TableColumn<Curso, String> titulo_tbl_curso;

    private File fileImg = null;

    @FXML
    private Label usr_lvl;

    @FXML
    private Label usr_nome;

    @FXML
    private Label xp_usr;


    @FXML
    public void onBtnCadCursoClicked() throws IOException {
        Application.geraTelas("CadastrarCurso.fxml", "Cadastrar Curso");

    }

    @FXML
    public void onBtnMeusCursosClicked() throws IOException {
        curso_pnl.setVisible(true);
        principal_pnl.setVisible(false);
    }

    private void popularCursos() throws IOException {
        ObservableList<Curso> cursos = FXCollections.observableArrayList(DaoFactory.createCursoDao().listar());

        System.out.println(cursos);
        pane_cursos.setItems(cursos);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            usr_nome.setText(Application.usuarioLogado.getNome());
            usr_lvl.setText(String.valueOf(Application.usuarioLogado.getLvl_usuario()));
            xp_usr.setText(String.valueOf(Application.usuarioLogado.getXp()));

            popularCursos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
