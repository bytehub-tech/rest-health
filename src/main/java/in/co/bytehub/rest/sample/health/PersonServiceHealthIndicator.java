package in.co.bytehub.rest.sample.health;

import in.co.bytehub.rest.sample.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PersonServiceHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        Status serviceStatus;
        ResponseEntity<Status> personServiceStatusEntity = restTemplate
                .getForEntity("http://127.0.0.1:9090/actuator/health", Status.class);

        String statusCode = personServiceStatusEntity.getBody().getStatus();
        switch (statusCode) {
            case "UP":
                builder.up();
                break;
            case "UNKNOWN":
                builder.unknown();
                break;
            case "OUT_OF_SERVICE":
                builder.outOfService();
                break;
            case "DOWN":
                builder.down();
                break;
        }


    }
}
