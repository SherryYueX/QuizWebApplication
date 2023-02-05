package quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddr;
    private String physicalAddr;
    private String phoneNumber;
    private String password;
    private String status;
}
