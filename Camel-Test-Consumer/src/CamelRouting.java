import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jms.JmsComponent;


import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

@ApplicationScoped
@Startup
@ContextName("cdi-context")
public class CamelRouting extends RouteBuilder {

    @Inject
    @ContextName("cdi-context")
    private CamelContext context;

    @Override
    public void configure() throws Exception {

        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Add the JMS connection factory as a Camel component. The name
        //  'myjms' is abitrary, but will be used in specifying the route
        //  later
        context.addComponent("myjms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        System.out.println("Life on venus");



        from("myjms:queue:needToEat").log("Beatheart").delay(500).bean(Consumer.class, "eat");
    }
}