package DoAn.E_LearningEducation.Controller;

import DoAn.E_LearningEducation.Dto.request.AuthenticationRequest;
import DoAn.E_LearningEducation.Dto.request.IntrospectRequest;
import DoAn.E_LearningEducation.Dto.response.ApiResponse;
import DoAn.E_LearningEducation.Dto.response.AuthenticationResponse;
import DoAn.E_LearningEducation.Dto.response.IntrospectResponse;
import DoAn.E_LearningEducation.Service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/check-login")
    ApiResponse<AuthenticationResponse> authenticationResponseApiResponse(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspectResponseApiResponse(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspectResponse(request);

        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        // Kiểm tra token và xác minh
        if (token != null && isValidToken(token)) {
            return ResponseEntity.ok().build(); // Trả về 200 OK nếu token hợp lệ
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Trả về 401 nếu token không hợp lệ
        }
    }

    private boolean isValidToken(String token) {
        // Logic kiểm tra token (giả định bạn đã có mã kiểm tra token)
        return true; // Hoặc false nếu không hợp lệ
    }
}
