package pt.raphaelneves.bao.test.codingchallenge.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.DataElementDto;
import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.DataElementGroupDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataElementGroup implements Serializable {
    @Id
    private String id;
    private String name;

    @ManyToMany(mappedBy = "dataElementGroups")
    @JsonIgnore
    private List<DataElement> dataElements;

    @Transient
    private List<String> members;

    public List<String> getMembers() {
        return Objects.isNull(dataElements) ? null :  dataElements.stream().map(DataElement::getName).collect(Collectors.toList());
    }
}
