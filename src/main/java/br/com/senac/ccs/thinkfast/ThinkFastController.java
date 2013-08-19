package br.com.senac.ccs.thinkfast;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet( urlPatterns = { "/thinkfast" },
             asyncSupported = true )
public class ThinkFastController extends HttpServlet {

    private ThinkFastGame game;

    @Override
    public void init( ServletConfig config ) throws ServletException {
        super.init( config );
        this.game = new ThinkFastGame();
        this.game.init();
    }

    @Override
    protected void doGet( HttpServletRequest req,
                          HttpServletResponse resp )
            throws ServletException, IOException {
        AsyncContext startAsync = req.startAsync();
        final String id = req.getSession().getId();
        final String action = req.getParameter( "action" );
        if ( "play".equals( action ) ) {
            game.play( id, req.getParameter( "participant" ), startAsync );
        }
        else if ( "bind".equals( action ) ) {
            game.bind( id, startAsync );
        }
        else if (  "answer".equals( action ) ) {
            game.answer( id, req.getParameter( "answer" ) );
        }
    }
}