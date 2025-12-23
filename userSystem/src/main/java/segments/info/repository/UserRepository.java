package segments.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import segments.info.entities.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);
}
