package segments.info.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.moments.support.Now;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import segments.info.entities.RefreshToken;
import segments.info.entities.UserInfo;
import segments.info.repository.RefreshTokenRepository;
import segments.info.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
public class RefreshTokenService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(String username) {
        UserInfo userInfoExtracted = userRepository.findByUsername(username);
        RefreshToken refreshToken = new RefreshToken().builder()
                .userInfo(userInfoExtracted)
                .token(UUID.randomUUID().toString())
                .expiryDate(String.valueOf(Instant.now().plusMillis(6000)))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(String.valueOf(Instant.now()))<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " expired");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

}
