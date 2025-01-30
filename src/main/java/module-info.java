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

    opens com.dev.projectjavafxjdbc to javafx.fxml;
    exports com.dev.projectjavafxjdbc;
    exports com.dev.projectjavafxjdbc.controllers;
    opens com.dev.projectjavafxjdbc.controllers to javafx.fxml;

}