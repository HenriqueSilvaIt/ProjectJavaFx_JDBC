package com.dev.projectjavafxjdbc.model.services;

import com.dev.projectjavafxjdbc.model.dao.DaoFactory;
import com.dev.projectjavafxjdbc.model.dao.SellerDao;
import com.dev.projectjavafxjdbc.model.entities.Seller;

import java.util.List;

public class SellerService { // tem que criar uma dependencia no

    // Injeção de dependencia com banco de dados DaoFactory
    private SellerDao dao = DaoFactory.createSellerDao();

    // SellerListController

    public List<Seller> findAll() {

        // bucas os departamento no banco de dados
        return dao.findAll();
    // Mocar é fingir retornar os dados, n retornar de verdade
    }

    // salva novo departamento ou atualiza;
    public void saveOrUpdate(Seller obj) {
        // Se o id for nulo, significa que estamos
        // inserindo um novo departamento
        if (obj.getId() == null) {
            dao.insert(obj);
        }
        else { // caso contrário ele atualiza
            dao.update(obj);
        }
    }

    public void remove(Seller obj ) {
        dao.deleteById(obj.getId());

    }
}
