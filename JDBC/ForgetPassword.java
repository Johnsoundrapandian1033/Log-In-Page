package JDBC;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
@WebServlet("/forgetpass")
public class ForgetPassword extends HttpServlet {
	public void doGet(HttpServletRequest req ,HttpServletResponse res) throws IOException , ServletException{
		res.setContentType("text/html");
		Connection con = null;
		String userid = req.getParameter("userid");
		String pass = req.getParameter("confirm");
		PreparedStatement pst = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage","root","John1045@");
			
			pst = con.prepareStatement("update Users SET passwords = (?) where userId = (?)");
			pst.setString(1,pass);
			pst.setString(2,userid);
			
			int row  = pst.executeUpdate();
			if(row == 1) {
					res.getWriter().print("successfully change password!...");
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery("select * from Users;");
				while(rs.next()) {
					res.getWriter().print(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				}
			}
			else res.getWriter().print("your id not found!...");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if(con != null) {
					con.close();
				pst.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		res.getWriter().print("");
	}
}
