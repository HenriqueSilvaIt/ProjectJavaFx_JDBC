package com.dev.projectjavafxjdbc.model.dao;


import com.dev.projectjavafxjdbc.db.DB;
import com.dev.projectjavafxjdbc.model.dao.impl.DepartmentDaoJDBC;
import com.dev.projectjavafxjdbc.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
