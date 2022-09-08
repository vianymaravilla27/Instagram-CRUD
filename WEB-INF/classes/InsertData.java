import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

// Import Database Connection Class file 
//import DatabaseConnection; 

// Servlet Name 
@WebServlet("/InsertData") 
public class InsertData extends HttpServlet { 
	private static final long serialVersionUID = 1L; 

	protected void doPost(HttpServletRequest request, 
HttpServletResponse response) 
		throws ServletException, IOException 
	{
	String parametro1 = request.getParameter("username"); 
		try { 

		
			Connection con = DatabaseConnection.initializeDatabase(); 

			
			PreparedStatement st = con 
				.prepareStatement("insert into usuarios (nombre, pass, email) values(?, ?, ?)"); 

			
			st.setString(1, request.getParameter("username")); 

			
			st.setString(2, request.getParameter("userpass")); 

			st.setString(3, request.getParameter("email")); 

			
			st.executeUpdate(); 

			
			st.close(); 
			con.close(); 

			
			PrintWriter out = response.getWriter(); 
			out.println("<html><body><b>Te has registrado correctamente " + parametro1 
						+ "</b>  "+"<br> <a href=\"/CrudFotos/\">Regresar a Login</a> </body></html>"); 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
} 
