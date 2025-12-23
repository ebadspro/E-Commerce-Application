package segments.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import segments.info.entities.RefreshToken;
import segments.info.model.UserInfoDTO;
import segments.info.response.JwtResponse;
import segments.info.services.JwtService;
import segments.info.services.RefreshTokenService;
import segments.info.services.UserServiceImpl;

@Controller
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity signup(@RequestBody UserInfoDTO userInfoDTO) {
        try {

            Boolean isSignuped = userService.signup(userInfoDTO);
            if (Boolean.FALSE.equals(isSignuped)) {
                return new ResponseEntity<>("Already registered", HttpStatus.BAD_REQUEST);
            }

            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(userInfoDTO.getUsername());
            String jwtToken = jwtService.generateToken(userInfoDTO.getUsername());
            return new ResponseEntity<>(JwtResponse.builder().accessToken(jwtToken).token(refreshToken.getToken()).build(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
