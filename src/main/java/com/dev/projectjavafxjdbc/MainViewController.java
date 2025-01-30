package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.Controller.util.Alerts;
import com.dev.projectjavafxjdbc.controllers.DepartmentListController;
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
        loadView2("DepartmentList.fxml");
    }


    @FXML
    public void onMenuItemAboutAction() {
        loadView("About.fxml");
    }

    @Override

    public void initialize(URL uri, ResourceBundle rb) {

    }

    // Método para carregar a tela about.fxml
    private synchronized void loadView(String absoluteName) {
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

        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

        private synchronized void loadView2 (String absoluteName){
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

                // O loader carrega a view(tela), e partir do loader
                // abaixo vamos usa-lo para acessar o controller
                // pegando a referencia do controler, vamos
                // injetar a depencia do service no controller
                DepartmentListController controller = loader.getController();
                // injetando depencencia do service no CONTROLLER
                controller.setDepartmentService(new DepartmentService());
                // chamando método de atualização do service na tableView (Tabela
                controller.updateTableView();

            } catch (IOException e) {
                Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
            }

        }
    }

