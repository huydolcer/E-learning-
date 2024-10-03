package DoAn.E_LearningEducation.Dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MenuCreationRequest {
    String title;
    int level;
    int position;
    int active;
    String link;
    int parentID;
}
