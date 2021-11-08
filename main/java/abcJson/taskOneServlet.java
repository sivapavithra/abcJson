package abcJson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import abcJson.EmployeeDetails;

/**
 * Servlet implementation class taskOneServlet
 */
@WebServlet("/taskOneServlet")
public class taskOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taskOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q="select * from employee;";
		ResultSet rs=resultset.registerEmployee(q);
		ArrayList<EmployeeDetails> ar1=new ArrayList<EmployeeDetails>();
		try {
			while(rs.next()){
				int ID=rs.getInt(1);
				String Name=rs.getString(2);
				int Age=rs.getInt(3);
				String Department=rs.getString(4);
				String Designation=rs.getString(5);
				String ReportingTo=rs.getString(6);
			
				
				EmployeeDetails ed1=new EmployeeDetails();
				
				ed1.setID(ID);
				ed1.setName(Name);
				ed1.setAge(Age);
				ed1.setDepartment(Department);
				ed1.setDesignation(Designation);
				ed1.setReportingTo(ReportingTo);				
				ar1.add(ed1);
}
			JSONArray jsonr1=new JSONArray();
			for(int i=0;i<ar1.size();i++) {
				Gson g1 =new Gson();
				String jsonString=g1.toJson(ar1.get(i));
				jsonr1.add(jsonString);
			}
			JSONObject jo1=new JSONObject();
			jo1.put("employees",jsonr1);
			
			String finalJson=jo1.toJSONString().replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}").replace("\\\\n", "");
			System.out.print(finalJson);
			request.setAttribute("jstring", finalJson);
	         RequestDispatcher rd = 
	             request.getRequestDispatcher("ShowRecords.jsp");
	          rd.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
