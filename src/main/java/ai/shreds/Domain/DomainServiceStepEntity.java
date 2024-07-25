package ai.shreds.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ElementCollection;
import javax.persistence.Column;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainServiceStepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String step;

    @ElementCollection
    private List<String> services;

    @Column(nullable = false)
    private boolean rights;
}