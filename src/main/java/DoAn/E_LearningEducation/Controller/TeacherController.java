package DoAn.E_LearningEducation.Controller;


import DoAn.E_LearningEducation.Dto.request.TeacherCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateTeacherRequest;
import DoAn.E_LearningEducation.Model.Teacher;
import DoAn.E_LearningEducation.Service.TeacherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Teachers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherController {

    TeacherService teacherService;

    @GetMapping("/show-teacher")
    List<Teacher> showAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @PostMapping("/create-teacher")
    ResponseEntity<String> createTeacher(@RequestBody TeacherCreationRequest request){

        teacherService.createTeacher(request);

        return ResponseEntity.ok("teacher create is successfully!");

    }

    @GetMapping("/find-teacher-by-id/{id}")
    Teacher findTeacherById(@PathVariable("id") String teacherId){
        return teacherService.findTeacherById(teacherId);
    }

    @PutMapping("/update-teacher/{id}")

    ResponseEntity<String> updateTeacher(@PathVariable("id") String id, @RequestBody UpdateTeacherRequest request){

        teacherService.updateTeacher(id, request);

        return ResponseEntity.ok("teacher update is successfully!");

    }



}
