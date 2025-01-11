package JDBC;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class First
 */
@WebServlet("/newuser")
public class NewUser extends HttpServlet {
	protected void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException , ServletException{
		res.setContentType("text/html");
		Connection con = null;
	    PreparedStatement pst = null;
	    Statement st = null;
	    
	    ResultSet rs = null;
	    String userId = req.getParameter("userId");
	    String password = req.getParameter("password");
	
		try{
			    // Class.forName("com.mysql.cj.jdbc.Driver");
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage","root","John1045@");
				 
			    
			     st = con.createStatement();
			     rs = st.executeQuery("select details.dName,details.dGender,details.dob,Users.userId from Users join details on dNo=d_no; ");
			    int num = 1;
			    boolean isSafe = true;
			    while(rs.next()) {
			    	
			    	//res.getWriter().print("\n"+num++ + ") ");
			    	if(userId.equals(rs.getString(4))) {
			    		res.getWriter().print("<h1>User"+num+"  Details...</h1>");
						   res.getWriter().println("   name : "+rs.getString(1));
						   res.getWriter().println("<br>"+"   Gender : "+rs.getString(2));
						   res.getWriter().println("<br>"+"   Date Of Birth : "+rs.getString(3));
						   res.getWriter().println("<br>"+"   UserId : "+rs.getString(4));
						   isSafe = false;
			    	}
			    	num++;
			    }
			    if(isSafe)  res.getWriter().print("<h1 style = 'color:red;'>your Account not register!...</h1>");
	    }
		catch(Exception e) {
			e.printStackTrace();
			//res.getWriter().print("I am john");
		}
		
		finally {
				try{
					if(con != null) con.close();
				    if(st != null)st.close();
				    if(rs != null)rs.close();
			     }
			     catch(SQLException se){
				      se.printStackTrace();
			     }
		}
	}

}
