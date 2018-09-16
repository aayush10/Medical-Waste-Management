package connectdb;
import java.sql.*;
public class MyDb {
	public Statement stmnt = null;
	public void connect() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/medicalWaste","root","hellomoto");
			stmnt = myConn.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		MyDb db= new MyDb();
		db.connect();
	}
}
