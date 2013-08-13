package br.com.senac.ccs.thinkfast;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet( urlPatterns = { "/thinkfast" },
             asyncSupported = true )
public class ThinkFastController extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req,
                          HttpServletResponse resp )
            throws ServletException, IOException {
        super.doGet( req, resp );
    }
}