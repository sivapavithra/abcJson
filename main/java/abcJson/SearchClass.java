package abcJson;

public class SearchClass {
	public String search(String m,String data,String operation){
		StringBuilder q=new StringBuilder();
		try {


			switch(operation) {
			case "equal": {

				q=new StringBuilder("~~* '"+data+"'");
				break;
			}
			case "startswith": {
				q=new StringBuilder("~~* '"+data+"%'");
				break;
			}
			case "endswith": {
				q=new StringBuilder("~~* '%"+data+"'");
				break;
			}
			case "contains": {
				q=new StringBuilder("~~* '%"+data+"%'");
				break;
			}
			case "not equal":{
				q=new StringBuilder("!~~* '"+data+"'");
				break;
			}
			case "not contains": {
				q=new StringBuilder("!~~* '%"+data+"%'");
				break;
			}
			}

			StringBuilder query=new StringBuilder("SELECT * FROM employee WHERE \""+m+"\" "+q);
			return (query.toString());
}catch(Exception e) {
	System.out.println(e);
}
		return null;
}
	public String search(String m,int data,String op) {
		try {

			StringBuilder query=new StringBuilder("SELECT * from employee WHERE \""+m+"\""+op+data);
			return (query.toString());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}
