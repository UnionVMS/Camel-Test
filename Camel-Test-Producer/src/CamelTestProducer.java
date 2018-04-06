
/*import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Consume;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.log.LogComponent;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Date;
import java.util.InputMismatchException;

/**
 * An example class for demonstrating some of the basics behind Camel. This
 * example sends some text messages on to a JMS Queue, consumes them and
 * persists them to disk
 *//*
public final class CamelTestProducer {

    private CamelJmsToFileExample() {
    }

    public static void main(String args[]) throws Exception {
        // START SNIPPET: e1
        //CamelContext context = new DefaultCamelContext();

        //context.setTracing(true);


        // END SNIPPET: e1
        // Set up the ActiveMQ JMS Components
        // START SNIPPET: e2
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        // Note we can explicit name the component
        /*context.addComponent("NeedToCook", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        context.addComponent("NeedToEat", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        context.addComponent("ThrowOut", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        context.addComponent("logger", new LogComponent());*/
        // END SNIPPET: e2
        // Add some configuration by hand ...
        // START SNIPPET: e3
        /*context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("seda:test-jms:queue:test.queue?concurrentConsumers=4").delay(500).to("file://test");
            }
        });*/
        /*context.addRoutes(new RouteBuilder() {
            public void configure() {
                //errorHandler(defaultErrorHandler().maximumRedeliveries(-1));//.log("Redelivery of: ${body}"));
                onException(InputMismatchException.class).log("exception").delay(1000).bean(Consumer.class, "cook");

                from("seda:NeedToCook:queue:test.queue?concurrentConsumers=4").errorHandler(defaultErrorHandler().maximumRedeliveries(5)).delay(500).bean(Consumer.class, "cook");
            }
        });

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("seda:NeedToEat:queue:test.queue2?concurrentConsumers=2").threads(3).delay(1000).bean(Consumer.class, "consumer");
            }
        });*/

        //context.addRoutes(new CamelRouting());
        // END SNIPPET: e3
        // Camel template - a handy class for kicking off exchanges
        // START SNIPPET: e4
        //ProducerTemplate template = context.createProducerTemplate();
        // END SNIPPET: e4
        // Now everything is set up - lets start the context
        //context.start();
        // Now send some test text to a component - for this case a JMS Queue
        // The text get converted to JMS messages - and sent to the Queue
        // test.queue
        // The file component is listening for messages from the Queue
        // test.queue, consumes
        // them and stores them to disk. The content of each file will be the
        // test we sent here.
        // The listener on the file component gets notified when new files are
        // found ... that's it!
        // START SNIPPET: e5
      /*  for (int i = 0; i < 50; i++) {
            //template.sendBody("NeedToCook:queue:test.queue", "Time: " + new Date().toString() + " Test Message: " + i);
            //template.sendBody("seda:test-jms:queue:test.queue", "Thread: " + Thread.currentThread().getId() + " Test Message: " + i);
            Thread.sleep(200);
            template.sendBody("seda:NeedToCook:queue:test.queue", "" + i);
        }
        // END SNIPPET: e5

        // wait a bit and then stop
        while(true) {
            Thread.sleep(10000);
            System.out.println("Tick");
        }
        //context.stop();
    }

    /*@Consume(uri="NeedToEat")
    public static void consumer(String s){
        System.out.println("Ate: " + s);
    }*/
/*}*/
