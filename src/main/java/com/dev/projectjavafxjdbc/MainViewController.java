package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.controllers.util.Alerts;
import com.dev.projectjavafxjdbc.model.services.DepartmentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {

    //Barra de menu
    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    private static void accept(Object x) {
    }

    // Métodos de ação dos itens de menu
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("OnMenuSellerAction");
    }

    @FXML
    public void onMenuItemDepartmentAction() {
        loadView("DepartmentList.fxml", (DepartmentListController controller) -> {
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });
    }


    @FXML
    // como tem ação no load view para o método acima, esse daqui usamos
    // uma expressão lambda passando nada como argumento porque esse n faz nada só
    // imprimi na tela
    public void onMenuItemAboutAction() {
        loadView("About.fxml", x -> {});
    }

    @Override

    public void initialize(URL uri, ResourceBundle rb) {

    }

    // Método para carregar a tela about.fxml
    private synchronized  <T> void  loadView(String absoluteName, Consumer<T> initializingAction) {
        // o synchronized é para que o método n seja interrompido
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            // Pegando referencia do VBOx da cena principal
            Scene mainScene = HelloApplication.getMainScene();
            // Get root pega o primeiro elemento da View Principal
            // , ou seja o Scroll pane, depois o Conteúdo .content
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            // Trazendo para essa tela o menu bar e os labels abaixo dele
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren()); // addAll é para adiconar coleção

            // Comando para ativar a função da interface funcional Consumer
            T controller = loader.getController();
            initializingAction.accept(controller);
            // essas 2 linhas acima executa função da expressão lambda
            // do método que injeta o controller
            // iss foi usado para criar um loadview único
            // que carrega a tela e faz a injeção do controller
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }


    }
}


