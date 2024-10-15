package DoAn.E_LearningEducation.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed", HttpStatus.BAD_REQUEST),// tuong duong 500
    UNCATEGORIZED_EXCEPTION(505,"Uncategorized exception!", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1003,"username is min 5 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004,"Password is min 8 characters", HttpStatus.BAD_REQUEST),
    INVALID_KEY(911,"Invalidate Key", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_INVALID(1005,"username is min 5 characters", HttpStatus.BAD_REQUEST),
    UN_AUTHENTICATION(202,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    USER_NOT_EXISTED(1009,"User not existed",HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1008, "Unauthorized users",HttpStatus.FORBIDDEN),
    BLOG_EXISTED(1102,"Blog existed",HttpStatus.BAD_REQUEST),
    BLOG_NOT_EXISTED(1007,"Blog is not existed",HttpStatus.NOT_FOUND),
    FILE_NOT_EXISTED(10023,"Empty Files",HttpStatus.NOT_FOUND)
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
