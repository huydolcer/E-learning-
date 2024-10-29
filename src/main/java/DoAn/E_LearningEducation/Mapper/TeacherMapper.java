package DoAn.E_LearningEducation.Mapper;


import DoAn.E_LearningEducation.Dto.request.TeacherCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateTeacherRequest;
import DoAn.E_LearningEducation.Dto.response.TeacherResponse;
import DoAn.E_LearningEducation.Model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    Teacher toTeacher(TeacherCreationRequest request);
    TeacherResponse toTeacherResponse(Teacher teacher);

    void updateTeacher(@MappingTarget Teacher teacher, UpdateTeacherRequest request);

}
