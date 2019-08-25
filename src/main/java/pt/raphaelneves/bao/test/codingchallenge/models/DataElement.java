package pt.raphaelneves.bao.test.codingchallenge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.raphaelneves.bao.test.codingchallenge.integrations.dtos.DataElementDto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "elements")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataElement implements Serializable {
    @Id
    private String id;
    private String name;

    @JoinTable(name = "elements_groups", joinColumns = @JoinColumn(name = "element_id"),
    inverseJoinColumns = @JoinColumn(name = "groups"))
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DataElementGroup> dataElementGroups;

    @Transient
    private List<String> groups;

    public List<String> getGroups() {
        return Objects.isNull(dataElementGroups) ? null : dataElementGroups.stream().map(DataElementGroup::getName).collect(Collectors.toList());
    }
}
