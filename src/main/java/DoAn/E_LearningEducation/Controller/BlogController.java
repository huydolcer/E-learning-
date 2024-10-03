package DoAn.E_LearningEducation.Controller;

import DoAn.E_LearningEducation.Dto.request.BlogCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateBlogRequest;
import DoAn.E_LearningEducation.Model.Blog;
import DoAn.E_LearningEducation.Service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/Blogs")
public class BlogController {

    BlogService blogService;

    @GetMapping
    List<Blog> showAllBlogs(){
        return blogService.getAllBlogs();
    }
    @PostMapping("/create_Blog")
    ResponseEntity<String> createBlog(@RequestBody BlogCreationRequest request){

        blogService.createBlog(request);
        return ResponseEntity.ok("Blog create successfully!");
    }

    @GetMapping("/get_blog/{id}")
    Blog getBlogById(@PathVariable("id") int blogId){
        return blogService.getBlogByID(blogId);
    }

    @PutMapping("/update_blog/{id}")
    ResponseEntity<String> update_Blog(@PathVariable("id") int blogId, UpdateBlogRequest request){
             blogService.updateBlog(blogId, request);
        return ResponseEntity.ok("Blog update successfully!");
    }
    @DeleteMapping("/delete_blog/{id}")
    ResponseEntity<String> delete_Blog(@PathVariable("id") int blogId){
        blogService.deleteBlog(blogId);

        return ResponseEntity.ok("Blog delete is successfully!");
    }

}
