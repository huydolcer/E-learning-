package DoAn.E_LearningEducation.Service;

import DoAn.E_LearningEducation.Dto.request.AuthenticationRequest;
import DoAn.E_LearningEducation.Dto.request.IntrospectRequest;
import DoAn.E_LearningEducation.Dto.response.AuthenticationResponse;
import DoAn.E_LearningEducation.Dto.response.IntrospectResponse;
import DoAn.E_LearningEducation.Exception.AppException;
import DoAn.E_LearningEducation.Exception.ErrorCode;
import DoAn.E_LearningEducation.Model.User;
import DoAn.E_LearningEducation.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspectResponse(IntrospectRequest request) throws JOSEException, ParseException {
        var token =  request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        return
                IntrospectResponse.builder()
                        .valid(verified && expityTime.after(new Date()))
                        .build();
        
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SIGNER_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setAuthenticationFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SIGNER_KEY).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        String role = claims.get("scope", String.class); // Lấy vai trò từ token
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }




    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());
        if(!authenticated)
         throw new AppException(ErrorCode.UN_AUTHENTICATION);

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    private String generateToken(User user){

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwsClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("learning_edu")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(7, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("scope", user.getRole())
                .build();

        Payload payload = new Payload(jwsClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token!", e);
            throw new RuntimeException(e);
        }
    }

}
