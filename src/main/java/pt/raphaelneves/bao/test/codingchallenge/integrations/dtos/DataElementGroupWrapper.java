package pt.raphaelneves.bao.test.codingchallenge.integrations.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataElementGroupWrapper implements DataWrapper{
    @JsonProperty("dataElementGroups")
    List<DataElementGroupDto> dataElementGroupsDtos;
}
