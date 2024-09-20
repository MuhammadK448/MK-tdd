package tek.tdd.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//Having pojo class is useful when we are dealing lots of objects
public class TokenRequest {
    private String username;
    private String password;
}
