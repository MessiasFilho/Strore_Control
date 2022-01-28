package Model.dao;

import java.util.List;

import Entityes.Department;

public interface DepartmentDao {

	 void Insert (Department Obj ) ;
	 void update (Department Obj  ); 
	 void DeleteById (Integer Id); 
	 Department FindById (Integer Id ); 
	 List <Department> FindALL (); 
	 
}
