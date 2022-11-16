package policy.management.mapper;

import org.springframework.stereotype.Component;
import policy.management.domain.InsuredPerson;
import policy.management.domain.Policy;
import policy.management.dto.*;
import policy.management.util.DateStringConversion;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PolicyManagementDTODomainMapper {

    public Policy mapCreatePolicyDTOToPolicy(CreatePolicyDTO createPolicyDTO) {
        return new Policy(DateStringConversion.convertStringToDate(createPolicyDTO.getStartDate()), mapInsuredPersonsDTOToInsuredPersons(createPolicyDTO.getInsuredPersons()));
    }

    public CreatePolicyResponseDTO mapPolicyToCreatePolicyResponseDTO(Policy policy, double totalPremium) {
        return new CreatePolicyResponseDTO(policy.getId(), DateStringConversion.convertDateToString(policy.getStartDate()), mapInsuredPersonsToInsuredPersonsDTO(policy.getInsuredPersons()), totalPremium);
    }

    public Policy mapUpdatePolicyDTOToPolicy(UpdatePolicyDTO updatePolicyDTO) {
        return new Policy(updatePolicyDTO.getPolicyId(), DateStringConversion.convertStringToDate(updatePolicyDTO.getEffectiveDate()), mapInsuredPersonsDTOToInsuredPersons(updatePolicyDTO.getInsuredPersons()));
    }

    public UpdatePolicyResponseDTO mapPolicyToUpdatePolicyResponseDTO(Policy policy, double totalPremium) {
        return new UpdatePolicyResponseDTO(policy.getId(), DateStringConversion.convertDateToString(policy.getStartDate()), mapInsuredPersonsToInsuredPersonsDTO(policy.getInsuredPersons()), totalPremium);
    }

    public GetPolicyResponseDTO mapPolicyToGetPolicyResponseDTO(Policy policy, double totalPremium) {
        return new GetPolicyResponseDTO(policy.getId(), DateStringConversion.convertDateToString(policy.getStartDate()), mapInsuredPersonsToInsuredPersonsDTO(policy.getInsuredPersons()), totalPremium);
    }


    private List<InsuredPersonDTO> mapInsuredPersonsToInsuredPersonsDTO(List<InsuredPerson> insuredPersons) {
        return insuredPersons.stream().map(entity -> new InsuredPersonDTO(entity.getId(), entity.getFirstName(), entity.getSecondName(), entity.getPremium())).collect(Collectors.toList());
    }


    private List<InsuredPerson> mapInsuredPersonsDTOToInsuredPersons(List<InsuredPersonDTO> insuredPersonsDTO) {
        return insuredPersonsDTO.stream().map(dto -> new InsuredPerson(dto.getId(), dto.getFirstName(), dto.getSecondName(), dto.getPremium())).collect(Collectors.toList());
    }


}
