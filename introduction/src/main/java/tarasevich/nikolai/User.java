package tarasevich.nikolai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class User {

    String username;

    String firstname;

    String lastname;

}