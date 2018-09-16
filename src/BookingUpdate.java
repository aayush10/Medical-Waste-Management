import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/booked")
public class BookingUpdate extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		String d = req.getParameter("bookDay");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String id = (String) req.getSession().getAttribute("id");
		String add = (String) req.getSession().getAttribute("add");
		String time = req.getParameter("time");
		java.util.Date date = null;
		String name = (String) req.getSession().getAttribute("name");
		
		try {
			date = sdf1.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());  
		String query = "INSERT INTO PICKDAYS VALUES('"+id+"','"+sqlDate+"','"+add+"','"+time+"')";
		MyDb conn = new MyDb();
		conn.connect();
		try {
			conn.stmnt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("yespickup.jsp");
		req.setAttribute("pickup", sqlDate);
		rd.forward(req, res);
	}
}
