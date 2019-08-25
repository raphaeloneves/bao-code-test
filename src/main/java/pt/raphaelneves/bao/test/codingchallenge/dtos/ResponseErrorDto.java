package pt.raphaelneves.bao.test.codingchallenge.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "error")
@Data
@Builder
public class ResponseErrorDto {
    private LocalDateTime timestamp;
    private String rootCause;
    private String message;
}
