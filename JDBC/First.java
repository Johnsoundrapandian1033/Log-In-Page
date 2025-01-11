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
@WebServlet("/john")
public class First extends HttpServlet {
	protected void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException , ServletException{
		res.setContentType("text/html");
		//res.getWriter().print("I am john");
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    String name = req.getParameter("name");
	    String gender = req.getParameter("gender");
	    String dob = req.getParameter("date");
	    String userid = req.getParameter("userid");
	    String userpass = req.getParameter("userpassword");
	    res.getWriter().print(dob+"  "+gender+" "+name);
		try{
			    // Class.forName("com.mysql.cj.jdbc.Driver");
				 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginpage","root","John1045@");
				 
			     st = con.createStatement();
			    
			     rs = st.executeQuery("select * from Users");
			     
			     int count =0,isSafe =0 ;
			    while(rs.next()) {
				   ++count;
				   if(rs.getString(2).equals(userid)) {
					   isSafe = 1;
					   res.getWriter().print(" That User Id is already exist!.... ");
					   req.getRequestDispatcher("Register.html").include(req,res);
					   break;
				   }
			    }
			    if(isSafe != 1) {
				    PreparedStatement pst =con.prepareStatement("insert into details (dName,dGender,dob) values (?,?,?);");
				    //res.getWriter().print("I am john");
				    pst.setString(1,name);
				    //res.getWriter().print("I am john");
				    pst.setString(2,gender);
				    
				    pst.setString(3,dob);
				    
				    
				    int row = pst.executeUpdate();
				    
				    res.getWriter().print(row);
				     pst = con.prepareStatement("insert into Users(userId,passwords,dNo) values(?,?,?);");
				     pst.setString(1,userid);
				     pst.setString(2, userpass);
				     pst.setInt(3, count+1);
				     int row2 = pst.executeUpdate();
				     res.getWriter().print(row2);
				     if( row == 1 && row2 == 1) {
					    	res.getWriter().print("Successfully Register!...");
					    	req.getRequestDispatcher("sighnin.html").forward(req,res);
					    	
					    }
				     else {
				    	 res.getWriter().print("Register failed.....");
				    	 req.getRequestDispatcher("Register.html").include(req, res);
				     }
			    }
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
