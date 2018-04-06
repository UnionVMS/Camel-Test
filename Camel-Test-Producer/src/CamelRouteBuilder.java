import org.apache.camel.builder.RouteBuilder;

public class CamelRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:order?period=10s&delay=0").log("Heartbeat").bean(Consumer.class, "cook");
    }
}
