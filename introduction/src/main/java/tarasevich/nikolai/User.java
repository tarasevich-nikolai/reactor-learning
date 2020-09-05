package tarasevich.nikolai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class User {

    public final static User SAUL = User.builder()
        .username("saul")
        .firstname("saul")
        .lastname("ivanovich")
        .build();

    public final static User JESSE = User.builder()
        .username("jesse")
        .firstname("jesse")
        .lastname("fedorovich")
        .build();

    String username;

    String firstname;

    String lastname;

}