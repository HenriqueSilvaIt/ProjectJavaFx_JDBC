package com.dev.projectjavafxjdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Instanciando programa passando o pacote da View:
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
            // Carregando a view
            ScrollPane scrollPane = loader.load(); // Trocar de parent para ScrollPane

            //Ajustando largura e altura dos itens automaticamnte
            // principalmente do menu
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            // Criando objeto Scene que é a cena principal
            Scene mainScene = new Scene(scrollPane);
            // Stage, palco da cena recebe a cena principal como argumento
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Sample JavaFx application"); // Título do palco
            // Mostra o palco (com a cena)
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}