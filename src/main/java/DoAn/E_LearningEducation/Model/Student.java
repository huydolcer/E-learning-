package DoAn.E_LearningEducation.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String studentId;

    @ManyToOne
    @JoinColumn(name="userID")
    User user;
    String cumulative_points;
    String phone_number;
    String cumulative_credit;
    int timetableId;
    String status;
}
