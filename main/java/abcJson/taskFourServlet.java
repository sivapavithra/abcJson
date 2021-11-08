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

/**
 * Servlet implementation class taskFourServlet
 */
@WebServlet("/taskFourServlet")
public class taskFourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public taskFourServlet() {
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
		String employeeName=request.getParameter("name");
		String q="SELECT p.\"Name\" FROM employee AS n,employee AS p WHERE n.\"lft\" BETWEEN p.\"lft\" AND p.\"rgt\" AND n.\"Name\" = '"+employeeName+"'"
				+ " ORDER BY p.\"lft\";";
		ResultSet rs=resultset.registerEmployee(q);
		ArrayList<NameDetails> ar4=new ArrayList<NameDetails>();
		try {
			while(rs.next()){	

				String Name=rs.getString("Name");

				NameDetails ed4=new NameDetails();

				ed4.setName(Name);

				ar4.add(ed4);
			}
			JSONArray jsonr4=new JSONArray();
			for(int i=0;i<ar4.size();i++) {
				Gson g4 =new Gson();
				String jsonString=g4.toJson(ar4.get(i));
				jsonr4.add(jsonString);
			}
			JSONObject jo4=new JSONObject();
			jo4.put("Reporting Tree",jsonr4);
			String finalJson=jo4.toJSONString().replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}").replace("\\\\n", "");
			System.out.print(finalJson);
			request.setAttribute("jstring", finalJson);
			RequestDispatcher rd = 
					request.getRequestDispatcher("ShowRecords.jsp");
			rd.forward(request, response);
		}catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
