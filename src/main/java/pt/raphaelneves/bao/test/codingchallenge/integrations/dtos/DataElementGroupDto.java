package pt.raphaelneves.bao.test.codingchallenge.integrations.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DataElementGroupDto {
    private String id;
    private String displayName;
    private List<DataElementDto> dataElements;
}
