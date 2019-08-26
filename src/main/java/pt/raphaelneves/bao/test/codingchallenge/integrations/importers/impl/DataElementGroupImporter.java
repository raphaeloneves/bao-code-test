package pt.raphaelneves.bao.test.codingchallenge.integrations.importers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.raphaelneves.bao.test.codingchallenge.integrations.connectors.DataElementGroupConnector;
import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.*;
import pt.raphaelneves.bao.test.codingchallenge.integrations.importers.DataImporter;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElement;
import pt.raphaelneves.bao.test.codingchallenge.models.DataElementGroup;
import pt.raphaelneves.bao.test.codingchallenge.repositories.DataElementGroupRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("groupImporter")
@Slf4j
public class DataElementGroupImporter implements DataImporter {

    private final DataElementGroupConnector connector;
    private final DataElementGroupRepository dataElementGroupRepository;

    @Autowired
    public DataElementGroupImporter(DataElementGroupConnector connector, DataElementGroupRepository dataElementGroupRepository) {
        this.connector = connector;
        this.dataElementGroupRepository = dataElementGroupRepository;
    }

    @Override
    @Scheduled(fixedDelayString = "${time2update}")
    public void importData() {
        String response = connector.findAll();
        DataElementGroupWrapper wrappedResponse = wrapResponse(response);
        List<DataElementGroupDto> elements = wrappedResponse.getDataElementGroupsDtos();
        saveDataElements(elements);
    }

    private void saveDataElements(List<DataElementGroupDto> dtos) {
        List<DataElementGroup> elements = mapToEntity(dtos);
        elements.forEach(dataElementGroup -> {
            try {
                dataElementGroupRepository.save(dataElementGroup);
            }catch (Exception e){
                log.error(String.format("Duplicate ID for DataElementGroup %s", dataElementGroup.getId()));
            }
        });
    }

    private List<DataElementGroup> mapToEntity(List<DataElementGroupDto> dtos) {
        return dtos.stream().map(el -> DataElementGroup
                .builder()
                .id(el.getId())
                .name(el.getDisplayName())
                .dataElements(el.getDataElements()
                        .stream()
                        .map(de -> DataElement.builder()
                                .id(de.getId())
                                .build())
                        .collect(Collectors.toList()))
                .build())
        .collect(Collectors.toList());
    }

    @Override
    public DataElementGroupWrapper wrapResponse(String response) {
        DataElementGroupWrapper wrapper = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            wrapper = mapper.readValue(response, DataElementGroupWrapper.class);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return wrapper;
    }


}
