package com.dev.projectjavafxjdbc.controllers;

import javafx.scene.control.Alert;

public class Alerts {


    public static void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type); // Tem  colocar o alert como atributo
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();


    }
}