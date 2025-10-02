package G50.pdp.dto;

import G50.pdp.model.GenderEnum;
import G50.pdp.model.Group;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private GenderEnum genderEnum;

    private String email;

    private String phoneNumber;

    private String groupName;

}
