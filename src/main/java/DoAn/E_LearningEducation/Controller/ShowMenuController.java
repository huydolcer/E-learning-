package DoAn.E_LearningEducation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowMenuController {

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
}
