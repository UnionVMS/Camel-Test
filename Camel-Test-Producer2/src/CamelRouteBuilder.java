import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;


import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Startup
@ContextName("cdi-context")
public class CamelRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("Life on mars");
        from("timer:order?period=10s&delay=0").log("Heartbeat").bean(Consumer.class, "cook");
    }
}