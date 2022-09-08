import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/mostrar_texto")
@MultipartConfig
public class ServletProcesamiento extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Part parte = request.getPart("archivo");
        InputStream contenido = parte.getInputStream();

        out.println("<!DOCTYPE html>\n"
        + "<html>\n"
        + "<head>\n"
        + "\t<meta charset=\"UTF-8\">\n" 
        + "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
	    + "\t<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"
        + "\t<title>Lo que hay en el archivo</title>\n" 
        + "</head>\n"
        + "<body>\n"
        + "\t<div class=\"container h-100\">\n"
        + "\t\t<div class=\"row h-100 justify-content-center align-items-center mx-auto p-5\">");
        
        if (contenido != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(contenido);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String texto;

            while ((texto = reader.readLine()) != null) {
                out.println(texto + "<br>\n");
            }
        }else 
            out.println("<p class=\"lead\"> No se ha podido recuperar el texto o el archivo esta vacio </p>\n");

        out.println("\t\t</div>\n"
        + "\t\t<br>\n"
        + "\t\t<a href=\"javascript:history.back()\" class=\"btn btn-primary btn-lg btn-success text-white\">Volver</a>\n"
        + "\t</div>\n"
        + "</body>\n"
        + "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
        + "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"
        + "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"
        + "</html>");

        out.close();
    }
}