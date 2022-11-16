package policy.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class InsuredPersonDTO {

    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String secondName;
    @Min(0)
    private double premium;

}
