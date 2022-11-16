package policy.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CreatePolicyResponseDTO {

    private Long policyId;
    private String startDate;
    private List<InsuredPersonDTO> insuredPersons;
    private double totalPremium;

}
