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
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String documentId;
    String documentName;
    String url;
    int documentType;
    @ManyToOne
    @JoinColumn(name = "subjectId")
    Subject subject;
    String Author;

}
