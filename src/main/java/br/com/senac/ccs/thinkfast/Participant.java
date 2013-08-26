package br.com.senac.ccs.thinkfast;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.AsyncContext;

public class Participant {

    private String id;
    private String name;
    private int score;
    private AsyncContext asyncContext;

    public Participant() {
        this.score = 0;
    }

    public Participant( String id, String name ) {
        this();
        this.id = id;
        this.name = name;
    }

    public Participant( String id, String name, AsyncContext asyncContext ) {
        this( id, name );
        this.asyncContext = asyncContext;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public void incrementScore() {
        this.score++;
    }

    public void setAsyncContext( AsyncContext asyncContext ) {
        this.asyncContext = asyncContext;
    }

    public void notify( Result result ) throws IOException {
        if(asyncContext != null ) {
            ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString( result );
            asyncContext.getResponse().getWriter().write( json );
            asyncContext.complete();
            asyncContext = null;
        }
    }
}
