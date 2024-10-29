package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.TeacherCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateTeacherRequest;
import DoAn.E_LearningEducation.Dto.response.ApiResponse;
import DoAn.E_LearningEducation.Dto.response.TeacherResponse;
import DoAn.E_LearningEducation.Exception.AppException;
import DoAn.E_LearningEducation.Exception.ErrorCode;
import DoAn.E_LearningEducation.Mapper.TeacherMapper;
import DoAn.E_LearningEducation.Model.Student;
import DoAn.E_LearningEducation.Model.Teacher;
import DoAn.E_LearningEducation.Model.User;
import DoAn.E_LearningEducation.Repository.TeacherRepository;
import DoAn.E_LearningEducation.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherService {

    TeacherRepository teacherRepository;
    TeacherMapper teacherMapper;
    UserRepository userRepository;

    public List<Teacher> getAllTeacher(){

        return teacherRepository.findAll();

    }

    public Teacher findTeacherById(String id){
        return teacherRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public Teacher createTeacher(TeacherCreationRequest request){

        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("user not exits!"));
        Teacher teacher = teacherMapper.toTeacher(request);
        teacher.setUser(user);
        return teacherRepository.save(teacher);
    }

    public TeacherResponse updateTeacher(String teacherId, UpdateTeacherRequest request){

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        teacherMapper.updateTeacher(teacher, request);

        return teacherMapper.toTeacherResponse(teacherRepository.save(teacher));

    }

}
