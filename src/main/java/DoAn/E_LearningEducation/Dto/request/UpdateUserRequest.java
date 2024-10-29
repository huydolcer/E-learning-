package DoAn.E_LearningEducation.Dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateUserRequest {
    String username;
    String password;
    String role;
    String firstname;
    String lastname;
    String email;
    String address;
}
