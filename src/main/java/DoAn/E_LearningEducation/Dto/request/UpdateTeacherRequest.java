package DoAn.E_LearningEducation.Dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTeacherRequest {
    String userId;
    String specialization;
    String phoneNumber;
}
