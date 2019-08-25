package pt.raphaelneves.bao.test.codingchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.raphaelneves.bao.test.codingchallenge.services.DataElementGroupService;
import pt.raphaelneves.bao.test.codingchallenge.services.DataElementService;


@RestController
@CrossOrigin
public class ApiController {

    private final DataElementService dataElementService;
    private final DataElementGroupService dataElementGroupService;

    @Autowired
    public ApiController(DataElementService dataElementService, DataElementGroupService dataElementGroupService) {
        this.dataElementService = dataElementService;
        this.dataElementGroupService = dataElementGroupService;
    }

    @GetMapping(value = "/dataElements", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getDataElements(@RequestParam(name = "format", required = false,
            defaultValue = "json") String responseFormat) {
        return ResponseEntity.ok()
                .headers(getResponseContentType(responseFormat))
                .body(dataElementService.findAll());
    }

    @GetMapping(value = "/dataElementGroups", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getDataElementsGroups(@RequestParam(name = "format", required = false,
            defaultValue = "json") String responseFormat) {
        return ResponseEntity.ok()
                .headers(getResponseContentType(responseFormat))
                .body(dataElementGroupService.findAll());
    }

    private HttpHeaders getResponseContentType(String responseFormat) {
        String mediaType = "json".equals(responseFormat) ? MediaType.APPLICATION_JSON_VALUE : MediaType.APPLICATION_XML_VALUE;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", mediaType);
        responseHeaders.set("Accept", mediaType);
        return responseHeaders;
    }
}
