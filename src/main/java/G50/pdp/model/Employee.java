package G50.pdp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Check;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 250,message = "Are fucking idiot the minSize=3,maxSize=250")
    @Column( name = "first_name")
    private String firstName;

    @Size(min = 3, max = 250,message = "Are fucking idiot the minSize=3,maxSize=250")
    @Column( name = "last_name")
    private String lastName;


    @Column( name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;

    private String email;

    @Column( name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}
