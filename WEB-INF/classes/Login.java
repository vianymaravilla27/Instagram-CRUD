import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.*;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
// Import Database Connection Class file 
//import DatabaseConnection; 

// Servlet Name 
@WebServlet("/Login") 
public class Login extends HttpServlet { 
    private static final long serialVersionUID = 1L; 

    protected void doPost(HttpServletRequest request, 
HttpServletResponse response) 
        throws ServletException, IOException 
    {
    String parametro1 = request.getParameter("username"); 
    String user = request.getParameter("username"); 
    String pass = request.getParameter("userpass"); 
        try { 

           
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement sentencia = con.createStatement();
          


            String query = "SELECT *FROM usuarios WHERE nombre='"+user+"' "+ "AND pass='"+pass+"'";
            ResultSet rs=sentencia.executeQuery(query);
            

            if(rs.next())
                        {
                            int sesion = rs.getInt("idusuarios");
                            String nombresesion = rs.getString("nombre");
                            HttpSession Usuario=request.getSession(true);
                            Usuario.setAttribute("sesionusuario",sesion);
                             Usuario.setAttribute("nombresesionusuario",nombresesion);
                          
                           response.sendRedirect("./Imagenes");
                         //  PrintWriter out = response.getWriter(); 
                        //  out.println("<html><body><b>Te has logueado correctamente " + parametro1 + "</b></body></html>"); 

                        }else{
                            PrintWriter out = response.getWriter(); 
                             out.println("<html><body><b>Te has logueado incorrectamente, revisa tus credenciales </b> "
                                +"<br> <a href=\"/CrudFotos/\">Regresar a Login</a> "
                                +"</body></html>"); 
                        }
        rs.close(); 
        con.close(); 
            
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 

//javac -classpath ".;C:\Program Files\tomcat\lib\servlet.jar" Login.java