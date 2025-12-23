package segments.info.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import segments.info.entities.UserInfo;
import segments.info.model.UserInfoDTO;
import segments.info.repository.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Component
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;


    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(userInfo);
    }

    public UserInfo checkifUserAlreadyExists(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean signup(UserInfoDTO userInfoDTO) {
        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        if (Objects.nonNull(checkifUserAlreadyExists(userInfoDTO.getUsername()))) {
            return false;
        }

        String userId = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId, userInfoDTO.getUsername(), userInfoDTO.getPassword(),new HashSet<>()));
        return true;
    }
}
