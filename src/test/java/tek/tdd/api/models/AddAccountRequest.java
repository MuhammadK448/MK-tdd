package tek.tdd.api.models;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddAccountRequest {
    private String email;
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private String maritalStatus;
    private String employmentStatus;
    //private Date dateOfBirth; //yyyy-MM-dd
    private String dateOfBirth;
}
