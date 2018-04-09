import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import java.util.InputMismatchException;


public class Consumer {

    @Produce  //for use if you want to send messages to camel endpoints
    private ProducerTemplate template;

    @Consume(uri="timer:order") //needed to show from what route this is intended to consume
    public String cook(String s) {//throws Exception{ //camel will attempt to automaticly match the message to corresponding invalues
                                                        //You can give more specific information about matching in the route
        if(s==null){
            s = "";
        }
        if(Math.random() > 0.9){
            System.out.println("Exception for " + s);
            throw new InputMismatchException("Burnt Food " + s);
        }
        String s2 = s.concat(" Boiled");
        System.out.println(s2);
        //return template.requestBody("NeedToEat:queue:test.queue2", s2, String.class);
        return s2;   //return value will become new body of the message
    }

    int number = 0;
    @Consume(uri="timer:order")
    public String produce(String s){
        return "" + number++;
    }


}
