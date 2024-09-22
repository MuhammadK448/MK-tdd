package tek.tdd.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserProfileResponse {
    private int id;
    private String fullName;
    private String username;
    private AccountType accountType;
    @JsonIgnore
    private Object authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
