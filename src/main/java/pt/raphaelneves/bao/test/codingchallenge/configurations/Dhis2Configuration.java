package pt.raphaelneves.bao.test.codingchallenge.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "dhis2")
@Component
@Data
public class Dhis2Configuration {
    private String username;
    private String password;
    private String url;
}
