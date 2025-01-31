package com.dev.projectjavafxjdbc.controllers.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {


    public static void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type); // Tem  colocar o alert como atributo
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
        alert.setResizable(true);


    }

    public static Optional<ButtonType> showConfirmation(String title, String content) { // esse optional é para testar se o cara clicou em sim ou não
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }

}