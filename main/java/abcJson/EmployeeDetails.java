package abcJson;

public class EmployeeDetails {
	int ID;
	String Name;
	int Age;
	String Department,Designation,ReportingTo;
	
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {		
		ID = iD;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		
		Age = age;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getReportingTo() {
		return ReportingTo;
	}
	public void setReportingTo(String reportingTo) {
		ReportingTo = reportingTo;
	}

	
}
