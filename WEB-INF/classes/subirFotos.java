import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;

@WebServlet("/subirFotos")


public class subirFotos extends HttpServlet {


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int parametro1 = Integer.parseInt(request.getParameter("idusuarios"));
    Part filePart = request.getPart("archivo"); 
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
    InputStream fileContent = filePart.getInputStream();
   try { 
        String query="INSERT INTO fotos(nombre,idusuarios) values (?,?)";
            Connection con = DatabaseConnection.initializeDatabase(); 
            PreparedStatement pst;
            pst=con.prepareStatement(query);
         

          //  String filePath= savePath + File.separator + fileName ;

            pst.setString(1,fileName);
            pst.setInt(2,parametro1);
            pst.executeUpdate();
             } 
        catch (Exception e) { 
            e.printStackTrace(); 
                } 
            }
        }
 