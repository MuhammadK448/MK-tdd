package tek.tdd.api.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//Having pojo class is useful when we are dealing lots of objects
public class TokenRequest {
    private String username;
    private String password;
}
