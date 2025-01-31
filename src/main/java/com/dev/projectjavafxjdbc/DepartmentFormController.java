package com.dev.projectjavafxjdbc;

import com.dev.projectjavafxjdbc.controllers.util.Alerts;
import com.dev.projectjavafxjdbc.controllers.util.Constraints;
import com.dev.projectjavafxjdbc.controllers.util.Utils;
import com.dev.projectjavafxjdbc.controllers.util.listeners.DataChangeListener;
import com.dev.projectjavafxjdbc.db.DbException;
import com.dev.projectjavafxjdbc.model.entities.Department;
import com.dev.projectjavafxjdbc.model.exceptions.ValidationException;
import com.dev.projectjavafxjdbc.model.services.DepartmentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class DepartmentFormController implements Initializable { // tem que
    // colocar o método o  Initializable

    private Department entity;

    private DepartmentService service;

    // Permiti outros objetos incresverem nessa lista e possam receber o event
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;


    // Mensagem de erro caso tenha algo errado no preenchimento do nome
    @FXML
    private Label labelErrorName;

    // Botão Salva
    @FXML
    private Button btSave;

    // Criando instancia da classe Department
    public void setDepartment(Department entity) {
        this.entity = entity;
    }

    public void setDepartmentService(DepartmentService service) {

        this.service = service;
    }

    // Método para  criar uma lista de objetos interessados em receber o event
    public void subscribeDataChangeListener(DataChangeListener listener) {
        // inscreve (salva) o listerner na lista
        dataChangeListeners.add(listener);

        // então todos os objetos que implemente a interface DataChangeListener
        // pode se incresver para receber o evento dessa classe
    }


    // Botão cancela
    @FXML
    private Button btCancel;



    @FXML
    //Salva departamento no banco de dados
    public void onBtSaveAction(ActionEvent event) {

        if ( entity == null ) {// caso o programado esqueceu de injetar
            throw new IllegalStateException("Entity was null, please check");
        }
        if ( service == null) {
            throw new IllegalStateException("Service was null, please check");
        }
            try {
                // Instancia o departamento (entity) e salva no banco
                entity = getFormData();
                service.saveOrUpdate(entity);

                // Notifica as classes listeners
                notifyDataChangeListeners();
                // Fechanado janela ao clicar no salvar
                Utils.currentStage(event).close();
            } catch (ValidationException e ) {// Utilizado para tratar a exceção da validação
                setErrorMessages(e.getErrors());
            }
            catch (DbException e) {
            Alerts.showAlert("Error saving objetct", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChange();
        }
    }

    // Método apra pegar os dados  do objeto e salvar
    private Department getFormData() {
        Department obj = new Department();

        // Instancia exceção de validação
        ValidationException exception = new ValidationException("Validation error");

        // pega o id do objeto
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        // Utils.tryParseToInt foi o método que criamo
        // para converter para inteiro, se for um novo id
        // ele considera que é nulo
            if (txtName.getText() == null || txtName.getText().trim().equals("")) { // Trim é utilizado para eliminar qualquer espaço em branco que esteja no inicio ou no final
            exception.addError("name", "Field can't be empty");
        }
        obj.setName(txtName.getText());

        if (exception.getErrors().size() > 0 ) { // verifica se exceção tem pelo meno 1 erro
            throw exception;
        }
        return obj;
    }

    @FXML
    public void onBtCancelAction(ActionEvent event) {
        // Cancela o preenchimento do formulário

      // fecha a janela ao clicar no cancelar
       Utils.currentStage(event).close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        // Limitando o atributo id para aceitar somente números inteiros
        Constraints.setTextFieldInteger(txtId);
        // Limitando o atributo name para aceitar somente 30 caracteres
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    //Método para atualizar ou salvar informações do formulário
    // Recebemos as informações da classe entity
    public void updateFormData() {
        if ( entity == null) { // Isso é só para garantir que o programador
            // n esqueça de injetar o objeto entity
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId())); // para pegar o id
        // usamos o String.valueOf, pois a caixa de texto (Text Field)
        // trabalha com String e para pegar o id precisamos converter para
        // inteiro, por isso usamos o String.valueOf
        txtName.setText(entity.getName());
    }

    // Utilizado para prencher a exceção no label de erro do Scene
    private void setErrorMessages(Map<String, String> errors ){
        Set<String> fields = errors.keySet(); // conjunto com os nomes dos campos

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name")); // Pega a mensagem
            // correspondente ao error name e seta ela no labelErrorName
        }
    }
}
