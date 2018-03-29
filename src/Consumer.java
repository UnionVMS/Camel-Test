import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import java.util.InputMismatchException;

public class Consumer {

    @Produce
    private ProducerTemplate template;

    @Consume(uri="NeedToCook:queue:test.queue")
    public String cook(String s) {//throws Exception{
        if(Math.random() > 0.9){
            System.out.println("Exception for " + s);
            throw new InputMismatchException("Burnt Food " + s);
        }
        String s2 = s.concat(" Boiled");
        System.out.println(s2);
        //return template.requestBody("NeedToEat:queue:test.queue2", s2, String.class);
        return s2;
    }

    @Consume(uri="NeedToEat:queue:test.queue2")
    public void consumer(String s){
        System.out.println("Ate: " + s);
    }

    @Consume(uri="ThrowOut:queue:test.queue3")
    public void throwAway(String s){
        System.out.println("Threw out: " + s);
    }
}
