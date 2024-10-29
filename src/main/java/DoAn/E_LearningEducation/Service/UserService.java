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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

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

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User byUsername = userRepository.findByUsername(name)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.userResponse(byUsername);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
    @PostAuthorize("hasRole('ADMIN') or returnObject.username == authentication.name")
    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not fount"));
    }

}
