package com.projetosant.enigmafx;

import com.projetosant.enigmafx.db.DB;
import com.projetosant.enigmafx.db.model.dao.DaoFactory;
import com.projetosant.enigmafx.db.model.entities.Curso;
import com.projetosant.enigmafx.utils.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CadastrarCursoController implements Initializable {

    @FXML
    private Menu menu_categorias;

    @FXML
    private TextField curso_title;

    @FXML
    private TextArea curso_desc;

    @FXML
    private ImageView img_curso;

    private File fileImg = null;

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
                CheckMenuItem i = new CheckMenuItem(rs.getString("nome"));
                i.setUserData(rs.getInt("id"));
                menuItems.add(i);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return menuItems;
    }
    private Map<Integer, String> categoriasSelecionadas(){
        Map<Integer, String> lista_categorias = new HashMap<>();

        for(MenuItem item: menu_categorias.getItems()){
            if (item.getStyleClass().contains("selected")){
                lista_categorias.put((Integer) item.getUserData(), item.getText());
            }
        }
        return lista_categorias;
    }
    private boolean validacao(){
        for(MenuItem item: menu_categorias.getItems()){
            if (item.getStyleClass().contains("selected")){
                if (!curso_title.getText().isEmpty())
                    return true;
            }
        }
        Alerta.exibirAlerta(Alert.AlertType.ERROR, "Categoria não Selecionada", "Selecione pelo menos uma categoria");
        return false;
    }

    @FXML
    public void criarCurso() throws IOException {
        if (validacao()){
            DaoFactory.createCursoDao().inserir(new Curso(curso_title.getText(),curso_desc.getText(), imgToBytes(), categoriasSelecionadas()));
        }
    }

    @FXML
    protected void onImgCursoClicked(){
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fileImg = fc.showOpenDialog(stage);

        if (fileImg != null) {
            img_curso.setImage(new Image(fileImg.toURI().toString()));

        }


    }
    @FXML
    protected void onClickedGoBack(MouseEvent mouseEvent) throws IOException {
        Application.geraTelas("Principal.fxml", "Tela Principal");

    }

    private byte[] imgToBytes() throws IOException {

        if (fileImg != null){
            return Files.readAllBytes(fileImg.toPath());
        }
        return null;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menu_categorias.getItems().addAll(carregarCategorias());
    }
}
