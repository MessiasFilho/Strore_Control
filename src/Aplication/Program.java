package Aplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Conecxao;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con =  null ; 
		Statement st = null ; 
		ResultSet rs = null ; 
		
		try {
			con = Conecxao.getConnection() ; 
			st = con.createStatement();
			rs =st.executeQuery("SELECT * FROM Seller"); 
			
			while (rs.next() ) {
				System.out.println(rs.getString("Name"));
			}	
		

		}catch (SQLException e ) {
			e.printStackTrace();
		
		}finally {
			
			Conecxao.CloseConnection() ; 
		}
		
		
	}

}
