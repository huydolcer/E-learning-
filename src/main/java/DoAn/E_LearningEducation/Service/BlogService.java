package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.BlogCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateBlogRequest;
import DoAn.E_LearningEducation.Dto.response.BlogResponse;
import DoAn.E_LearningEducation.Exception.AppException;
import DoAn.E_LearningEducation.Exception.ErrorCode;
import DoAn.E_LearningEducation.Mapper.BlogMapper;
import DoAn.E_LearningEducation.Model.Blog;
import DoAn.E_LearningEducation.Repository.BlogRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BlogService {
    BlogMapper blogMapper;
    BlogRepository blogRepository;


    public List<Blog> getAllBlogs(){
        return blogRepository.findAllByOrderByBlogIDDesc();
    }

    public Blog createBlog(BlogCreationRequest request){

        if(blogRepository.existsByTitle(request.getTitle()))
            throw new AppException(ErrorCode.BLOG_EXISTED);

        Blog blog = blogMapper.toBlog(request);
        return blogRepository.save(blog);

    }

    public void deleteBlog(int BlogID){

        if(blogRepository.existsByBlogID(BlogID))
            throw new AppException(ErrorCode.BLOG_NOT_EXISTED);

        blogRepository.deleteById(BlogID);
    }

    public BlogResponse updateBlog(int BlogID,UpdateBlogRequest request){

        Blog blog = blogRepository.findById(BlogID).orElseThrow(() ->
                new AppException(ErrorCode.BLOG_NOT_EXISTED));

        blogMapper.updateBlog(blog,request);
        return blogMapper.toResponseBlog(blogRepository.save(blog));
    }

    public Blog getBlogByID(int blogID){

       return blogRepository.findById(blogID).orElseThrow(()
               -> new AppException(ErrorCode.BLOG_NOT_EXISTED));
    }

}
