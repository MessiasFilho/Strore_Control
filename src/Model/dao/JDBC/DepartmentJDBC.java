package Model.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Conecxao;
import BD.DBexp;
import Entityes.Department;
import Model.dao.DepartmentDao;

public class DepartmentJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentJDBC(Connection com) {
		this.conn = com;
	}

	@Override
	public void Insert(Department Obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department " + "(Name) " + "VALUES " + "(?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, Obj.getName());

			int linhas = ps.executeUpdate();
			if (linhas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {

					int id = rs.getInt(1);
					Obj.setID(id);
				}

			} else {
				throw new DBexp("Não ha linhas fetadas ");
			}

		} catch (SQLException e) {
			throw new DBexp(e.getMessage());
		} finally {
			Conecxao.CloseSatement(ps);
		}

	}

	@Override
	public void update(Department Obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE department " 
									 + "SET Name = ? " 
									 + "WHERE Id = ?");

			ps.setString(1, Obj.getName());
			ps.setInt(2, Obj.getID());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DBexp(e.getMessage());
		} finally {
			Conecxao.CloseSatement(ps);
		}

	}

	@Override
	public void DeleteById(Integer Id) {
		PreparedStatement ps = null ; 
		try {
			
			ps = conn.prepareStatement("DELETE FROM department "
					+ "WHERE Id = ?"); 
			
			ps.setInt(1, Id);
			
			ps.executeUpdate(); 
			
		
	}catch(SQLException e)
	{
	
		throw new DBexp(e.getMessage());
	}
	finally{
		Conecxao.CloseSatement(ps);
	}
	}
	@Override
	public Department FindById(Integer Id) {
		PreparedStatement ps = null ; 
		ResultSet rs = null ; 
		
		try {
			ps= conn.prepareStatement("SELECT * FROM department WHERE Id = ? "); 
			
			ps.setInt(1, Id);
			
			rs = ps.executeQuery(); 
			
			if (rs.next() ) {
				Department dep =new Department(); 
				dep.setID(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				
				return dep ; 
			}
			return null ; 
			
		}catch(SQLException e)
		{
			
			throw new DBexp(e.getMessage());
		}
		finally{
			Conecxao.CloseSatement(ps);
			Conecxao.CloseResultSet(rs);
		}
	}

	@Override
	public List<Department> FindALL() {
		PreparedStatement ps = null ; 
		ResultSet rs = null ; 
		try {
			ps = conn.prepareStatement("SELECT * FROM department ORDER BY Name"); 
			
			rs = ps.executeQuery() ; 
			
			List <Department> list = new ArrayList <>(); 
			
			while (rs.next()) {
				Department dep = new Department(); 
				dep.setID(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep); 
				
			}
			return list ; 
			
		}catch(SQLException e)
		{
			
			throw new DBexp(e.getMessage());
		}
		finally{
			Conecxao.CloseSatement(ps);
			Conecxao.CloseResultSet(rs);
		}
		
		
		
	}

}
