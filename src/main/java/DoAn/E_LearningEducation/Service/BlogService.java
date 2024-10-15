package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.BlogCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateBlogRequest;
import DoAn.E_LearningEducation.Dto.response.BlogResponse;
import DoAn.E_LearningEducation.Exception.AppException;
import DoAn.E_LearningEducation.Exception.ErrorCode;
import DoAn.E_LearningEducation.Mapper.BlogMapper;
import DoAn.E_LearningEducation.Model.Blog;
import DoAn.E_LearningEducation.Model.Category;
import DoAn.E_LearningEducation.Model.User;
import DoAn.E_LearningEducation.Repository.BlogRepository;
import DoAn.E_LearningEducation.Repository.CategoryRepository;
import DoAn.E_LearningEducation.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BlogService {
    BlogMapper blogMapper;
    BlogRepository blogRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;
    private final String uploadDir = "src/main/resources/file_upload/";

    public Blog createBlog(BlogCreationRequest request, MultipartFile file) throws IOException {
        if (blogRepository.existsByTitle(request.getTitle())) {
            throw new AppException(ErrorCode.BLOG_EXISTED);
        }

        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại"));
        User user = userRepository.findById(String.valueOf(request.getUserID())).orElseThrow(()->
                new RuntimeException("User not existed!"));

        Blog blog = blogMapper.toBlog(request, category, user);
        if (file.isEmpty()) {
            throw new RuntimeException("File không hợp lệ");
        }
        String fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        blog.setImage("file_upload/" + fileName);
        blog.setDatecreate(LocalDate.now());

        return blogRepository.save(blog);
    }


    public List<Blog> getAllBlogs(){
        return blogRepository.findAllBlogsWithCategoryAndUser();
    }
    public List<Blog> getAllBlogByUser(){
        return blogRepository.findAllBlogWithActive();
    }
    public void deleteBlog(int BlogID){

//        if(blogRepository.existsByBlogID(BlogID))
//            throw new AppException(ErrorCode.BLOG_NOT_EXISTED);

        blogRepository.deleteById(BlogID);
    }

    public BlogResponse updateBlog(int blogID, UpdateBlogRequest request, MultipartFile file) throws IOException {
        Blog blog = blogRepository.findById(blogID).orElseThrow(() ->
                new AppException(ErrorCode.BLOG_NOT_EXISTED));

        System.out.println("request =" + request);


        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại"));
        User user = userRepository.findById(String.valueOf(request.getUserID()))
                .orElseThrow(() -> new RuntimeException("User not existed!"));
        blogMapper.updateBlog(blog, request);
        blog.setCategory(category);
        blog.setUser(user);
        blog.setDatecreate(LocalDate.now());
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);

            if (!Files.exists(filePath)) {
                Files.copy(file.getInputStream(), filePath);
            }

            blog.setImage("file_upload/" + fileName);
        }
        Blog updatedBlog = blogRepository.save(blog);

        return blogMapper.toResponseBlog(updatedBlog);
    }


    public Blog getBlogByID(int blogID){

       return blogRepository.findById(blogID).orElseThrow(()
               -> new AppException(ErrorCode.BLOG_NOT_EXISTED));
    }

}
