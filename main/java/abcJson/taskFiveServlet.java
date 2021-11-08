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

/**
 * Servlet implementation class taskFiveServlet
 */
@WebServlet("/taskFiveServlet")
public class taskFiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String summaryName=request.getParameter("name");
		String q="SELECT \""+summaryName+"\",count(\""+summaryName+"\") as ct FROM employee group by \""+summaryName+"\";";
		ResultSet rs=resultset.registerEmployee(q);
		ArrayList<SummaryDetails> ar5=new ArrayList<SummaryDetails>();
		try {
			while(rs.next()){

				String sname=rs.getString(summaryName);
				int Count=rs.getInt("ct");



				SummaryDetails cd5=new SummaryDetails();

				if(summaryName.equals("Department")) {
					cd5.setDepartment(sname);
				}
				else if(summaryName.equals("Designation")) {
					cd5.setDesignation(sname);
				}
				else if(summaryName.equals("ReportingTo")) {
					cd5.setReportingTo(sname);
				}
				cd5.setCount(Count);


				ar5.add(cd5);
			}
			JSONArray jsonr5=new JSONArray();
			for(int i=0;i<ar5.size();i++) {
				Gson g5 =new Gson();
				String jsonString=g5.toJson(ar5.get(i));
				jsonr5.add(jsonString);
			}
			JSONObject jo5=new JSONObject();
			jo5.put("Summary",jsonr5);
			String finalJson=jo5.toJSONString().replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}").replace("\\\\n", "");
			System.out.print(finalJson);
			PrintWriter out = response.getWriter();
			out.println(finalJson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
