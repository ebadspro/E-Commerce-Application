package segments.info.repository;

import org.springframework.data.repository.CrudRepository;
import segments.info.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

}
