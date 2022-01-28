package Aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conecxao;
import Model.dao.DepartmentDao;
import Model.dao.FactoryDao;
import Model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Connection con =  null ; 
		Statement pt = null ; 
		ResultSet rs = null ; 
		
		
	
		
				try {
			
			con = Conecxao.getConnection();
			pt = con.createStatement(); 
			
			rs = pt.executeQuery("SELECT * FROM Department"); 
			
			
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + " "+rs.getString("Name"));
			}
		


		}catch (SQLException e ) {
			e.printStackTrace();
		
		}finally {
			Conecxao.CloseSatement(pt);
			Conecxao.CloseResultSet(rs); 
			Conecxao.CloseConnection() ; 
			
			
			
		}
		
		
	}

}
