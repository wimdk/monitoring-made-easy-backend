package be.cegeka.rsvz.metrics.metricsdropwizardspike;

import com.codahale.metrics.Meter;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/dummyservice")
public class DummyService {
    
    private final Meter requests = MyMetricsServletContextListener.METRIC_REGISTRY
            .meter("requests");
    
    @GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        System.out.println("in echo + meter requests : " + requests.getCount());
        LoggerFactory.getLogger(this.getClass()).debug("message :  {} | requests : {}", message, requests.getCount());
        requests.mark();
        return message;   
    }

    
}
