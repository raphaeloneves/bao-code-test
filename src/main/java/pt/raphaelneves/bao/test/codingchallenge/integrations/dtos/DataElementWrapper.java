package pt.raphaelneves.bao.test.codingchallenge.integrations.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
public class DataElementWrapper implements DataWrapper{
    @JsonProperty("dataElements")
    List<DataElementDto> dataElementDtos;
}
