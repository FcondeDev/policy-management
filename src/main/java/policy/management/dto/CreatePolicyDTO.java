package policy.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CreatePolicyDTO {

    @NotEmpty
    private String startDate;
    @NotEmpty
    @Valid
    private List<InsuredPersonDTO> insuredPersons;


}
