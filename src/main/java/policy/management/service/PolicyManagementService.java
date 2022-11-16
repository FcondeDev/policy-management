package policy.management.service;

import org.springframework.validation.annotation.Validated;
import policy.management.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface PolicyManagementService {

    CreatePolicyResponseDTO createPolicy(@Valid CreatePolicyDTO createPolicyDTO);

    UpdatePolicyResponseDTO updatePolicy(@Valid UpdatePolicyDTO updatePolicyDTO);

    GetPolicyResponseDTO getPolicyByIdAndDate(@NotNull Long policyId, String requestDate);

}
