package DoAn.E_LearningEducation.Mapper;


import DoAn.E_LearningEducation.Dto.request.BlogCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateBlogRequest;
import DoAn.E_LearningEducation.Dto.response.BlogResponse;
import DoAn.E_LearningEducation.Model.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BlogMapper {
    Blog toBlog(BlogCreationRequest request);
    BlogResponse toResponseBlog(Blog blog);
    void updateBlog(@MappingTarget Blog blog, UpdateBlogRequest request);
}
