package DoAn.E_LearningEducation.Controller;

import DoAn.E_LearningEducation.Dto.request.AuthenticationRequest;
import DoAn.E_LearningEducation.Dto.request.IntrospectRequest;
import DoAn.E_LearningEducation.Dto.response.ApiResponse;
import DoAn.E_LearningEducation.Dto.response.AuthenticationResponse;
import DoAn.E_LearningEducation.Dto.response.IntrospectResponse;
import DoAn.E_LearningEducation.Service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    ApiResponse<AuthenticationResponse> authenticationResponseApiResponse(@RequestBody AuthenticationRequest request,
                                                                          HttpServletResponse response){
        var result = authenticationService.authenticate(request);
            System.out.println("KQ =" +result);
        if(result.isAuthenticated()){
            Cookie cookie = new Cookie("token", result.getToken());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);

            // In thông tin cookie ra console
            System.out.println("Cookie Name: " + cookie.getName());
            System.out.println("Cookie Value: " + cookie.getValue());
            System.out.println("Cookie Path: " + cookie.getPath());
            System.out.println("Cookie Max Age: " + cookie.getMaxAge());
        }

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

}
