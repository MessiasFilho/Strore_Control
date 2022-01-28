package Entityes;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable   {

	private static final long serialVersionUID = 1L;

	private Integer ID ; 
	private String Name ; 
	
	public Department () {}

	public Department(Integer iD, String name) {
		super();
		ID = iD;
		Name = name;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(ID, other.ID);
	}

	@Override
	public String toString() {
		return "Department [ID=" + ID + ", Name=" + Name + "]";
	}

	
	
	
	
}
