package DoAn.E_LearningEducation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRouter {
    @GetMapping("/")
    public String directionHtmlAdmin() {
        return "admin/index";
    }
    @GetMapping("/MenuAdmin")
    String showMenuAdmin(){
        return "admin/Menu";
    }
    @GetMapping("/add_menu")
    String directionAddMenu(){
        return "admin/add_menu";
    }
    @GetMapping("/update_menu")
    String directionUpdateMenu(){
        return "admin/update_menu";
    }
    @GetMapping("/BlogAdmin")
    String directionBlog(){
        return "admin/Blog";
    }
    @GetMapping("/add_blog")
    String directionAddBlog(){
        return "admin/add_blog";
    }
    @GetMapping("/update_blog")
    String directionUpdateBlog(){
        return "admin/update_blog";
    }
    @GetMapping("/AdminStudents")
    String directionStudents(){
        return "admin/Student";
    }
    @GetMapping("/add_student")
    String directionAddSudent(){
        return "admin/add_student";
    }
    @GetMapping("/update_student")
    String directionUpdateStudent(){
        return "admin/update_student";
    }
    @GetMapping("/Teachers")
    String directionTeacherIndex(){
        return "admin/teacher";
    }
    @GetMapping("/Teachers/create")
    String directionCreateTeacher(){
        return "admin/add_teacher";
    }
    @GetMapping("/update-teacher")
    String directionUpdateTeacher(){
        return "admin/update_teacher";
    }
    @GetMapping("/login")
    String directionLoginAdmin(){
        return "admin/login";
    }
}
