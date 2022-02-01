package Model.dao;

import java.util.List;

import Entityes.Department;
import Entityes.Seller;

public interface SellerDao {

	void Insert (Seller Obj ) ;
	 void update (Seller Obj  ); 
	 void DeleteById (Integer Id); 
	 Seller FindById (Integer Id ); 
	 List <Seller> FindALL (); 
	 List <Seller> FindByDepartment(Department obj); 
	 
}
