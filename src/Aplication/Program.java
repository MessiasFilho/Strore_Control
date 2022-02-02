package Aplication;


import java.util.Date;
import java.util.List;

import Entityes.Department;
import Entityes.Seller;
import Model.dao.FactoryDao;
import Model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

			SellerDao sellerdao = FactoryDao.FactorySeller(); 
			
			
			System.out.println("==== Teste1 seller FindById === ");
			Seller seller = sellerdao.FindById(3); 
			System.out.println(seller);
		
			System.out.println("\n==== Teste2 FindByDepartment ===");
			Department dep = new Department(1,null );
			List<Seller> sel = sellerdao.FindByDepartment(dep); 
			for (Seller obj : sel) {
				System.out.println(obj);
			}
	 
			/*		
			 
			System.out.println("\n=== Teste3 InnerSeller === ");
			
			Seller sel1 = new Seller (null ,"marcos", "marcos@gmail.com",new Date(),2548.0,dep); 
			sellerdao.Insert(sel1);
			System.out.println("new user "+sel1.getId());
		
			 */	
			
			
			System.out.println("\n ===Teste4 UpDate ==== ");
			Seller selr = new Seller ( 3 , "Messias" , "Messias@gmail.com", new Date() , 3500.0,dep );  
			sellerdao.update(selr);
			
			
			System.out.println("\n===Teste5 FindALL ==== ");
			
			List <Seller> list = sellerdao.FindALL(); 
			for (Seller  obj : list ) {
				System.out.println(obj);
				
			}
			
			System.out.println("\n ===Teste6 DeleteId ==== ");
			
			//sellerdao.DeleteById(7);
			
			
			
	}

}
