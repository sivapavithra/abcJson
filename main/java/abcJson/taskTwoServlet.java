package abcJson;

import java.io.IOException;
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

import abcJson.SearchClass;
@WebServlet("/taskTwoServlet")
public class taskTwoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recordName=request.getParameter("name");
		String operation=request.getParameter("operation");
		SearchClass obj=new SearchClass();
		String q;
		if(recordName.equals("Age")) {
			int data=Integer.parseInt(request.getParameter("data"));
			q=obj.search(recordName,data,operation);
		}
		else {
			String data=request.getParameter("data");
			q=obj.search(recordName,data,operation);
		}
		ResultSet rs=resultset.registerEmployee(q);
		ArrayList<EmployeeDetails> ar2=new ArrayList<EmployeeDetails>();
		try {
			while(rs.next()){
				int ID=rs.getInt(1);
				String Name=rs.getString(2);
				int Age=rs.getInt(3);
				String Department=rs.getString(4);
				String Designation=rs.getString(5);
				String ReportingTo=rs.getString(6);


				EmployeeDetails cd2=new EmployeeDetails();

				cd2.setID(ID);
				cd2.setName(Name);
				cd2.setAge(Age);
				cd2.setDepartment(Department);
				cd2.setDesignation(Designation);
				cd2.setReportingTo(ReportingTo);

				ar2.add(cd2);
			}
			JSONArray jsonr2=new JSONArray();
			for(int i=0;i<ar2.size();i++) {
				Gson g2 =new Gson();
				String jsonString=g2.toJson(ar2.get(i));
				jsonr2.add(jsonString);
			}
			JSONObject jo2=new JSONObject();
			jo2.put("employees",jsonr2);
			String finalJson=jo2.toJSONString().replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}").replace("\\\\n", "");
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
