package DoAn.E_LearningEducation.Controller;


import DoAn.E_LearningEducation.Dto.request.StudentCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateStudentRequest;
import DoAn.E_LearningEducation.Model.Student;
import DoAn.E_LearningEducation.Service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/Students")
public class StudentController {

    StudentService studentService;

    @PostMapping("/create_student")
    public ResponseEntity<String> createStudent(@RequestBody StudentCreationRequest request){


        studentService.createStudent(request);

        return ResponseEntity.ok("Student Create is Successfully!");

    }
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable("id") String studentId){
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/update_student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") String studentId, @RequestBody UpdateStudentRequest request){

        studentService.updateStudent(studentId, request);

        return ResponseEntity.ok("Student Update Successfully!");

    }

}
