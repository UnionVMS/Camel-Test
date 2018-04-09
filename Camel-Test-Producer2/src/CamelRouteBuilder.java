import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;


import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

@ApplicationScoped
@Startup
@ContextName("cdi-context")
public class CamelRouteBuilder extends RouteBuilder {  //need to extend RouteBuilder and override configure for camel to work

    /*Camel CDI automatically deploys and configures a CamelContext bean. That CamelContext bean is automatically instantiated,
    configured and started (resp. stopped) when the CDI container initializes (resp. shuts down). It can be injected in the application, e.g.:
    That default CamelContext bean is qualified with the built-in @Default qualifier, is scoped @ApplicationScoped and is of type DefaultCamelContext.

    Note that this bean can be customized programmatically and other Camel context beans can be deployed in the application as well.
    For more info see: http://camel.apache.org/cdi.html  */
    @Inject
    @ContextName("cdi-context")
    private CamelContext context;

    @Override
    public void configure() throws Exception {

        // Instantiate a connection factory that is appropriate to the
        //  JMS broker in use. In this case, we're using ActiveMQ.
        // The connection URI will vary between brokers; in this case
        //  we're using TCP protocol with the default ActiveMQ port
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Add the JMS connection factory as a Camel component. The name
        //  'myjms' is abitrary, but will be used in specifying the route
        //  later
        context.addComponent("myjms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        System.out.println("Life on mars");

        //specifics how to handle errors(exceptions) on a global level, can also be used on a route level by
        // from(....).errorHandler(defaultErrorHandler().maximumRedeliveries(1)).to(........)
        errorHandler(defaultErrorHandler().maximumRedeliveries(2));

        //The other way to do error handling, here on a global level, can be on route level as well
        //onException(InputMismatchException.class, RuntimeCamelException.class).bean(Consumer.class, "throwAway");


        //from whereever, in this case a timer that generates messages every 10 sec, followed by logging (you can set logging levels as well)
        //to a POJO that populates the body of the message, followed by a POJO that throws an exception 10% of the time and finaly
        //to a JMS queue, in the format "component from above : what type it is (queue or topic) : name of queue"

        //The beans require @Consume annotations on there end as long as there are more then one per class, see Consumer.java for more info
        from("timer:order?period=10s&delay=0").log("Heartbeat").bean(Consumer.class, "produce").bean(Consumer.class, "cook")
                .to("myjms:queue:needToEat");
    }
}