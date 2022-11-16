package policy.management.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import policy.management.domain.InsuredPerson;
import policy.management.domain.Policy;
import policy.management.domain.repository.PolicyRepository;
import policy.management.dto.*;
import policy.management.mapper.PolicyManagementDTODomainMapper;
import policy.management.service.PolicyManagementService;
import policy.management.util.DateStringConversion;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolicyManagementServiceImpl implements PolicyManagementService {

    private final PolicyManagementDTODomainMapper policyManagementDTODomainMapper;
    private final PolicyRepository policyRepository;


    @Autowired
    public PolicyManagementServiceImpl(PolicyManagementDTODomainMapper policyManagementDTODomainMapper, PolicyRepository policyRepository) {
        this.policyManagementDTODomainMapper = policyManagementDTODomainMapper;
        this.policyRepository = policyRepository;
    }

    @Override
    @Transactional
    public CreatePolicyResponseDTO createPolicy(CreatePolicyDTO createPolicyDTO) {
        Policy policy = policyManagementDTODomainMapper.mapCreatePolicyDTOToPolicy(createPolicyDTO);
        policy.isAValidStartDateDate();
        policy = policyRepository.createPolicy(policy);
        return policyManagementDTODomainMapper.mapPolicyToCreatePolicyResponseDTO(policy, calculateTotalPremium(policy.getInsuredPersons()));
    }

    @Override
    @Transactional
    public UpdatePolicyResponseDTO updatePolicy(UpdatePolicyDTO updatePolicyDTO) {
        Policy policy = policyManagementDTODomainMapper.mapUpdatePolicyDTOToPolicy(updatePolicyDTO);
        policyRepository.getPolicy(policy.getId());
        policy.isAValidStartDateDate();
        policy = policyRepository.updatePolicy(policy);
        return policyManagementDTODomainMapper.mapPolicyToUpdatePolicyResponseDTO(policy, calculateTotalPremium(policy.getInsuredPersons()));
    }

    @Override
    public GetPolicyResponseDTO getPolicyByIdAndDate(Long policyId, String requestDate) {
        LocalDate date = StringUtils.isEmpty(requestDate) ? LocalDate.now() : DateStringConversion.convertStringToDate(requestDate);
        Policy policy = policyRepository.getPolicyByIdAndDate(policyId, date);
        return policyManagementDTODomainMapper.mapPolicyToGetPolicyResponseDTO(policy, calculateTotalPremium(policy.getInsuredPersons()));
    }


    private double calculateTotalPremium(List<InsuredPerson> insuredPersons) {
        return insuredPersons.stream().mapToDouble(person -> person.getPremium()).sum();
    }
}
