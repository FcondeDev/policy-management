package policy.management.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private double premium;

    public InsuredPerson(Long id, String firstName, String secondName, double premium) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.premium = premium;
    }
}
