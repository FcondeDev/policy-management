package policy.management.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class UpdatePolicyDTO {

    @NotNull
    private Long policyId;
    @NotEmpty
    private String effectiveDate;
    @NotEmpty
    private List<InsuredPersonDTO> insuredPersons;


}
