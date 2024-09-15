package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Post;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.projetosant.enigmafx.Application.invocaCurso;
import static com.projetosant.enigmafx.Application.usuarioLogado;

public class PrincipalController implements Initializable {

    @FXML
    private static PrincipalController instance;

    @FXML
    private GridPane menu_pnl;


    @FXML
    private Label btn_catalogo;

    @FXML
    private Label btn_cad_curso;

    @FXML
    private Label btn_meus_cursos;

    @FXML
    private GridPane catalogo_cursos_pnl;

    @FXML
    private AnchorPane curso_pnl;

    @FXML
    private AnchorPane principal_pnl;

    @FXML
    private AnchorPane painel_curso_pnl;

    @FXML
    private TableView<Curso> tbl_cursos;

    @FXML
    private TableColumn<Curso, LocalDate> data_tbl_curso;

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
    private Button btn_cancelar_matricula;

    @FXML
    private Button btn_matricular;

    @FXML
    private Button btn_add_post;

    @FXML
    private Label btn_meu_perfil;

    @FXML
    private Button btn_del_curso;

    @FXML
    private Button btn_mod_curso;

    @FXML
    private ListView<AnchorPane> feed;


    @FXML
    private void onBtnCadCursoClicked() throws IOException {
        Application.geraTelas("CadastrarCurso.fxml", "Cadastrar Curso");

    }

    @FXML
    private void onTblCursosClicked(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2){
            Curso c = DaoFactory.createCursoDao().pesquisarPorID(tbl_cursos.getSelectionModel().getSelectedItem().getId());
            System.out.println(c.getImg());
            invocaCurso("Curso.fxml", c.getTitulo(), c);
        }

    }

    @FXML
    private void onBtnMeusCursosClicked() throws IOException {
            curso_pnl.setVisible(true);
            principal_pnl.setVisible(false);
            painel_curso_pnl.setVisible(false);

    }

    @FXML
    private void popularCursos() throws IOException {
        TableColumn<Curso, Integer> qtdalunos_tbl_curso = new TableColumn<>("Qtd. Alunos");

        titulo_tbl_curso.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        data_tbl_curso.setCellValueFactory(new PropertyValueFactory<>("data_curso"));



        qtdalunos_tbl_curso.setCellValueFactory(cellData -> {
            Curso curso = cellData.getValue(); // Obter o curso atual
            int qtdAlunos = DaoFactory.createCursoDao().quantidadeAlunos(curso.getId()); // Chama a função que retorna a quantidade de alunos
            return new SimpleIntegerProperty(qtdAlunos).asObject(); // Retorna o valor como uma propriedade observável
        });

        tbl_cursos.getColumns().add(qtdalunos_tbl_curso);
        ObservableList<Curso> cursos;
        if (usuarioLogado.isEh_instrutor()){
            cursos = FXCollections.observableArrayList(DaoFactory.createCursoDao().pesquisarPorInstrutorID(Application.usuarioLogado.getId()));
        }else{
            cursos = FXCollections.observableArrayList(DaoFactory.createUsuarioDao().getInscricoes(usuarioLogado.getId()));
        }
        tbl_cursos.setItems(cursos);


    }

    @FXML
    private void catalogoCursos() throws IOException {
        ObservableList<Curso> cursos = FXCollections.observableArrayList(DaoFactory.createCursoDao().listar());
        int row = 0;
        int column = 0;

        catalogo_cursos_pnl.getRowConstraints().clear();
        catalogo_cursos_pnl.getColumnConstraints().clear();


        for (Curso c: cursos){
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("CardCurso.fxml"));
                AnchorPane pane = load.load();
                CardController cc = load.getController();
                cc.setCurso(c);
                if (column==2){
                    column=0;
                    row++;
                }

                pane.setOnMouseClicked(event -> {
                    try {
                        invocaCurso("Curso.fxml", c.getTitulo(), c);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pane.setStyle("-fx-border-color: #000; -fx-border-width: 2px; -fx-border-radius: 5px");
                pane.setOnMouseEntered(event -> pane.setCursor(Cursor.DEFAULT));
                catalogo_cursos_pnl.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    @FXML
    public void feed() throws IOException {

        feed.getItems().clear();
        ObservableList<Post> posts;

        if (!usuarioLogado.isEh_instrutor()){
            posts = FXCollections.observableArrayList(DaoFactory.createPostDao().listarPostsCursosdoUsuario(usuarioLogado));
        }else {
            posts = FXCollections.observableArrayList(DaoFactory.createPostDao().listarPostsCursosdoInstrutor(usuarioLogado));
        }

        if(posts.isEmpty()){
            AnchorPane ap = new AnchorPane();
            Label lbl_vazio = new Label("Não há posts para serem mostrados.");
            ap.getChildren().add(lbl_vazio);
            AnchorPane.setTopAnchor(lbl_vazio, 0.0);
            AnchorPane.setBottomAnchor(lbl_vazio, 0.0);
            AnchorPane.setLeftAnchor(lbl_vazio, 0.0);
            AnchorPane.setRightAnchor(lbl_vazio, 0.0);

            feed.getItems().add(ap);
        }else{
            for (Post p : posts) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("CardPost.fxml"));
                    AnchorPane pane = load.load();
                    CardPostController cpc = load.getController();
                    cpc.setPost(p);

                    feed.getItems().add(pane);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }

        }
    }

    @FXML
    private void onBtnCatalogoClicked(MouseEvent event) {
        curso_pnl.setVisible(false);
        painel_curso_pnl.setVisible(true);
        principal_pnl.setVisible(false);
    }

    @FXML
    private void onBtnMeuPerfil(MouseEvent event) throws IOException {
        Application.invocaTela("Usuario.fxml", "Meu perfil");
    }

    @FXML
    private void onClickedGoBack(MouseEvent mouseEvent) throws IOException {
        curso_pnl.setVisible(false);
        painel_curso_pnl.setVisible(false);
        principal_pnl.setVisible(true);
    }
    @FXML
    private void onBtnSairClicked() throws IOException {
        Application.getStagePrincipal().close();
        Application.geraTelas("Inicial.fxml", "Inicial");
    }


    @FXML
    private void setMenu(){

        menu_pnl.add(btn_meus_cursos,0,0);
        menu_pnl.add(btn_meu_perfil,2,0);

        btn_meu_perfil.setVisible(true);
        btn_meus_cursos.setVisible(true);


        if (usuarioLogado.isEh_instrutor()){
            btn_cad_curso.setVisible(true);
            menu_pnl.add(btn_cad_curso,1,0);

        }else{
            btn_catalogo.setVisible(true);
            menu_pnl.add(btn_catalogo,1,0);



        }


    }

    public static void updateFeed() throws IOException {
        if (instance != null) {
            instance.feed();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;

        try {
            WritableImage i = Imagem.bytesToImg(Application.usuarioLogado.getImg());
            if (i != null) {
                usr_img.setFill(new ImagePattern(i));
            } else{
                //diretorio padrao da imagem padrao
                //usr_img.setFill(new ImagePattern());
            }

            usr_nome.setText(Application.usuarioLogado.getNome());
            System.out.println(usr_nome);
            usr_lvl.setText("LVL: " + Application.usuarioLogado.getLvl_usuario());
            xp_usr.setText("XP: " + Application.usuarioLogado.getXp());
            btn_cad_curso.setVisible(Application.usuarioLogado.isEh_instrutor());
            setMenu();
            popularCursos();
            catalogoCursos();
            feed();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
