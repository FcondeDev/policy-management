package policy.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class GetPolicyResponseDTO {

    private Long policyId;
    private String requestDate;
    private List<InsuredPersonDTO> insuredPersons;
    private double totalPremium;

}
