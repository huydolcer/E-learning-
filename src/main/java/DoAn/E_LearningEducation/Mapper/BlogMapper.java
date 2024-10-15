package DoAn.E_LearningEducation.Mapper;


import DoAn.E_LearningEducation.Dto.request.BlogCreationRequest;
import DoAn.E_LearningEducation.Dto.request.UpdateBlogRequest;
import DoAn.E_LearningEducation.Dto.response.BlogResponse;
import DoAn.E_LearningEducation.Model.Blog;
import DoAn.E_LearningEducation.Model.Category;
import DoAn.E_LearningEducation.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BlogMapper {
    @Mapping(source = "request.title", target = "title") // ánh xạ từ title của request đến title của Blog
    @Mapping(source = "request.content", target = "content")
    @Mapping(source = "request.categoryID", target = "category.categoryID") // ánh xạ categoryID tới thuộc tính id của Category
    @Mapping(source = "request.image", target = "image")
    @Mapping(source = "request.description", target = "description")
    @Mapping(source = "request.active", target = "active")
    @Mapping(source = "user", target = "user") // ánh xạ user
    Blog toBlog(BlogCreationRequest request, Category category, User user);

    BlogResponse toResponseBlog(Blog blog);

    void updateBlog(@MappingTarget Blog blog, UpdateBlogRequest request);
}

