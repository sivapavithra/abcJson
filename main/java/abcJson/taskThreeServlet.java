package abcJson;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class taskThreeServlet
 */
@WebServlet("/taskThreeServlet")
public class taskThreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public taskThreeServlet() {
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

		String managerName=request.getParameter("name");
		String q="select * from employee where employee.\"ReportingTo\"='"+managerName+"';";
		ResultSet rs=resultset.registerEmployee(q);
		ArrayList<EmployeeDetails> ar3=new ArrayList<EmployeeDetails>();
		try {
			while(rs.next()){
				int ID=rs.getInt(1);
				String Name=rs.getString(2);
				int Age=rs.getInt(3);
				String Department=rs.getString(4);
				String Designation=rs.getString(5);
				String ReportingTo=rs.getString(6);

				EmployeeDetails cd3=new EmployeeDetails();

				cd3.setID(ID);
				cd3.setName(Name);
				cd3.setAge(Age);
				cd3.setDepartment(Department);
				cd3.setDesignation(Designation);
				cd3.setReportingTo(ReportingTo);

				ar3.add(cd3);
			}
			JSONArray jsonr3=new JSONArray();
			for(int i=0;i<ar3.size();i++) {
				Gson g3 =new Gson();
				String jsonString=g3.toJson(ar3.get(i));
				jsonr3.add(jsonString);
			}
			JSONObject jo3=new JSONObject();
			jo3.put("Employees_under_manager",jsonr3);
			String finalJson=jo3.toJSONString().replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}").replace("\\\\n", "");
			System.out.print(finalJson);
			request.setAttribute("jstring", finalJson);
			RequestDispatcher rd = 
					request.getRequestDispatcher("ShowRecords.jsp");
			rd.forward(request, response);

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
