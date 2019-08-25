package pt.raphaelneves.bao.test.codingchallenge.integrations.connectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.GetMapping;
import pt.raphaelneves.bao.test.codingchallenge.configurations.FeignRequestConfiguration;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@FeignClient(value = "dataElementGroup", url = "${dhis2.url}", configuration = FeignRequestConfiguration.class)
public interface DataElementGroupConnector {

    @GetMapping("/api/29/dataElementGroups.json?paging=false&fields=id,displayName,dataElements[id]")
    String findAll();
}