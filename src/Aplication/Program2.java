package Aplication;

import java.util.List;

import Entityes.Department;
import Model.dao.DepartmentDao;
import Model.dao.FactoryDao;

public class Program2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DepartmentDao depdao = FactoryDao.FactoryDepartment(); 
		
		// insert departamento 
		System.out.println("\n=== Insert ====");
		Department dep = new Department( 7 , "Vidrasaria" ); 
	
		//depdao.Insert(dep);
		//System.out.println(" " +dep.getID());
	
		// atualiza departamento 
		
		System.out.println("\n===Update===");
		depdao.update(dep);
		
		System.out.println("\n=== Delete ====");
		depdao.DeleteById(5);
		//mostra todos 
		
		System.out.println(" \n=== FindById ");
		 Department dep2=depdao.FindById(4); 
		System.out.println(dep2);
		
		
		System.out.println("\n ==FindALL ====");
		List<Department> list = depdao.FindALL(); 
		for (Department obj : list ) {
			System.out.println(obj);
			
		}
		
		
	}

}
