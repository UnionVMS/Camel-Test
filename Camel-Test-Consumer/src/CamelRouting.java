import org.apache.camel.LoggingLevel;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.DeadLetterChannel;

import java.util.InputMismatchException;
import java.util.function.Consumer;

public class CamelRouting extends RouteBuilder {

    @Override
    public void configure() {

        getContext().setHandleFault(true);
        //onException(InputMismatchException.class, RuntimeCamelException.class).log(LoggingLevel.DEBUG,"redelivery").maximumRedeliveries(-5).log(LoggingLevel.ERROR,"exception").bean(Consumer.class, "throwAway");
        onException(InputMismatchException.class, RuntimeCamelException.class).bean(Consumer.class, "throwAway").handled(true);
        //errorHandler(defaultErrorHandler().maximumRedeliveries(-1).redeliveryDelay(10));
        //errorHandler(de);



        //from("seda:NeedToCook:queue:test.queue?concurrentConsumers=4").delay(500).bean(Consumer.class, "cook");
        from("seda:activeMQ:queue:fromAtoB?concurrentConsumers=1").threads(3).delay(600).to("log:throughput?groupSize=10").log("${threadName}").bean(Consumer.class, "cook");
        //from("NeedToCook:queue:test.queue").delay(200).doTry().bean(Consumer.class, "cook").doCatch(InputMismatchException.class).bean(Consumer.class, "throwAway").end();

        //from("seda:NeedToEat:queue:test.queue2?concurrentConsumers=2").log("To consumer").delay(100).threads(3).delay(1000).bean(Consumer.class, "consumer");
        //from("NeedToEat:queue:test.queue2").log("To consumer").delay(100).threads(2).delay(1000).bean(Consumer.class, "consumer");
    }
}
