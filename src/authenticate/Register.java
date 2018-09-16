package authenticate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connectdb.MyDb;

@WebServlet("/regis")
public class Register extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		MyDb conn = new MyDb();
		PrintWriter out = res.getWriter();
		conn.connect();
		
		String id = req.getParameter("email");
		String query = "SELECT id FROM Doctorsandclinics WHERE id = '"+id+"'";
		
		ResultSet rs = null;
		String check = "";
		try {
			rs = conn.stmnt.executeQuery(query);
			rs.next();
			check = rs.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(check.equals(id)) {
			out.println("User already exists");
		}
		else {
			String email = req.getParameter("email");
			String name = req.getParameter("hosName");
			String add = req.getParameter("address");
			String pass = req.getParameter("pass");
			String que = "INSERT INTO Doctorsandclinics VALUES('"+email+"','"+pass+"','"+name+"','"+add+"')";
			try {
				conn.stmnt.executeUpdate(que);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("nopickup.jsp");
		rd.forward(req, res);
	}
}
