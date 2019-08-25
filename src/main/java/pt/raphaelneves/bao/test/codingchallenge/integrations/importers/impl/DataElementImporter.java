package pt.raphaelneves.bao.test.codingchallenge.integrations.importers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.raphaelneves.bao.test.codingchallenge.integrations.connectors.DataElementConnector;
import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.DataElementDto;
import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.DataElementWrapper;
import pt.raphaelneves.bao.test.codingchallenge.integrations.importers.DataImporter;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElement;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElementGroup;
import pt.raphaelneves.bao.test.codingchallenge.repositories.DataElementRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("elementImporter")
@Slf4j
public class DataElementImporter implements DataImporter {

    private final DataElementConnector connector;
    private final DataElementRepository dataElementRepository;

    @Autowired
    public DataElementImporter(DataElementConnector connector, DataElementRepository dataElementRepository) {
        this.connector = connector;
        this.dataElementRepository = dataElementRepository;
    }

    @Override
    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void importData() {
        String response = connector.findAll();
        DataElementWrapper wrappedResponse = wrapResponse(response);
        List<DataElementDto> elements = wrappedResponse.getDataElementDtos();
        saveDataElements(elements);
    }

    private void saveDataElements(List<DataElementDto> dtos) {
        List<DataElement> elements = mapToEntity(dtos);
        elements.forEach(dataElement -> {
            try {
                dataElementRepository.save(dataElement);
            }catch (Exception e){
                log.error(String.format("Duplicate ID for DataElement %s", dataElement.getId()));
            }
        });
    }

    private List<DataElement> mapToEntity(List<DataElementDto> dtos) {
        return dtos.stream().map(el -> DataElement.builder()
                .id(el.getId())
                .name(el.getDisplayName())
                .dataElementGroups(el.getDataElementGroups()
                        .stream()
                        .map(g -> DataElementGroup
                                .builder()
                                .id(g.getId())
                                .build())
                        .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public DataElementWrapper wrapResponse(String response) {
        DataElementWrapper wrapper = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            wrapper = mapper.readValue(response, DataElementWrapper.class);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return wrapper;
    }


}
