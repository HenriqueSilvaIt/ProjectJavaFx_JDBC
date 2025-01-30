package com.dev.projectjavafxjdbc.model.services;

import com.dev.projectjavafxjdbc.model.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService { // tem que criar uma dependencia no
    // DepartmentListController

    public List<Department> findAll() {
        //MOCK
        List<Department> list = new ArrayList<>();
        list.add(new Department(1,"Books"));
        list.add(new Department(2,"Computers"));
        list.add(new Department(3,"Electronics"));
        return list;

    // Mocar é fingir retornar os dados, n retornar de verdade
    }
}
