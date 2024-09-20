package tek.tdd.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountResponse {
    private int id;
    private String email;
    private String title;
    private String firstName;
    private String lastName;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String employmentStatus;
    @JsonIgnore
    private Date dateOfBirth; //yyyy-MM-dd
}
