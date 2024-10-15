package DoAn.E_LearningEducation.Dto.response;


import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String username;
    String password;
    int role;
    String firstname;
    String lastname;
    String email;
    String address;
}
