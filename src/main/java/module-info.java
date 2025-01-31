module com.dev.projectjavafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires java.sql;
    requires java.desktop;

    opens com.dev.projectjavafxjdbc to javafx.fxml;
    exports com.dev.projectjavafxjdbc;
    exports com.dev.projectjavafxjdbc.model.services;
    opens com.dev.projectjavafxjdbc.model.services to javafx.fxml;
    exports com.dev.projectjavafxjdbc.model.entities;
    opens com.dev.projectjavafxjdbc.model.entities to javafx.fxml;
    exports com.dev.projectjavafxjdbc.db;
    opens com.dev.projectjavafxjdbc.db to javafx.fxml;
    exports com.dev.projectjavafxjdbc.controllers.util;
    opens com.dev.projectjavafxjdbc.controllers.util to javafx.fxml;

}