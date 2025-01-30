package com.dev.projectjavafxjdbc.controllers;

import com.dev.projectjavafxjdbc.HelloApplication;
import com.dev.projectjavafxjdbc.model.entities.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentListController implements Initializable {

    @FXML
    private TableView<Department> tableViewDepartment;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    @FXML
    private Button btNew;

    //Método botão
    public void onBtNewAction() {
        System.out.println("OnBtNewAto");
    }


    @Override
    public void initialize(URL url, ResourceBundle br) {

        initializeNodes();
    }

    // Método para iniciar as colunas da tabela
    private void initializeNodes() {

            try {   //Padrão do Javafx para iniciar o comportamento das coluna
                tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

                // Pega a referencia da cena principal e pegar janela da
                // tela pricnipal, o Window é uma superclasse do stage
                // por isso fizemos o downcastging do stage

                

                Stage stage = (Stage) HelloApplication.getMainScene().getWindow();
                // Comando para fazer a tabela acompanhar a altura da janela
             tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());

            } catch (NullPointerException e ) {
               System.out.println(e.getMessage());
            }
        }
    }







