package policy.management.infrastructure.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import policy.management.domain.Policy;
import policy.management.domain.repository.PolicyRepository;
import policy.management.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class PolicyRepositoryH2Impl implements PolicyRepository {

    private final H2Repository h2Repository;

    @Autowired
    public PolicyRepositoryH2Impl(H2Repository h2Repository) {
        this.h2Repository = h2Repository;
    }

    @Override
    public Policy createPolicy(Policy policy) {
        return h2Repository.save(policy);
    }

    @Override
    public Policy updatePolicy(Policy policy) {
        return h2Repository.save(policy);
    }

    @Override
    public Policy getPolicyByIdAndDate(Long policyId, LocalDate date) {
        Optional<Policy> policy = h2Repository.findPolicyByIdAndStartDate(policyId, date);

        if (!policy.isPresent())
            throw new NotFoundException();

        return policy.get();
    }

    @Override
    public Policy getPolicy(Long policyId) {
        Optional<Policy> policy = h2Repository.findById(policyId);

        if (!policy.isPresent())
            throw new NotFoundException();

        return policy.get();
    }
}
