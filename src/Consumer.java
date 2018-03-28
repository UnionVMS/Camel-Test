import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

public class Consumer {

    @Produce
    private ProducerTemplate template;

    @Consume(uri="NeedToEat")
    public String Cook(String s){
        s = "Preparied: " + s;
        System.out.println(s);
        return template.requestBody()
    }


}
