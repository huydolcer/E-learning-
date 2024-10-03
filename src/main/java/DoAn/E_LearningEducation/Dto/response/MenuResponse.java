package DoAn.E_LearningEducation.Dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuResponse {
    int menuID;
    String title;
    int level;
    int position;
    int parentID;
    String link;
    int active;
}
