package DoAn.E_LearningEducation.Controller;

import DoAn.E_LearningEducation.Dto.request.UpdateUserRequest;
import DoAn.E_LearningEducation.Dto.request.UserCreationRequest;
import DoAn.E_LearningEducation.Dto.response.ApiResponse;
import DoAn.E_LearningEducation.Model.User;
import DoAn.E_LearningEducation.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping("/create_user")
    ApiResponse<String> createUser(@RequestBody UserCreationRequest request){

        System.out.println("request =" + request);
        ApiResponse<String> apiResponse = new ApiResponse<>();

        User user = userService.createUser(request);
        String userStrId = user.getUserID();
        System.out.println("UserId = " + userStrId );
        apiResponse.setResult(userStrId);
        return apiResponse;
    }
    @DeleteMapping("/delete_user/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("user delete is successfully!");
    }
    @PutMapping("/update_user/{id}")
    ResponseEntity<String> updateUser(@PathVariable("id") String userId, @RequestBody UpdateUserRequest request){
        System.out.println("User Id Controller==" + userId);
        userService.updateUser(userId, request);
        return ResponseEntity.ok("User update is successfully!");
    }
}
