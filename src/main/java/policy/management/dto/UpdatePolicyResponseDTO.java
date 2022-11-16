package policy.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class UpdatePolicyResponseDTO {

    private Long policyId;
    private String effectiveDate;
    private List<InsuredPersonDTO> insuredPersons;
    private double totalPremium;

}
