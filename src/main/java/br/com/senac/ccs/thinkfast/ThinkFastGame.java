package br.com.senac.ccs.thinkfast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.AsyncContext;

public class ThinkFastGame {

    private final ConcurrentHashMap<String, Participant> participants;
    private final Lock lock;
    private final List<Question> questions;
    private Question currentQuestion;

    public ThinkFastGame() {
        this.participants = new ConcurrentHashMap<String, Participant>();
        this.questions = new ArrayList<Question>();
        this.lock = new ReentrantLock();
    }

    public void play( String id, String name, AsyncContext asyncContext ) throws IOException {
    }

    public void bind( String id, AsyncContext asyncContext ) {
    }

    public void answer( String id, String answer ) throws IOException {
    }

    public void init() {
        this.questions.add( new Question( "Qual a capital dos EUA?", Arrays.asList( new String[]{ "Washington DC", "California", "Nevada" } ), "Washington DC" ) );
        this.questions.add( new Question( "Qual a capital da Russia?", Arrays.asList( new String[]{ "Berlin", "Paris", "Moscou" } ), "Moscou" ) );
        this.currentQuestion = questions.get( 0 );
    }
}