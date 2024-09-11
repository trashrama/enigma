package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    private AnchorPane curso_pnl;

    @FXML
    private AnchorPane principal_pnl;

    @FXML
    private TableView<Curso> tbl_cursos;

    @FXML
    private TableColumn<Curso, LocalDate> data_tbl_curso;

    @FXML
    private TableColumn<Curso, byte[]> img_tbl_curso;

    @FXML
    private TableColumn<Curso, String> titulo_tbl_curso;

    @FXML
    private Circle usr_img;

    @FXML
    private Label usr_lvl;

    @FXML
    private Label usr_nome;

    @FXML
    private Label xp_usr;



    @FXML
    private void onBtnCadCursoClicked() throws IOException {
        Application.geraTelas("CadastrarCurso.fxml", "Cadastrar Curso");

    }

    @FXML
    private void onTblCursosClicked(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2){
            Curso c = tbl_cursos.getSelectionModel().getSelectedItem();
            Application.invocaCurso("Curso.fxml", c.getTitulo(), c);
        }

    }

    @FXML
    private void onBtnMeusCursosClicked() throws IOException {
        if (Application.usuarioLogado.isEh_instrutor()){
            curso_pnl.setVisible(true);
            principal_pnl.setVisible(false);
        }

    }

    @FXML


    private void popularCursos() throws IOException {
        img_tbl_curso.setCellValueFactory(new PropertyValueFactory<>("img"));

        img_tbl_curso.setCellFactory(column -> new TableCell<Curso, byte[]>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Image img = Imagem.bytesToImg(item);
                    imageView.setImage(img);
                    imageView.setFitHeight(150);
                    imageView.setFitWidth(100);
                    setGraphic(imageView);
                }
            }
        });

        img_tbl_curso.setPrefWidth(100);


        titulo_tbl_curso.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        data_tbl_curso.setCellValueFactory(new PropertyValueFactory<>("data_curso"));

        ObservableList<Curso> cursos = FXCollections.observableArrayList(DaoFactory.createCursoDao().listar());

        tbl_cursos.setItems(cursos);


    }

    @FXML
    private void onClickedGoBack(MouseEvent mouseEvent) throws IOException {
        curso_pnl.setVisible(false);
        principal_pnl.setVisible(true);
    }
    @FXML
    private void onBtnSairClicked() throws IOException {
        Application.getStagePrincipal().close();
        Application.geraTelas("Inicial.fxml", "Inicial");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            WritableImage i = Imagem.bytesToImg(Application.usuarioLogado.getImg());
            if (i != null) {
                usr_img.setFill(new ImagePattern(i));
            } else{
                System.out.println("oi");
                //diretorio padrao da imagem padrao
                //usr_img.setFill(new ImagePattern());
            }

            usr_nome.setText(Application.usuarioLogado.getNome());
            System.out.println(usr_nome);
            usr_lvl.setText("LVL: " + Application.usuarioLogado.getLvl_usuario());
            xp_usr.setText("XP: " + Application.usuarioLogado.getXp());
            btn_cad_curso.setVisible(Application.usuarioLogado.isEh_instrutor());

            popularCursos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
