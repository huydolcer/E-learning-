package DoAn.E_LearningEducation.Dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UpdateBlogRequest {
    String title;
    String content;
    int categoryID;
    LocalDate dateCreate;
    int userID;
    int active;
}
