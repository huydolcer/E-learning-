package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.UpdateUserRequest;
import DoAn.E_LearningEducation.Dto.request.UserCreationRequest;
import DoAn.E_LearningEducation.Dto.response.UserResponse;
import DoAn.E_LearningEducation.Exception.AppException;
import DoAn.E_LearningEducation.Exception.ErrorCode;
import DoAn.E_LearningEducation.Mapper.UserMapper;
import DoAn.E_LearningEducation.Model.User;
import DoAn.E_LearningEducation.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request){

        if(userRepository.existsByUsername(request.getUsername()))
            throw  new RuntimeException("User da ton tai !");

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        return userRepository.save(user);
    }
    public UserResponse updateUser(String userId, UpdateUserRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.toUserResponse(user, request);

        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
        } else {
            userRepository.updateUserWithoutPassword(
                    userId,
                    request.getUsername(),
                    request.getRole(),
                    request.getFirstname(),
                    request.getLastname(),
                    request.getEmail(),
                    request.getAddress()
            );
        }
        return userMapper.userResponse(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
