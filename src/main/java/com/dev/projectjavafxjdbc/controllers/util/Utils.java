package com.dev.projectjavafxjdbc.controllers.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

    // Vai pegar o stage a partir do objeto de evento
    public static Stage currentStage(ActionEvent event) {
        // Acessando o stage aonde o evento que recebeu o stage está:
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}
