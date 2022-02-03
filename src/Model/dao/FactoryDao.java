package Model.dao;

import BD.Conecxao;
import Model.dao.JDBC.DepartmentJDBC;
import Model.dao.JDBC.SellerJDBC;

public class FactoryDao {

	public static DepartmentDao FactoryDepartment() { 
		return new DepartmentJDBC(Conecxao.getConnection() ); 
	}
	
	public static SellerDao FactorySeller() { 
		return new SellerJDBC(Conecxao.getConnection()); 
	}
}
