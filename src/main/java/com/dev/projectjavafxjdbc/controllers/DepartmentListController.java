package com.dev.projectjavafxjdbc.controllers;

import com.dev.projectjavafxjdbc.HelloApplication;
import com.dev.projectjavafxjdbc.model.entities.Department;
import com.dev.projectjavafxjdbc.model.services.DepartmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentListController implements Initializable {

    // Injeção de dependencia do servic
    private DepartmentService service;

    @FXML
    private TableView<Department> tableViewDepartment;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    private ObservableList<Department> obsList;

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

    // Inverter controle, ao inves de chamar o objeto
    // de outra classe diretamente nessa
    // nos Injetar a dependencia para pegar as informações da outra classe
    public void setDepartmentService(DepartmentService service) {
        this.service = service;
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

        // Método responsável por acessar os serviços
        // carregar os Departmentos e jogar os Departmentos
        // no atributo obsersableList
        public void updateTableView() {
            // Verifica o se objeto DepartmentServer foi instanciado
            // nessa classe
            if (service == null) {
                throw new IllegalStateException("Service was null");
            }
            // Lista abaixo será carregada dentro do obsList
            List<Department> list = service.findAll();
            obsList = FXCollections.observableArrayList(list);
            // ai obsList vai pegar informação da lista de servicos
            tableViewDepartment.setItems(obsList);
        }
    }







