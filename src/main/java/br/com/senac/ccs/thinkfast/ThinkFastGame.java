package br.com.senac.ccs.thinkfast;

import com.google.common.collect.Iterators;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
    private Iterator<Question> questionsPool;

    public ThinkFastGame() {
        this.participants = new ConcurrentHashMap<String, Participant>();
        this.questions = new ArrayList<Question>();
        this.lock = new ReentrantLock();
    }

    public void play( String id, String name, AsyncContext asyncContext ) throws IOException {
        Participant participant = new Participant( id, name, asyncContext );
        this.participants.put( id, participant );
        participant.notify(new Result( currentQuestion, "Welcome!"));
    }

    public void bind( String id, AsyncContext asyncContext ) {
        Participant participant = this.participants.get( id );
        participant.setAsyncContext( asyncContext );
    }

    public void answer( String id, String answer ) throws IOException {
        lock.lock();
        try {
            Question current = this.currentQuestion;
            if ( current.getAnswer().equals( answer ) ) {
                current = this.questionsPool.next();
                Participant winner = participants.remove( id );
                final List<Participant> all = new ArrayList<Participant>( participants.values() );
                winner.incrementScore();
                winner.notify( new Result( current, String.format("Congratzzzzzz %s!! :)", winner.getName()), all ) );
                for ( Participant participant : participants.values() ) {
                    participant.notify( new Result( current,
                                        String.format( "The participant %s was more faster "
                            + "try again this one", winner.getName() ), all ) );
                }
                this.participants.put( id, winner );
                this.currentQuestion = current;
            }
            else {
                Participant participant = participants.get( id );
                participant.notify( new Result( "Nope!! :(" ) );
            }
        }
        finally {
            lock.unlock();
        }
    }

    public void init() {
        this.questions.add( new Question( "Qual a capital dos EUA?", Arrays.asList( new String[]{ "Washington DC", "California", "Nevada" } ), "Washington DC" ) );
        this.questions.add( new Question( "Qual a capital da Russia?", Arrays.asList( new String[]{ "Berlin", "Paris", "Moscou" } ), "Moscou" ) );
        this.questionsPool = Iterators.cycle( questions );
        this.currentQuestion = questionsPool.next();
    }
}