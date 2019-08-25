package pt.raphaelneves.bao.test.codingchallenge.integrations.connectors;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.GetMapping;
import pt.raphaelneves.bao.test.codingchallenge.configurations.FeignRequestConfiguration;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@FeignClient(value = "dataElement", url = "${dhis2.url}", configuration = FeignRequestConfiguration.class)
public interface DataElementConnector {

    @GetMapping("/api/29/dataElements.json?paging=false&fields=id,displayName,dataElementGroups[id]")
    String findAll();
}