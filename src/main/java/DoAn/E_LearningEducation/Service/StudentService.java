package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.StudentCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateStudentRequest;
import DoAn.E_LearningEducation.Dto.response.StudentResponse;
import DoAn.E_LearningEducation.Exception.AppException;
import DoAn.E_LearningEducation.Exception.ErrorCode;
import DoAn.E_LearningEducation.Mapper.StudentMapper;
import DoAn.E_LearningEducation.Model.Student;
import DoAn.E_LearningEducation.Model.User;
import DoAn.E_LearningEducation.Repository.StudentRepository;
import DoAn.E_LearningEducation.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {

    StudentRepository studentRepository;
    StudentMapper studentMapper;
    UserRepository userRepository;

    public Student createStudent(StudentCreationRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = studentMapper.toStudent(request);
        student.setUser(user);
        return studentRepository.save(student);
    }
    public List<Student> getAllStudent(){
        return  studentRepository.findAll();
    }

    public Student getStudentById(String studentId){

        return studentRepository.findById(studentId).orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED));

    }

    public StudentResponse updateStudent(String studentId, UpdateStudentRequest request){

        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new AppException(ErrorCode.USER_EXISTED));

        studentMapper.updateStudent(student, request);

        return studentMapper.toStudentResponse(studentRepository.save(student));

    }

}
