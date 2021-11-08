package abcJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class resultset {
	public static ResultSet registerEmployee(String q)  {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			
			Class.forName("org.postgresql.Driver");  
			String mysqlUrl = "jdbc:postgresql://localhost/project1";

			con = DriverManager.getConnection(mysqlUrl, "postgres", "Beindependent1$");
			stmt = con.prepareStatement(q);
			rs = stmt.executeQuery();
			return rs;			
	}
		catch(Exception e) {
			System.out.print(e);
		}
		
		
		return null;
		
	}

}
