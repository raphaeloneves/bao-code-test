package pt.raphaelneves.bao.test.codingchallenge.configurations;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRequestConfiguration {

    private final Dhis2Configuration dhis2Configuration;

    @Autowired
    public FeignRequestConfiguration(Dhis2Configuration dhis2Configuration) {
        this.dhis2Configuration = dhis2Configuration;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(dhis2Configuration.getUsername(), dhis2Configuration.getPassword());
    }
}
