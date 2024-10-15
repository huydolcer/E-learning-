package DoAn.E_LearningEducation.Dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogCreationRequest {
    String title;
    String content;
    int categoryID;
    String image;
    LocalDate datecreate;
    String description;
    int userID;
    int active;
}
