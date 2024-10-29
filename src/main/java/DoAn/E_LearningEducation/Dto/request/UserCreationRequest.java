package DoAn.E_LearningEducation.Dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCreationRequest {
    String username;
    String password;
    String role;
    String firstname;
    String lastname;
    String email;
    String address;
}
