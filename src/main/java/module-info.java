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

    opens com.dev.projectjavafxjdbc to javafx.fxml;
    exports com.dev.projectjavafxjdbc;
    opens com.dev.projectjavafxjdbc.Controller to javafx.fxml;
    exports com.dev.projectjavafxjdbc.Controller;
}