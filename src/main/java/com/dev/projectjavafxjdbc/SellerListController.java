package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.controllers.util.Alerts;
import com.dev.projectjavafxjdbc.controllers.util.Utils;
import com.dev.projectjavafxjdbc.controllers.util.listeners.DataChangeListener;
import com.dev.projectjavafxjdbc.db.DbIntegrityException;
import com.dev.projectjavafxjdbc.model.entities.Seller;
import com.dev.projectjavafxjdbc.model.services.DepartmentService;
import com.dev.projectjavafxjdbc.model.services.SellerService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SellerListController implements Initializable, DataChangeListener {

    // Injeção de dependencia do servic
    private SellerService service;


    @FXML
    private TableView<Seller> tableViewSeller;

    @FXML
    private TableColumn<Seller, Integer> tableColumnId;

    @FXML
    private TableColumn<Seller, String> tableColumnName;

    @FXML
    private TableColumn<Seller, Seller> tableColumnEDIT;

    @FXML
    private TableColumn<Seller, Seller> tableColumnREMOVE;

    @FXML
    private TableColumn<Seller, Seller> tableColumnEmail;

    @FXML
    private TableColumn<Seller, Date> tableColumnBirthDate;

    @FXML
    private TableColumn<Seller, Double> tableColumnBaseSalary;

    private ObservableList<Seller> obsList;

    @FXML
    private Button btNew;

    //Método botão
    @FXML
    public void onBtNewAction(ActionEvent event) {


        //  Pegando referÊncia para o Stage atual
        Stage parentStage = Utils.currentStage(event);
        Seller obj = new Seller();
        // Abrido arquivo e janela de formulário
        createDialogForm(obj,"SellerForm.fxml", parentStage);
    }


    @Override
    public void initialize(URL url, ResourceBundle br) {

        initializeNodes();
    }

    // Inverter controle, ao inves de chamar o objeto
    // de outra classe diretamente nessa
    // nos Injetar a dependencia para pegar as informações da outra classe
    public void setSellerService(SellerService service) {
        this.service = service;
    }

    // Método para iniciar as colunas da tabela
    private void initializeNodes() {

            try {   //Padrão do Javafx para iniciar o comportamento das coluna
                tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
                tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
                tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                tableColumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
                // Formata data
                Utils.formatTableColumnDate(tableColumnBirthDate, "dd/MM/yyyy");
                tableColumnBaseSalary.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
                // Formata double com 2 casa decimais
                Utils.formatTableColumnDouble(tableColumnBaseSalary, 2);


                // Pega a referencia da cena principal e pegar janela da
                // tela pricnipal, o Window é uma superclasse do stage
                // por isso fizemos o downcastging do stage

                

                Stage stage = (Stage) HelloApplication.getMainScene().getWindow();
                // Comando para fazer a tabela acompanhar a altura da janela
             tableViewSeller.prefHeightProperty().bind(stage.heightProperty());

            } catch (NullPointerException e ) {
               System.out.println(e.getMessage());
            }
        }

        // Método responsável por acessar os serviços
        // carregar os Selleros e jogar os Selleros
        // no atributo obsersableList
        public void updateTableView() {
            // Verifica o se objeto SellerServer foi instanciado
            // nessa classe
            if (service == null) {
                throw new IllegalStateException("Service was null");
            }
            // Lista abaixo será carregada dentro do obsList
            List<Seller> list = service.findAll();
            obsList = FXCollections.observableArrayList(list);
            // ai obsList vai pegar informação da lista de servicos
            tableViewSeller.setItems(obsList);
            initEditButtons();
            initRemoveButtons();
        }

        //Instanciando a janela de dialogo para o formulário
       private void createDialogForm(Seller obj, String absoluteName, Stage parentStage) { // o segundo argumento é o nome da
              // view que será carregada e passamos ela no painel (painel) lá embaixo
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
              Pane pane = loader.load(); // carregando a view

              // Pegando referencia para o controlador form
              SellerFormController controller  = loader.getController();
              // injetando o departamento entity
              controller.setSeller(obj);
              controller.setServices(new SellerService(), new DepartmentService()); // injetando SellerService
              // Carregando departamento no banco de dados e colocando
              // no controller
              controller.loadAssociateObjects();
              // Se inscrevendo para receber o evento, e quando o
              // evento for disparado, vai ser executando o método
              // lá em baixo onDataChanged()
              controller.subscribeDataChangeListener(this);
              controller.updateFormData(); // carrega dados do objeto no formulário

              // Inserindo a nova janela na frente ( é um palco dentro do outro)
              Stage dialogStage = new Stage();
              dialogStage.setTitle("Enter Seller data: ");
              dialogStage.setScene(new Scene(pane));
              dialogStage.setResizable(false); // Resizable é o que permite ou não a janela ser redimensionada
              // se tiver false ele não vai deixar redimensionar a janela
              dialogStage.initOwner(parentStage); // aqui colocando o Stage pai dessa janela
              // Janela vai ficar travada enquanto você não fechar ela, n consegui
              // acessar a janela anterior, para isso usamos WINDOW_MODAL
              dialogStage.initModality(Modality.WINDOW_MODAL);
              dialogStage.showAndWait();
          }catch (IOException e) {
              Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
              System.out.println(e.getMessage());
          }
       }

        // Quando dispara o evento informando que foi alterado
    // os dados no formulário ao clicarem em salvar
    // esse método vai atualizar a janela anterior
    // com updateTableView(
    @Override
    public void onDataChange() {
        updateTableView();
    }

    //Método cria um botão de edição em cada linha da tabela, para que cada linha da tabela possa ser alterado o departamento
    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Seller, Seller>() {
            private final Button button = new Button("edit");


            @Override
            protected void updateItem(Seller obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> createDialogForm( // Cria janela do formulário ao apertar o botão
                                obj, "SellerForm.fxml",Utils.currentStage(event))); // esse obj pega o departamento da linha do departamento que vocÊ clicar
            }
        });
    }

    // Método para criar um botão de remoção em cada linha de departamento
    private void initRemoveButtons() {
        tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnREMOVE.setCellFactory(param -> new TableCell<Seller, Seller>() {
            private final Button button = new Button("remove");
            @Override
            protected void updateItem(Seller obj, boolean empty) {
                super.updateItem(obj, empty);
                if (obj == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> removeEntity(obj));
            }


        });
    }

    private void removeEntity(Seller obj) {

        // Armazena o resultado do alert na váriavel result
        Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");

      // .get para pegar informação do objeto dentro do optional
        if (result.get() == ButtonType.OK) { // verifica se clico em OK
            if (service == null) { // Verifica se o programador
                // injeto a classe service
                throw new IllegalStateException("Service was null");
            }
            try {
                service.remove(obj); // remove da tabela
                updateTableView(); // atualiza os dados na janela

            } catch (DbIntegrityException e) {
                e.printStackTrace();
                Alerts.showAlert("Error removing object", null, e.getMessage(), Alert.AlertType.ERROR);
            }
        }

    }

}







