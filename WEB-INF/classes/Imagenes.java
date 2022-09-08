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



@WebServlet("/Imagenes") 
public class Imagenes extends HttpServlet { 
    private static final long serialVersionUID = 1L; 

    protected void doGet(HttpServletRequest request, 
HttpServletResponse response) 
        throws ServletException, IOException 
    {

        //Objetos Iniciales
        HttpSession Usuario=request.getSession(true);
        int id=(int)Usuario.getAttribute("sesionusuario");
        String name=(String)Usuario.getAttribute("nombresesionusuario");
        PrintWriter out = response.getWriter(); 

        //Empezamos a imprimir la pagina
        out.println("<!DOCTYPE html>\n"
        + "<html>\n"
        + "<head>\n"
        + "\t<meta charset=\"UTF-8\">\n" 
        + "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
        + "\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"
        + "\t<title>Galeria</title>\n" 
        + "</head>\n"
        + "<body>\n"
        + "\t<div class=\"container h-100\">\n");
                         
        //Primer Consulta a la base de datos
        try { 

           
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement sentencia = con.createStatement();
            String query = "SELECT * FROM fotos WHERE idusuarios='"+id+"'";
            ResultSet rs=sentencia.executeQuery(query);

            if(rs.next()){
                out.println("<h1>Hola "+ name + "</h1>"); 
                out.println("<br><br>");
                out.println("<div class=\"jumbotron\" align=\"center\">");

                //FORM UPLOAD IMAGE
                out.println("<form action=\"./subirFotos\" method=\"post\" enctype=\"multipart/form-data\" >");
                out.println("<label>Sube tu archivo</label>");
                out.println("<input  type=\"file\"  id=\"archivo\"  accept=\"image/*\" name=\"archivo\">");
               
                out.println("<input type=\"text\"  name="+id+"><br><br>");
                out.println("<button type=\"submit\"  class=\"btn btn-dark form-control\" >Añade mas fotos!</button>");  



                out.println("</div>"); 
                out.println("<br><br>"); 
               
            }else{
                out.println("<h1>Hola "+ name + "</h1>"); 
                out.println("<div class=\"jumbotron\">");
                out.println("<h1>Esto se ve un poco vacio, añade más fotos!</h1>"); 
                out.println("<button class=\"btn btn-dark form-control \" >Añade mas fotos!</button>");  
                out.println("</div>"); 
                out.println("<br><br>"); 
            }
                           out.println("<br><br><br>");
                           
                           out.println("<div class=\"container\">");
                           out.println("<div class=\"row\">");
                           

                            while(rs.next()){
                            String resultado = rs.getString("nombre");
                            out.println("<div class=\"col-3\">"); 
                            out.println("<img src="+resultado+" class=\"img-fluid\" alt="+resultado+">"); 
                            out.println("</div>"); 
                                }  
                            out.println("</div");
                            out.println("</div");
                           
                          
        rs.close(); 
        con.close(); 
            
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 


        out.println("\t\t</div>\n"
        + "\t\t<br>\n"
        + "<div class=\"footer\"><p>Footer</p></div>"
        + "</body>\n"
        + "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
        + "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
        + "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"
        + "</html>");
    } 
} 

//javac -classpath ".;C:\Program Files\tomcat\lib\servlet.jar" Login.java