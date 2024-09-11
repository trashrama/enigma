module com.projetosant.enigmafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.swing;

    opens com.projetosant.enigmafx to javafx.fxml;
    opens com.projetosant.enigmafx.db.model.entities to javafx.base;

    exports com.projetosant.enigmafx;

}