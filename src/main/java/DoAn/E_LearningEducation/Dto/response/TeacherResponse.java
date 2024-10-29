package DoAn.E_LearningEducation.Dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherResponse {
    String userId;
    String cumulative_points;
    String phone_number;
    String cumulative_credit;
    int timetableID;
    String status;
}
