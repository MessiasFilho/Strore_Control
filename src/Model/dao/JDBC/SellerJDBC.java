package Model.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		PreparedStatement ps = null ; 
		try {
			ps = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,Obj.getNome());
			ps.setString(2,Obj.getEmail());
			ps.setDate(3,new java.sql.Date(Obj.getBirthDate().getTime()));
			ps.setDouble(4, Obj.getBaseSalary());
			ps.setInt(5, Obj.getDepartment().getID());
			
			int linhas = ps.executeUpdate(); 
			
			if (linhas > 0 ) {
				ResultSet rs = ps.getGeneratedKeys(); 
				if (rs.next() ) {
					int id = rs.getInt(1); 
					Obj.setId(id);
				}
				Conecxao.CloseResultSet(rs);
			}else {
				throw new DBexp("Não a linhas afetadas ");
			}
			
			
		}catch (SQLException e ) {
		
			throw new DBexp(e.getMessage());
		
		}finally {
			
			
			Conecxao.CloseSatement(ps);
		}
		
	}

	@Override
	public void update(Seller Obj) {
		
		PreparedStatement ps = null ; 
		 
		try {
			ps = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?"); 
			
				
				ps.setString(1,Obj.getNome());
				ps.setString(2, Obj.getEmail());
				ps.setDate(3,new java.sql.Date(Obj.getBirthDate().getTime()) );
				ps.setDouble(4, Obj.getBaseSalary());
				ps.setInt(5, Obj.getDepartment().getID());
				ps.setInt(6, Obj.getId());
				
				ps.executeUpdate(); 
				
				
		}catch (SQLException e ) {
		
			throw new DBexp(e.getMessage());
		
		}finally {
			
			
			Conecxao.CloseSatement(ps);
		}

		
		
		
		
	}

	@Override
	public void DeleteById(Integer Id) {
		PreparedStatement st = null ; 
		try {
			st = conn.prepareStatement("DELETE FROM seller "
					+ "WHERE Id = ?"); 
			
			
			st.setInt(1, Id);
			
			st.executeUpdate(); 
			
		}catch (SQLException e ) {
		
			throw new DBexp(e.getMessage());
		
		}finally {
			
			
			Conecxao.CloseSatement(st);
		}

		
		
		
	}

	@Override
	public Seller FindById(Integer Id) {
		
		PreparedStatement ps = null ; 
		ResultSet rs = null ; 
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
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
			
			Conecxao.CloseResultSet(rs);
			Conecxao.CloseSatement(ps);
		}
		
		
		
		
		
	}

	@Override
	public List<Seller> FindALL() {
		PreparedStatement ps = null ; 
		ResultSet rs = null ; 
		
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name ");
			
			
			rs = ps.executeQuery(); 
			
			List <Seller> list = new ArrayList<>(); 
			Map<Integer , Department > map =new HashMap<>();  
			
			while  (rs.next() ) { 
				
				Department dep = map.get(rs.getInt("DepartmentId")); 
				
				if (dep == null  ) {
					 dep = InstaceDepartment(rs);
					 map.put(rs.getInt("DepartmentId"), dep);
				 }
				
				 Seller sel = InstanceSeller (rs , dep  ); 
				
				 list.add(sel); 
			
			}
			return list; 
			
		}catch (SQLException e ) {
		
			throw new DBexp(e.getMessage());
		
		}finally {
			
			Conecxao.CloseResultSet(rs);
			Conecxao.CloseSatement(ps);
		}
		
		
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

	@Override
	public List<Seller> FindByDepartment(Department obj) {
		PreparedStatement ps = null ; 
		ResultSet rs = null ; 
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name"); 
		
			ps.setInt(1, obj.getID());
			
			rs = ps.executeQuery(); 
			
			List <Seller> list = new ArrayList<>(); 
			Map <Integer,Department  > map = new HashMap<>();  
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId")); 
				
				if (dep == null ) {
					 dep  = InstaceDepartment(rs ); 
					 map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sel = InstanceSeller (rs , dep ); 
				
				list.add(sel); 
			}
			return list ; 
			
			
		}catch (SQLException e ) {
			throw new DBexp(e.getMessage() );
		}finally {
			
			Conecxao.CloseResultSet(rs);
			Conecxao.CloseSatement(ps);
		}
		
		
		
	}
	
}
