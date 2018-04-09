import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import java.util.InputMismatchException;

public class Consumer {

    @Produce
    private ProducerTemplate template;


    @Consume(uri="myjms:queue:needToEat")
    public void eat(String s){
        System.out.println("Ate: " + s);
    }

    //@Consume(uri="ThrowOut:queue:test.queue3")
    //@Consume(uri="activeMQ:queue:fromBtoA")
    public void throwAway(String s){
        System.out.println("Threw out: " + s);
    }
}
