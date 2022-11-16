package policy.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import policy.management.exception.CustomException;
import policy.management.exception.error.ErrorEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "startDate")
    private LocalDate startDate;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<InsuredPerson> insuredPersons;

    public Policy(LocalDate startDate, List<InsuredPerson> insuredPersons) {
        this.startDate = startDate;
        this.insuredPersons = insuredPersons;
    }


    public void isAValidStartDateDate() {
        if (startDate.isBefore(LocalDate.now()))
            throw new CustomException(ErrorEnum.INVALID_DATE_EXCEPTION, HttpStatus.BAD_REQUEST);
    }

}
