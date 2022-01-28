package Model.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import BD.Conecxao;
import BD.DBexp;
import Entityes.Department;
import Entityes.Seller;
import Model.dao.SellerDao;

public class SellerJDBC implements SellerDao {

	private Connection conn ; 
	
	public SellerJDBC (Connection com) {
		this.conn = com ; 
	}
	
	@Override
	public void Insert(Seller Obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller Obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteById(Integer Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller FindById(Integer Id) {
		
		PreparedStatement ps = null ; 
		ResultSet rs = null ; 
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName"
					+ "FROM seller INNER JOIN department"
					+ "ON seller.DepartmentId = department.Id"
					+ "WHERE seller.Id = ?");  
					
			ps.setInt(1, Id);
			rs = ps.executeQuery(); 
			
			if (rs.next() ) {
				Department dep = InstaceDepartment(rs);
				Seller obj = InstanceSeller (rs , dep); 
				return obj; 
			}
			
			return null;
					
		}catch (SQLException e ) {
		
			throw new DBexp(e.getMessage());
		
		}finally {
			Conecxao.CloseConnection();
			Conecxao.CloseResultSet(rs);
			Conecxao.CloseSatement(ps);
		}
		
		
		
		
		
	}

	@Override
	public List<Seller> FindALL() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Department InstaceDepartment  (ResultSet rs  ) throws SQLException {
		Department de = new Department ();  
		de.setID(rs.getInt("Id"));
		de.setName(rs.getString("Name"));
		return de ; 
		
		
	}
	
	private Seller InstanceSeller (ResultSet rs , Department dep ) throws SQLException {
		
		Seller sel = new Seller (); 
		
		sel.setId(rs.getInt("Id"));
		sel.setNome(rs.getString("Name"));
		sel.setEmail(rs.getString("Email")); 
		sel.setBirthDate(rs.getDate("birthDate"));
		sel.setBaseSalary(rs.getDouble("BaseSalary"));
		sel.setDepartment(dep);
		
		return sel ; 
		
	}
	
}
