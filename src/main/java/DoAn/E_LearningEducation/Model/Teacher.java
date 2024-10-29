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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String teacherId;

    @ManyToOne
    @JoinColumn(name="userID",referencedColumnName = "userID")
    User user;
    String specialization;
    String phoneNumber;
}
