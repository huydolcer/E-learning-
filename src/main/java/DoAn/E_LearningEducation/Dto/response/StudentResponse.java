package DoAn.E_LearningEducation.Dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    String userID;
    String cumulative_points;
    String phoneNumber;
    String cumulative_credit;
    int timetableID;
    String status;
}
