package com.dev.projectjavafxjdbc.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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

    // Métodos de ação dos itens de menu
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("OnMenuSellerAction");
    }

    @FXML
    public void onMenuItemDepartmentAction() {
        System.out.println("OnMenuDepartmentAction");
    }

    @FXML
    public void onMenuItemAboutAction() {
        System.out.println("OnMenuDepartmentAction");
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb ) {

    }
}
