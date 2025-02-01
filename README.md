# Employee Register
[![NPM](https://img.shields.io/badge/licence-MIT-blue)](https://github.com/HenriqueSilvaIt/ProjectJavaFx_JDBC/blob/main/LICENSE) 

# Sobre o projeto

Employee Register é uma aplicação offline de interface gráfica desenvolvida com JavaFx utilizada para gerenciamento de vendedores dentro de uma determinada empresa.
Com ela você pode fazer o controle de funcionários ativos em sua empresa classificando-os pelo seus respectivos departamentos. 

## Como funciona a aplicação:

### Tela Inicial

![Home page](https://github.com/HenriqueSilvaIt/Assets/blob/main/Initi.png)

### Lista de Departamentos

![Departments](https://github.com/HenriqueSilvaIt/Assets/blob/main/Departmen.png) 

### Cadastro de departamento

![DepartmentRegister](https://github.com/HenriqueSilvaIt/Assets/blob/main/Department%20form.png) 

#### Cenário de preenchimento incorreto.
![DepartmentFormErr](https://github.com/HenriqueSilvaIt/Assets/blob/main/Department%20form%20err.png)

### Editar departamento

![DepartmentEdit](https://github.com/HenriqueSilvaIt/Assets/blob/main/Department%20edi.png) 

### Remover departamento

![DepartmentRemove](https://github.com/HenriqueSilvaIt/Assets/blob/main/Department%20remov.png) 

### Lista de vendedores

![Sellers](https://github.com/HenriqueSilvaIt/Assets/blob/main/Selle.png) 

### Cadastro de vendedores

![SellerRegister](https://github.com/HenriqueSilvaIt/Assets/blob/main/Seller%20for.png) 

#### Cenário de preenchimento incorreto.
![SellerFormErr](https://github.com/HenriqueSilvaIt/Assets/blob/main/Seller%20form%20err.png)

### Editar vendedores

![SellerEdit](https://github.com/HenriqueSilvaIt/Assets/blob/main/Seller%20edit.png) 

### Remover vendedores

![SellerRemove](https://github.com/HenriqueSilvaIt/Assets/blob/main/RemovSle.png) 
# Tecnologias utilizadas

- JavaFx
- Banco de dados: MySql

# Como executar o projeto

```bash
# Para executar o projeto (Windows) precisa instalar as seguintes ferramentas
- Java (Pré-requisitos: Java 17 +)
- Configurar a variavel de ambiente JAVA_HOME (ex: C:\Program Files\Java\jdk-17.0.3)
- JavaFx SDK
- Configurar a variavel de ambiente PATH_TO_FX (ex: C:\java-libs\javafx-sdk\lib)
- MySQLConnector

# Após instalar:
- Copiar os arquivos db.propeties e .Jar desse reposítorio para uma pasta X de sua prefêrencia

# executar o projeto
- Abra o Command Prompt (Prompt de comando), acesse a pasta X que vocÊ criou na etapa anterior e execute o comando abaixo:
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -cp ProjectJavaFx-JDBC.jar
HelloApplication.Main

``` 
# Autor

Henrique Oliveira da Silva

https://www.linkedin.com/in/henriqueoliveirati/

