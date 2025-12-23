package segments.info.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import segments.info.entities.RefreshToken;
import segments.info.request.AuthRequestDTO;
import segments.info.request.RefreshTokenDTO;
import segments.info.response.JwtResponse;
import segments.info.services.JwtService;
import segments.info.services.RefreshTokenService;

@Controller
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("auth/v1/refreshToken")
        public JwtResponse refreshToken(@RequestBody RefreshTokenDTO refreshToken){
            return refreshTokenService.findByToken(refreshToken.getToken())
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUserInfo)
                    .map(userInfo -> {
                        String accessToken = jwtService.generateToken(userInfo.getUsername());
                        return JwtResponse.builder().accessToken(accessToken).token(refreshToken.getToken()).build();
                    }) .orElseThrow( () -> new RuntimeException("Refresh token not found")) ;
        }

    @PostMapping("auth/v1/login")
    public ResponseEntity authenticateandgetToken(@RequestBody AuthRequestDTO authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(authRequest.getUsername());
            return new ResponseEntity<>(JwtResponse.builder().accessToken(jwtService.generateToken(authRequest.getUsername())).token(refreshToken.getToken()).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error: " , HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    }
