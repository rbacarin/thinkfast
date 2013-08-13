package br.com.senac.ccs.thinkfast;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

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
    }
}
