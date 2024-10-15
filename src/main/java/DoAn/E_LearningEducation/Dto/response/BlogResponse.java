package DoAn.E_LearningEducation.Dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogResponse {
    String title;
    String content;
    int categoryID;
    LocalDate datecreate;
    String image;
    int userID;
    int active;
    String description;
}
