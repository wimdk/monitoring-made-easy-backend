package hackathon.metrics.dummyApp;

import com.codahale.metrics.Meter;
import org.slf4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Random;

import static hackathon.metrics.dummyApp.ErrorType.values;
import static org.slf4j.LoggerFactory.getLogger;

@Path("/dummyservice")
public class DummyService {

    private final Meter requests = MyMetricsServletContextListener.METRIC_REGISTRY.meter("requests");
    private final Meter faultyRequests = MyMetricsServletContextListener.METRIC_REGISTRY.meter("faultyRequests");
    private Logger logger = getLogger(this.getClass());;

    @GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message") String message) {
        if(chanceOfThrowingErrorIsTrue(0.10f)){
            ErrorType errorType = values()[new Random().nextInt(ErrorType.values().length)];
            logError(errorType);
        } else {
            logger.info("message : {} | requests : {}", message.trim(), requests.getCount());
            requests.mark();
            System.out.println("successful requests : " + requests.getCount());
        }

        return message;
    }

    private void logError(ErrorType errorType) {
        logger.error("message : {} | faultyRequests : {}", errorType.toString(), faultyRequests.getCount());
        faultyRequests.mark();
        System.out.println("faulty requests : " + faultyRequests.getCount());
    }

    private boolean chanceOfThrowingErrorIsTrue(float chance) {
        return new Random().nextFloat() <= chance;
    }
}
