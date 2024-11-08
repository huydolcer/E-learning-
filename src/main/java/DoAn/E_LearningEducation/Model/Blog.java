package DoAn.E_LearningEducation.Model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int blogID;
    String title;
    String content;
    String image;
    String description;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    Category category;

    LocalDate datecreate;

    @ManyToOne
    @JoinColumn(name = "userID")
    User user;
    int active;
}
