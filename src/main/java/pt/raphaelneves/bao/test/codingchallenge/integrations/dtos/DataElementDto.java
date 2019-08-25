package pt.raphaelneves.bao.test.codingchallenge.integrations.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataElementDto {
    private String id;
    private String displayName;
    private List<DataElementGroupDto> dataElementGroups;
}
