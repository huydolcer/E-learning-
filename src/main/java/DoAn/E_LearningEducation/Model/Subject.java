package DoAn.E_LearningEducation.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String subjectId;
    String subjectName;
    String description;
    @OneToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

}
