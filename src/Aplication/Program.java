package Aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conecxao;
import Entityes.Seller;
import Model.dao.DepartmentDao;
import Model.dao.FactoryDao;
import Model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

			SellerDao sel = FactoryDao.FactorySeller(); 
			
			
			System.out.println("==== Teste1 seller FindById === ");
			Seller seller = sel.FindById(3); 
			System.out.println(seller);
		
		
	}

}
