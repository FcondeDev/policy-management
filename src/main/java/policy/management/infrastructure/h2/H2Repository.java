package policy.management.infrastructure.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import policy.management.domain.Policy;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface H2Repository extends JpaRepository<Policy, Long> {

    Optional<Policy> findPolicyByIdAndStartDate(Long id, LocalDate date);

}
