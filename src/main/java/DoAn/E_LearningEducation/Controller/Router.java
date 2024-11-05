package DoAn.E_LearningEducation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class Router {

    @GetMapping("/")
    String directionHtml(){
        return "user/index";
    }
    @GetMapping("/Blog")
    String directionShowBlog(){
        return "user/blog";
    }
    @GetMapping("/get_blogs")
    String directionBlog_Detail(){
        return "user/blog_details";
    }
    @GetMapping("/login-user")
    String directionLogin(){
        return "user/Login";
    }
}
