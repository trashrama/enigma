package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.db.model.entities.Post;
import com.projetosant.enigmafx.db.model.entities.Usuario;
import com.projetosant.enigmafx.utils.Alerta;
import com.projetosant.enigmafx.utils.Imagem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.projetosant.enigmafx.Application.usuarioLogado;

public class CursoController implements Initializable {

    private Curso curso;

    @FXML
    private Button btn_salvar_curso;

    @FXML
    private Button editarImg;

    @FXML
    private TextField tituloedt_field;

    @FXML
    private ImageView imgedt_field;

    @FXML
    private TextArea descricaoedt_field;

    @FXML
    private Menu categoriasedt_field;

    @FXML
    private DatePicker dataedt_field;

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

    @FXML
    private TableColumn<Post, Boolean> tbl_aula;

    @FXML
    private TableColumn<Post, LocalDate> tbl_data;

    @FXML
    private TableColumn<Post, Integer> tbl_id;

    @FXML
    private TableColumn<Post, String> tbl_titulo;

    @FXML
    private TableView<Post> tbl_posts;

    @FXML
    private AnchorPane pnl_curso;

    @FXML
    private AnchorPane pnl_editar;

    private File fileImg = null;




    public void setCurso(Curso curso) throws IOException {
        this.curso = curso;
        atualizarInterface();
        popularPosts();

    }
    public void atualizarInterface(){
        if (curso != null) {
            if (curso.getImg() == null){
                curso_img.setImage(new Image(getClass().getResource("assets/photo.png").toExternalForm()));
            }else{
                curso_img.setImage(Imagem.bytesToImg(curso.getImg()));
            }

            curso_titulo.setText(curso.getTitulo());
            curso_data.setText(curso.getData_curso().toString());
            curso_desc.setText(curso.getDescricao());
            curso_categorias.setText(curso.listaCategorias());

            if(usuarioLogado.isEh_instrutor() && curso.getId_instrutor() == usuarioLogado.getId()){
                btn_add_post.setVisible(true);
                btn_mod_curso.setVisible(true);
                btn_del_curso.setVisible(true);
                btn_cancelar_matricula.setVisible(false);
                btn_matricular.setVisible(false);
            } else {
                btn_add_post.setVisible(false);
                btn_mod_curso.setVisible(false);
                btn_del_curso.setVisible(false);
                btn_cancelar_matricula.setVisible(true);
                btn_matricular.setVisible(true);

                if (DaoFactory.createUsuarioDao().getInscricoes(usuarioLogado.getId()).contains(curso)){
                    btn_cancelar_matricula.setDisable(false);
                    btn_matricular.setDisable(true);
                } else {
                    btn_cancelar_matricula.setDisable(true);
                    btn_matricular.setDisable(false);
                }
            }
        }
    }

    @FXML
    private void cancelarMatricula(ActionEvent event) throws IOException {
        DaoFactory.createCursoDao().desmatriculaUsuario(curso, usuarioLogado);
        btn_matricular.setDisable(false);
        btn_cancelar_matricula.setDisable(true);

        Application.geraTelas("Principal.fxml", "Principal");



    }

    @FXML
    void popularPosts() throws IOException {
        tbl_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tbl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_aula.setCellValueFactory(new PropertyValueFactory<>("eh_aula"));
        tbl_data.setCellValueFactory(new PropertyValueFactory<>("data_post"));


        ObservableList<Post> posts = FXCollections.observableArrayList(DaoFactory.createPostDao().listarPosts(curso.getId()));
        tbl_posts.setItems(posts);
        tbl_posts.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Post p = tbl_posts.getSelectionModel().getSelectedItem();
                if (p != null) {
                    try {
                        Parent root = null;

                        if(usuarioLogado.isEh_instrutor() && usuarioLogado.getId() == curso.getId_instrutor()){
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Post.fxml"));
                            root = loader.load();
                            PostController postController = loader.getController();

                            postController.setCursoController(this);
                            postController.setPost(p, curso.getId());
                        }else{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("CardPost.fxml"));
                            root = loader.load();
                            CardPostController cpc = loader.getController();
                            cpc.setPost(p);
                        }


                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }
    @FXML
    protected void onEditarImgClicked(){
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fileImg = fc.showOpenDialog(stage);

        if (fileImg != null) {
            imgedt_field.setImage(new Image(fileImg.toURI().toString()));

        }


    }

    @FXML
    private void fazerMatricula(ActionEvent event) throws IOException {
        DaoFactory.createCursoDao().matriculaUsuario(curso, usuarioLogado);
        btn_matricular.setDisable(true);
        btn_cancelar_matricula.setDisable(false);

        Application.geraTelas("Principal.fxml", "Principal");



    }


    @FXML
    private void addPost() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Post.fxml"));
        Parent root = loader.load();
        PostController postController = loader.getController();

        postController.setCursoController(this);
        postController.setPost(null, curso.getId());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }


    @FXML
    private void deletarCurso() throws IOException {
        if (Alerta.exibirAlerta(Alert.AlertType.CONFIRMATION, "", "Deseja mesmo deletar o curso?", "Sim", "Não")){
            if (DaoFactory.createCursoDao().deletarPorID(curso.getId())){
                Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Curso deletado.");
                PrincipalController.updateFeed();

                Stage stage = (Stage) pnl_curso.getScene().getWindow();
                stage.close();

                Application.geraTelas("Principal.fxml", "Principal");
            }
        }
    }


    private ArrayList<CheckMenuItem> carregarCategorias() {
        ArrayList<CheckMenuItem> menuItems = new ArrayList<>();
        System.out.println("entrei");
        String sql = "SELECT id, nome from categoria_c";
        Connection conexao = DB.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                int id = rs.getInt("id");

                CheckMenuItem i = new CheckMenuItem(nome);
                i.setUserData(id);

                if (curso.getCategorias().containsValue(nome)){
                    i.setSelected(true);
                }
                menuItems.add(i);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return menuItems;
    }

    @FXML
    private void alterarCurso() throws IOException{

        pnl_curso.setVisible(false);
        pnl_editar.setVisible(true);


        tituloedt_field.setText(curso.getTitulo());
        dataedt_field.setValue(curso.getData_curso());
        imgedt_field.setImage(Imagem.bytesToImg(curso.getImg()));
        descricaoedt_field.setText((curso.getDescricao()));
        categoriasedt_field.getItems().addAll(carregarCategorias());

    }

    private Map<Integer, String> categoriasSelecionadas(){
        Map<Integer, String> lista_categorias = new HashMap<>();

        for(MenuItem item: categoriasedt_field.getItems()){
            if (item.getStyleClass().contains("selected")){
                lista_categorias.put((Integer) item.getUserData(), item.getText());
            }
        }
        return lista_categorias;
    }

    @FXML
    private void onSalvarCursoClicked(ActionEvent event) throws IOException {
        byte[] img = Imagem.imgToBytes(fileImg);
        if (img == null){
            img = curso.getImg();
        }

        if(DaoFactory.createCursoDao().atualizar(new Curso(curso.getId(), tituloedt_field.getText(), curso.getId_instrutor(), dataedt_field.getValue(), categoriasSelecionadas(), img, descricaoedt_field.getText()))){
            Alerta.exibirAlerta(Alert.AlertType.INFORMATION, "", "Atualizado com sucesso!");
            Application.geraTelas("Principal.fxml", "Principal");

            return;
        }
        Alerta.exibirAlerta(Alert.AlertType.ERROR, "", "Não foi possível atualizar.");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
