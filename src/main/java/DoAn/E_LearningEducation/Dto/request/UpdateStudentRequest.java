package DoAn.E_LearningEducation.Dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateStudentRequest {
    String userID;
    String cumulative_points;
    String phone_number;
    String cumulative_credit;
    int timetableID;
    String status;
}
