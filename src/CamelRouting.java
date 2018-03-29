import org.apache.camel.LoggingLevel;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.DeadLetterChannel;

import java.util.InputMismatchException;

public class CamelRouting extends RouteBuilder {

    @Override
    public void configure() {

        getContext().setHandleFault(true);
        //onException(InputMismatchException.class, RuntimeCamelException.class).log(LoggingLevel.DEBUG,"redelivery").maximumRedeliveries(-5).log(LoggingLevel.ERROR,"exception").bean(Consumer.class, "throwAway");
        errorHandler(defaultErrorHandler().maximumRedeliveries(-1).redeliveryDelay(1000));
        //errorHandler(de);



        //from("seda:NeedToCook:queue:test.queue?concurrentConsumers=4").delay(500).bean(Consumer.class, "cook");
        from("NeedToCook:queue:test.queue").delay(200).bean(Consumer.class, "cook");

        //from("seda:NeedToEat:queue:test.queue2?concurrentConsumers=2").log("To consumer").delay(100).threads(3).delay(1000).bean(Consumer.class, "consumer");
        //from("NeedToEat:queue:test.queue2").log("To consumer").delay(100).threads(2).delay(1000).bean(Consumer.class, "consumer");
    }
}
