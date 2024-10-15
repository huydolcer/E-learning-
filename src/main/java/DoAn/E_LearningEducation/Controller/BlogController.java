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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/Blogs")
public class BlogController {

    BlogService blogService;

    @GetMapping("/show_Blog")
    List<Blog> showAllBlogs(){
        return blogService.getAllBlogs();
    }

    @GetMapping("/show_BlogUser")
    List<Blog> showAllBlogByUser(){
        return blogService.getAllBlogByUser();
    }

    @PostMapping("/create_blog")
    public ResponseEntity<String> createBlog(
            @RequestPart("request") BlogCreationRequest request,
            @RequestPart("file") MultipartFile file) throws IOException {

        System.out.println("title" + request.getTitle());
        blogService.createBlog(request, file);
        return ResponseEntity.ok("Blog created successfully!");
    }

    @GetMapping("/get_blog/{id}")
    Blog getBlogById(@PathVariable("id") int blogId){
        return blogService.getBlogByID(blogId);
    }

    @PutMapping("/update_blog/{id}")
    ResponseEntity<String> update_Blog(@PathVariable("id") int blogId,
                                       @RequestPart("request") UpdateBlogRequest request,
                                       @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        System.out.println("Blog ID: " + blogId);
        System.out.println("Request: " + request);
        System.out.println("File is present: " + (file != null && !file.isEmpty()));
        blogService.updateBlog(blogId, request, file);
        return ResponseEntity.ok("Blog updated successfully!");
    }


    @DeleteMapping("/delete_blog")
    ResponseEntity<String> delete_Blog(@RequestParam("id") int blogId) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.ok("Blog deleted successfully!");
    }
}
