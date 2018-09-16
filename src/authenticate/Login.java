package authenticate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connectdb.MyDb;
@WebServlet("/authenticate")
public class Login extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		MyDb conn = new MyDb();
		conn.connect();
		String id = req.getParameter("Uname");
		String pass = req.getParameter("pass"); String checkPass="";
		int flag = 0; ResultSet rs = null;
		try {
			String query = "SELECT pass FROM doctorsandclinics WHERE id = '"+id+"'";
			rs = conn.stmnt.executeQuery(query);
		}
		catch(Exception e) {
			flag = -1;
		}
		try {
			rs.next();
			checkPass = rs.getString("pass");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			flag = -1 ;
		}
		if(!checkPass.equals(pass)) {
			flag = -1 ;
		}
		if(flag == 0) {
			req.setAttribute("userId", id);
			
			Date pickupDate = dateOfPickup(id);
			if(pickupDate == null) {
				RequestDispatcher rd = req.getRequestDispatcher("nopickup.jsp");
				rd.forward(req, res);
			}
			else {
				RequestDispatcher rd = req.getRequestDispatcher("yespickup.jsp");
				rd.forward(req, res);
			}
			req.setAttribute("pickup", pickupDate);
			
			String query = "SELECT nam FROM doctorsandclinics WHERE id = '"+id+"'";
			String query2 = "Select hospital FROM doctorsandclinics WHERE id = '"+id+"'";
			ResultSet rslt = null; String name = "";
			try {
				rslt = conn.stmnt.executeQuery(query);
				rslt.next();
				name = rslt.getString("nam");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("name", name);
			String hospital = "";
			try {
				rslt = conn.stmnt.executeQuery(query2);
				rslt.next();
				hospital = rslt.getString("hospital");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("hospitalName", hospital);
			//today's date
			DateFormat fr = new SimpleDateFormat("dd-mm-yyyy");
			Date d= new Date();
			try {
				Date date = fr.parse(d.toString());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			String sql = "SELECT address FROM Doctorsandclients WHERE id = '"+id+"'";
			String add = "";
			try {
				ResultSet ress = conn.stmnt.executeQuery(sql);
				ress.next();
				add= ress.getString("address");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("add", add);
		}
		else {
			req.setAttribute("err", flag);
			RequestDispatcher rd = req.getRequestDispatcher("badlogin.jsp");
			rd.forward(req, res);
		}
	}
	public Date dateOfPickup(String s) {
		MyDb conn = new MyDb();
		conn.connect();
		Date d = null;
		String query = "SELECT dates FROM PickDays WHERE id = '"+s+"'";
		ResultSet rs = null; String date = "";
		try {
			rs = conn.stmnt.executeQuery(query);
			rs.next();
			date = rs.getString("dates");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			d = null;
			date = "";
		}
		DateFormat fr = new SimpleDateFormat("dd-mm-yyyy");
		
		try {
			d= fr.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			d = null;
		}
		return d;
	}
}
