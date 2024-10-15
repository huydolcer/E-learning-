package DoAn.E_LearningEducation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/")
    String directionHtml(){
        return "user/index";
    }
    @GetMapping("/HomeAdmin")
    String directionHtmlAdmin(){
        return "admin/index";
    }
    @GetMapping("/HomeAdmin/MenuAdmin")
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
    @GetMapping("/Blog")
    String directionShowBlog(){
        return "user/blog";
    }
    @GetMapping("/get_blogs")
    String directionBlog_Detail(){
        return "user/blog_details";
    }
    @GetMapping("/HomeAdmin/BlogAdmin")
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
    @GetMapping("/HomeAdmin/AdminStudents")
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
}
