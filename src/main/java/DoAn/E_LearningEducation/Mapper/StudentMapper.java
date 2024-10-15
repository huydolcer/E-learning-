package DoAn.E_LearningEducation.Mapper;

import DoAn.E_LearningEducation.Dto.request.StudentCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateStudentRequest;
import DoAn.E_LearningEducation.Dto.response.StudentResponse;
import DoAn.E_LearningEducation.Model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentCreationRequest request);
    StudentResponse toStudentResponse(Student student);
    void updateStudent(@MappingTarget Student student, UpdateStudentRequest request);
}
