package DoAn.E_LearningEducation.Mapper;

import DoAn.E_LearningEducation.Dto.request.UpdateUserRequest;
import DoAn.E_LearningEducation.Dto.request.UserCreationRequest;
import DoAn.E_LearningEducation.Dto.response.UserResponse;
import DoAn.E_LearningEducation.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse userResponse(User user);
    void toUserResponse(@MappingTarget User user, UpdateUserRequest request);
}
