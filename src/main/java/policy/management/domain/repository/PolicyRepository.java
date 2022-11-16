package policy.management.domain.repository;

import policy.management.domain.Policy;

import java.time.LocalDate;

public interface PolicyRepository {

    Policy createPolicy(Policy policy);

    Policy updatePolicy(Policy policy);

    Policy getPolicyByIdAndDate(Long policyId, LocalDate date);

    Policy getPolicy(Long policyId);

}
